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
}
