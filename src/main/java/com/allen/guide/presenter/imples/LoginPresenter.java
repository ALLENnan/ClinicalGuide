package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.ILoginListener;
import com.allen.guide.model.imples.UserModel;
import com.allen.guide.model.interfaces.IUserModel;
import com.allen.guide.presenter.interfaces.ILoginPresenter;
import com.allen.guide.ui.interfaces.ILoginView;

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter, ILoginListener {
    private IUserModel mUserModel;

    public LoginPresenter() {
        mUserModel = UserModel.getInstance();
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
        mUserModel.doLogin(phoneNum, password, this);
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
