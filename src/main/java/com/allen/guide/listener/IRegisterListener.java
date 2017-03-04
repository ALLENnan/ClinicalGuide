package com.allen.guide.listener;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface IRegisterListener {

    void onSuccess(String msg);

    void onPhoneNumError(String msg);

    void onPasswordError(String msg);
    
    void onError(String msg);
}
