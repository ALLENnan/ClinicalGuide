package com.allen.guide.model.interfaces;

import com.allen.guide.listener.IRegisterListener;

public interface IRegisterModel {

    void doRegister(String phoneNum, String password, IRegisterListener registerListener);
}
