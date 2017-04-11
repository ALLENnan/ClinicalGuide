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

}
