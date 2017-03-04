package com.allen.guide.model.interfaces;

import com.allen.guide.listener.ILoginListener;

public interface ILoginModel {

    void doLogin(String phoneNum, String password, ILoginListener loginListener);
}
