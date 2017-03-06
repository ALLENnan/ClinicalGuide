package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.IGuideListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.presenter.interfaces.IHomePresenter;
import com.allen.guide.ui.interfaces.IHomeView;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter, IGuideListener {
    private IGuideModel mGuideModel;

    public HomePresenter() {
        mGuideModel = new GuideModel();
    }

    @Override
    public void getNetGuile() {
        getView().showLoading();
        mGuideModel.getGuideList(this);
    }

    @Override
    public void onSuccess(List<GuideBean> guideList) {
        getView().hideLoading();
        getView().updateGuideList(guideList);
    }

    @Override
    public void onSuccess(String msg) {
    }

    @Override
    public void onFail(String msg) {
        getView().hideLoading();
        getView().showToast(msg);
    }

    @Override
    public void onError(String msg) {
        getView().hideLoading();
        getView().showError(msg);
    }
}
