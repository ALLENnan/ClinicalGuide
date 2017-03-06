package com.allen.guide.model.interfaces;

import com.allen.guide.listener.IBaseListener;

public interface IRegisterModel {

    void doRegister(String phoneNum, String password, IBaseListener baseListener);
}
