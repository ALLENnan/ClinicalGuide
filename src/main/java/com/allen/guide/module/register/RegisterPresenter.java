package com.allen.guide.module.register;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.model.imples.UserModel;
import com.allen.guide.model.interfaces.IUserModel;

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter, IBaseListener {
    private IUserModel mUserModel;

    public RegisterPresenter() {
        mUserModel = UserModel.getInstance();
    }

    @Override
    public void doRegister(String phoneNum, String password) {
        mUserModel.doRegister(phoneNum, password, this);
    }

    @Override
    public void onSuccess(String msg) {
        getView().hideLoading();
        getView().setSuccess(msg);
    }

    @Override
    public void onFail(String msg) {
        getView().setFail(msg);
    }

    @Override
    public void onError(String msg) {
        getView().showError(msg);
    }
}
