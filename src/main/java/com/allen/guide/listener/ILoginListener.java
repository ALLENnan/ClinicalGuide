package com.allen.guide.listener;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface ILoginListener extends IBaseListener {

    void onPhoneNumError(String msg);

    void onPasswordError(String msg);
    
}
