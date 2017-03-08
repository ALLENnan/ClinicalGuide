package com.allen.guide.net;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.allen.guide.config.Constants;
import com.allen.guide.module.listener.IDownLoadListener;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.allen.guide.config.Constants.DIR_PATH;


public class DownloadUtil {
    private Handler mMainHandler;

    public DownloadUtil() {
        mMainHandler = new Handler(Looper.getMainLooper());
    }

    public void start(final String fileUrl, final IDownLoadListener downLoadListener) {
        new Thread(new DownloadRunnable(fileUrl, downLoadListener)).start();
    }

    class DownloadRunnable implements Runnable {
        private String fileUrl;
        private IDownLoadListener mIDownLoadListener;

        public DownloadRunnable(String fileUrl, IDownLoadListener downLoadListener) {
            this.fileUrl = fileUrl;
            this.mIDownLoadListener = downLoadListener;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(fileUrl);
                HttpURLConnection connection = (HttpURLConnection)
                        url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                if (connection.getResponseCode() == 200) {
                    InputStream is = connection.getInputStream();
                    byte[] arr = new byte[1];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(baos);
                    int n = is.read(arr);
                    while (n > 0) {
                        bos.write(arr);
                        n = is.read(arr);

                    }
                    bos.close();
                    
                    File file = new File(Constants.DIR_PATH);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    String fileName = fileUrl.substring(fileUrl.indexOf("=") + 1);
                    String filePath = Constants.DIR_PATH + fileName;
                    Log.d("Allen-----", "DownloadRunnable->run: " + fileName + ":" + filePath);

                    FileOutputStream fos = new FileOutputStream(new File(filePath));
                    fos.write(baos.toByteArray());
                    fos.close();
                    //关闭网络连接
                    connection.disconnect();
                    
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mIDownLoadListener.onDownLoadSuccess("下载完成");
                        }
                    });
                    Log.d("Allen-----", "DownloadRunnable->run: 下载完成");

//                    if (file.exists()) {
//                        System.out.println("打开");
//                        Uri path1 = Uri.fromFile(file);
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setDataAndType(path1, "application/pdf");
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                        try {
//                            context.startActivity(intent);
//                        } catch (ActivityNotFoundException e) {
//                            System.out.println("打开失败");
//
//                        }
//                    }
                }
            } catch (Exception e) {
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIDownLoadListener.onDownLoadFail("下载失败");

                    }
                });
                e.printStackTrace();
            }
        }
    }

}
