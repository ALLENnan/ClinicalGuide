package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.IBaseListener;
import com.allen.guide.model.imples.RegisterModel;
import com.allen.guide.model.interfaces.IRegisterModel;
import com.allen.guide.presenter.interfaces.IRegisterPresenter;
import com.allen.guide.ui.interfaces.IRegisterView;

public class RegisterPresenter extends BasePresenter<IRegisterView> implements IRegisterPresenter, IBaseListener {
    private IRegisterModel mRegisterModel;

    public RegisterPresenter() {
        mRegisterModel = new RegisterModel();
    }
    @Override
    public void doRegister(String phoneNum, String password) {
        mRegisterModel.doRegister(phoneNum, password, this);
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
