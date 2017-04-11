package com.allen.guide.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.allen.guide.App;
import com.allen.guide.model.entities.UserBean;
import com.google.gson.Gson;

public class UserUtil {
    private static final String KEY_SP = "com.allen.guide.prefs";
    private static final String KEY_USER = "user";

    /**
     * 获取当前用户
     *
     * @param context
     * @return
     */
    public static UserBean getCurrentUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(KEY_SP, Context.MODE_PRIVATE);
        String str = sp.getString(KEY_USER, "");
        if (str == "") {
            return null;
        } else {
            return (new Gson()).fromJson(str, UserBean.class);
        }
    }

    /**
     * 保存当前用户到本地
     *
     * @param context
     * @param jsonString
     */
    public static void saveCurrentUser(Context context, String jsonString) {
        SharedPreferences sp = context.getSharedPreferences(KEY_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_USER, jsonString);
        editor.commit();
    }

    /**
     * @param context
     * @param userBean
     */
    public static void saveCurrentUser(Context context, UserBean userBean) {
        SharedPreferences sp = context.getSharedPreferences(KEY_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        editor.putString(KEY_USER, gson.toJson(userBean));
        editor.commit();
    }

    /**
     * 注销当前用户
     *
     * @param context
     */
    public static void logOut(Context context) {
        SharedPreferences sp = context.getSharedPreferences(KEY_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(KEY_USER);
        editor.commit();
    }

    /**
     * 检查用户是否登录
     */
    public static boolean checkLogined(Context context) {
        if (UserUtil.getCurrentUser(App.getContext()) == null) {
            return false;
        }
        return true;
    }
}
