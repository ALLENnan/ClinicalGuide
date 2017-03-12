package com.allen.guide.module.retrieve;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.IGuideListener;

import java.util.List;

public class RetrievePresenter extends BasePresenter<IRetrieveView> implements IRetrievePresenter, IGuideListener {
    private IGuideModel mGuideModel;

    public RetrievePresenter() {
        mGuideModel = GuideModel.getInstance();
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

    @Override
    public void retrieveGuiles(String field, String query) {
        getView().showLoading();
        mGuideModel.retrieveGuides(field, query, this);
    }
}
