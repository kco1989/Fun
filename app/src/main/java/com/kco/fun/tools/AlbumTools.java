package com.kco.fun.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kco.fun.activity.demo5.AlbumImageActivity;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 666666 on 2018/6/8.
 */

public final class AlbumTools {
    public static void listOnClick(final Context context, String alburmInfo){
        final String[] split = alburmInfo.split("\\s+");
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                String fileName = split[1].replaceAll("http://.*?/(.*?)/(.*?).html", "$1$2.txt");
                File file = new File(externalFilesDir, fileName);
                if (!file.exists()){
                    AlbumTools.parseUrls(split[0], file);
                }
                if (file.exists()){
                    List<String> utf8 = FileUtils.readLines(file, "utf8");
                    emitter.onNext(utf8);
                }else {
                    emitter.onNext(null);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        if (strings != null && !strings.isEmpty()){
                            Intent intent = new Intent(context, AlbumImageActivity.class);
                            intent.putExtra("picUrls", new Gson().toJson(strings));
                            context.startActivity(intent);
                        }
                    }
                });
    }

    private static void parseUrls(String url, File file) throws Exception {
        Document parse = Jsoup.connect(url).execute().parse();
        Element first = parse.select(".content-pic img").first();
        FileUtils.write(file, url + "\t" + first.attr("abs:src") + "\n", "utf8", true);
        Element last = parse.select("a.page-ch").last();
        if (StringUtils.equals("下一页", last.text())) {
            parseUrls(last.attr("abs:href"), file);
        }
    }

    public static void showImage(final Context context, final String picturPath,
                                 final String imageUrl, final String referer,
                                 final ImageView imageView){
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<File> emitter) throws Exception {
                String replace = imageUrl.replace("http://", "");
                File externalFilesDir = context.getExternalFilesDir(picturPath);
                File file = new File(externalFilesDir, "alburm/" + replace);
                if (!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()){
                    IOUtils.write(Jsoup.connect(imageUrl)
                            .ignoreContentType(true)
                            .header("Referer", referer)
                            .header("Host", "img1.mm131.me")
                            .header("Pragma", "no-cache")
                            .execute().bodyAsBytes(), new FileOutputStream(file));
                }
                emitter.onNext(file);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Exception {
                        Glide.with(context).load(file).into(imageView);
                    }
                });
    }
}
