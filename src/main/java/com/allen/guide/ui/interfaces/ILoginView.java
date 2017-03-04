package com.allen.guide.ui.interfaces;

import com.allen.guide.base.IBaseView;

public interface ILoginView extends IBaseView {

    void setPhoneNumError(String msg);

    void setPasswordError(String msg);
    
    void setNetVerifyError(String msg);
    
    void navigateToMe();
}
