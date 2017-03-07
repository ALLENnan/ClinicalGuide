package com.allen.guide.module.login;

import com.allen.guide.base.IBaseView;

public interface ILoginView extends IBaseView {

    void setPhoneNumError(String msg);

    void setPasswordError(String msg);
    
    void setVerifyFail();
    
    void navigateToMe();
}
