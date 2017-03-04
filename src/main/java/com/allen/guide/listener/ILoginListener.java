package com.allen.guide.listener;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface ILoginListener {

    void onSuccess();

    void onPhoneNumError(String msg);

    void onPasswordError(String msg);

    void onNetVerifyError(String msg);

}
