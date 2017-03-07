package com.allen.guide.model.interfaces;

import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.module.listener.ILoginListener;

public interface IUserModel {

    void doLogin(String phoneNum, String password, ILoginListener loginListener);

    void doRegister(String phoneNum, String password, IBaseListener baseListener);
}
