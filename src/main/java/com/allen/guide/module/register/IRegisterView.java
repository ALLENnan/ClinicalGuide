package com.allen.guide.module.register;

import com.allen.guide.base.IBaseView;

public interface IRegisterView extends IBaseView {

    void setSuccess(String msg);

    void setFail(String msg);
}
