package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.ILoginListener;
import com.allen.guide.model.imples.LoginModel;
import com.allen.guide.model.interfaces.ILoginModel;
import com.allen.guide.presenter.interfaces.ILoginPresenter;
import com.allen.guide.ui.interfaces.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter, ILoginListener{
    private ILoginModel mLoginModel;

    public LoginPresenter() {
        mLoginModel = new LoginModel();
    }

    @Override
    public void onSuccess() {
        getView().hideLoading();
        getView().navigateToMe();
    }

    @Override
    public void onPhoneNumError(String msg) {
        getView().hideLoading();
        getView().setPhoneNumError(msg);
    }

    @Override
    public void onPasswordError(String msg) {
        getView().hideLoading();
        getView().setPasswordError(msg);
    }

    @Override
    public void onNetVerifyError(String msg) {
        getView().hideLoading();
        getView().setNetVerifyError(msg);
    }

    @Override
    public void doLogin(String phoneNum, String password) {
        getView().showLoading();
        mLoginModel.doLogin(phoneNum, password ,this);
    }
}
