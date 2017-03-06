package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.IBaseListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.presenter.interfaces.IGuideDetailPresenter;
import com.allen.guide.ui.interfaces.IGuideDetailView;

public class GuideDetailPresenter extends BasePresenter<IGuideDetailView> implements IGuideDetailPresenter, IBaseListener {
    private IGuideModel mGuideModel;

    public GuideDetailPresenter() {
        mGuideModel = new GuideModel();
    }

    @Override
    public void onSuccess(String msg) {
        getView().showToast(msg);
    }

    @Override
    public void onFail(String msg) {
        getView().showToast(msg);
    }

    @Override
    public void onError(String msg) {
        getView().showToast(msg);
    }

    @Override
    public void doDownload(GuideBean guideBean) {
        
    }

    @Override
    public void doCollect(GuideBean guideBean) {
        mGuideModel.doCollect(guideBean, this);
    }
}
