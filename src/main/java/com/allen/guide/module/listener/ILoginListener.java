package com.allen.guide.module.listener;

import com.allen.guide.module.listener.IBaseListener;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface ILoginListener extends IBaseListener {

    void onPhoneNumError(String msg);

    void onPasswordError(String msg);
    
}
