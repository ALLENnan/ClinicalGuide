package com.allen.guide.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 返回单一目录下的文件名
     *
     * @param rootPath 根目录
     * @return
     */
    public static List<String> getFileDir(String rootPath) {
        List<String> list = new ArrayList<>();
        File f = new File(rootPath);
        if (!f.exists()) {
            return null;
        }
        File[] files = f.listFiles();
        if (files != null) {
            int count = files.length;
            for (int i = 0; i < count; i++) {
                File file = files[i];
                list.add(file.getName());
            }
        }
        return list;
    }

    /**
     * 打开PDF文件
     *
     * @param context
     * @param file
     */
    public static void openPDF(Context context, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent2 = new Intent(Intent.ACTION_VIEW);
            intent2.setDataAndType(uri, "application/pdf");
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                context.startActivity(intent2);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
