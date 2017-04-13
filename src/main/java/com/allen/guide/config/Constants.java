package com.allen.guide.config;

import android.os.Environment;

public class Constants {

    private Constants() {
    }

    public static final String GUIDE_BEAN = "guideBean";
    public static final String USER_ID = "userId";
    public static final String GUIDE_ID = "guideId";
    public static final String COMMENT_CONTENT = "content";

    public static final String DIR_PATH = Environment.getExternalStorageDirectory() + "/guide/";

    public static final String QUERY = "query";
    public static final String FIELD = "field";

    public static String[] RETRIEVE_SRTS = new String[]{"abstract_cn", "title", "author",
            "source"};

    public static final String KEY_HISTORY = "key_history";
    public static final String GUIDE_TITLE = "title";

    public static final String PARAM_CATEGORY = "category";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_USERID = "userid";

    public static final String PARAM_PHONE = "phonenum";
    public static final String PARAM_PASSWORD = "password";

    public static String VERIFY_KEY = "1ce84aae40e1f";
    public static String VERIFY_SECRET = "de0282f0e9c7f902db16f8055d9b2ec4";
}
