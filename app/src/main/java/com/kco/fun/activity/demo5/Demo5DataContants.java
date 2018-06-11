package com.kco.fun.activity.demo5;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvsw on 2018/6/8.
 */
public class Demo5DataContants {
    public static final String[] assertFileName = {
            "all.txt",
            "chemo.txt",
            "mingxing.txt",
            "qipao.txt",
            "xiaohua.txt",
            "qingchun.txt"
    };
    public static final int alburm_all_index = 0;
    public static final int alburm_chemo_index = 1;
    public static final int alburm_mingxing_index = 2;
    public static final int alburm_qipao_index = 3;
    public static final int alburm_xiaohua_index = 4;
    public static final int alburm_qingchu_index = 5;
    public static List<String> readAssertFileData(Context context, int index){
        try {
            return IOUtils.readLines(context.getAssets().open(assertFileName[index]), "utf8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }


}
