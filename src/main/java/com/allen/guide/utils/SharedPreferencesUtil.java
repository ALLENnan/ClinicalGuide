package com.allen.guide.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesUtil {
    private static final String PREFERENCE_NAME = "guide_data";

    private SharedPreferencesUtil() {
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putDataSet(Context context, String key, Set<String> datas) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, datas);
        editor.commit();
    }

    public static Set<String> getDataSet(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        return sp.getStringSet(key, new HashSet<String>());
    }

    public static void clear(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        
    }
    
}
