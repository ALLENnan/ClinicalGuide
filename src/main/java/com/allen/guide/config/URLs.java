package com.allen.guide.config;

/**
 * @author Allen
 * @brief 后台URL
 * @date 17/2/27
 */
public class URLs {
    //手机
    public static final String SERVER_IP = "http://192.168.2.1:8080/GuideServer";
    //实验室
//    public static final String SERVER_IP = "http://192.168.0.103:8080/GuideServer";
    //Genymotion
//    public static final String SERVER_IP = "http://10.0.3.2:8080/GuideServer";
    
    public static final String file_download = SERVER_IP + "www/";
    public static final String GUIDE = SERVER_IP + "/guide";
    public static final String LOGIN = SERVER_IP + "/login";
    public static final String REGISTER = SERVER_IP + "/register";
    public static final String COLLECT = SERVER_IP + "/collect";
    public static final String COMMENT = SERVER_IP + "/comment";
    public static final String RETRIEVE = SERVER_IP + "/retrieve";
    public static final String FAVOUR = SERVER_IP + "/favour";

}
