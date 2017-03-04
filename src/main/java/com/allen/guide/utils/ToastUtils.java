package com.allen.guide.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {

	private static Handler mHandler = new Handler(Looper.getMainLooper());
	private static Toast mToast = null;
	private static Object mSyncObj = new Object();

	public static void showMessage(final Context context, final String message) {
		showMessage(context, message, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context context, final int message) {
		showMessage(context, message, Toast.LENGTH_SHORT);
	}

	public static void showMessage(final Context context, final String message, final int duration) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				mHandler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (mSyncObj) {
							if (mToast != null) {
								mToast.setText(message);
								mToast.setDuration(duration);
							} else {
								if (null == context) {
									return;
								}
								mToast = Toast.makeText(context, message, duration);
							}
							mToast.show();
						}
					}
				});
			}
		}).start();
	}

	public static void showMessage(final Context context, final int message, final int duration) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				mHandler.post(new Runnable() {

					@Override
					public void run() {
						synchronized (mSyncObj) {
							if (mToast != null) {
								mToast.setText(message);
								mToast.setDuration(duration);
							} else {
								if (null == context) {
									return;
								}
								mToast = Toast.makeText(context, message, duration);
							}
							mToast.show();
						}
					}
				});
			}
		}).start();
	}
}