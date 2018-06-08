package com.kco.fun.tools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kco.fun.activity.demo5.AlbumImageActivity;
import com.kco.fun.adapter.AlbumImageListAdapter;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void showImage(final Context context, final String picturPath,
                                 final String imageUrl, final String referer,
                                 final ImageView imageView){
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<File> emitter) throws Exception {
                String[] split = imageUrl.split("\\.");
                File externalFilesDir = context.getExternalFilesDir(picturPath);
                File file = new File(externalFilesDir, MD5Util.MD5(imageUrl) + "." +split[split.length - 1]);
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

    private static Map<String, String> parseUrls(String url, File file) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        Document parse = Jsoup.connect(url).execute().parse();
        Element first = parse.select(".content-pic img").first();
        String info = url + "\t" + first.attr("abs:src");
        resultMap.put("info", info);
        FileUtils.write(file, info + "\n", "utf8", true);
        Element last = parse.select("a.page-ch").last();
        if (StringUtils.equals("下一页", last.text())) {
            resultMap.put("next", last.attr("abs:href"));
        }
        return resultMap;
    }

    public static void listPicUrl(final Context context, final AlbumImageListAdapter albumImageListAdapter, String alburmInfo) {
        final String[] split = alburmInfo.split("\t");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                String fileName = MD5Util.MD5(split[0]) + ".txt";
                File file = new File(externalFilesDir, fileName);
                SharedPreferences listPicUrl = context.getSharedPreferences("listPicUrl", Context.MODE_APPEND);
                boolean aBoolean = listPicUrl.getBoolean(fileName, false);
                if (aBoolean && file.exists()){
                    List<String> utf8 = FileUtils.readLines(file, "utf8");
                    for (String temp : utf8){
                        emitter.onNext(temp);
                    }
                }else {
                    file.deleteOnExit();
                    String next = split[0];
                    do {
                        Map<String, String> map = AlbumTools.parseUrls(next, file);
                        emitter.onNext(map.get("info"));
                        next = map.get("next");
                    }while (StringUtils.isNoneBlank(next));
                    SharedPreferences.Editor edit = listPicUrl.edit();
                    edit.putBoolean(fileName, true);
                    edit.commit();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String info) throws Exception {
                        albumImageListAdapter.addPiceUrl(info);
                    }
                });
    }
}
