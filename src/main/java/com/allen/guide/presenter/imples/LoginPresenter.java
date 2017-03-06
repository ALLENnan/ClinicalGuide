package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.ILoginListener;
import com.allen.guide.model.imples.LoginModel;
import com.allen.guide.model.interfaces.ILoginModel;
import com.allen.guide.presenter.interfaces.ILoginPresenter;
import com.allen.guide.ui.interfaces.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter, ILoginListener {
    private ILoginModel mLoginModel;

    public LoginPresenter() {
        mLoginModel = new LoginModel();
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
    public void doLogin(String phoneNum, String password) {
        getView().showLoading();
        mLoginModel.doLogin(phoneNum, password, this);
    }

    @Override
    public void onSuccess(String msg) {
        getView().hideLoading();
        getView().navigateToMe();
    }

    @Override
    public void onFail(String msg) {
        getView().hideLoading();
        getView().setVerifyFail();
    }

    @Override
    public void onError(String msg) {
        getView().hideLoading();
        getView().showError(msg);
    }
}
