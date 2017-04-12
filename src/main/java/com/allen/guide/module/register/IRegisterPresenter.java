package com.allen.guide.module.register;

/**
 * @author Allen
 * @brief
 * @date 17/3/2
 */
public interface IRegisterPresenter {

    void doRegister(String phoneNum, String password);

    boolean getVerifyStatus();

    void setVerifyStatus(boolean isVerify);

    boolean isInputLegal(String phoneNum, String password);
}
