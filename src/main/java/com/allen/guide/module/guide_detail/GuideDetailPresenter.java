package com.allen.guide.module.guide_detail;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;

public class GuideDetailPresenter extends BasePresenter<IGuideDetailView> implements IGuideDetailPresenter, IBaseListener {
    private IGuideModel mGuideModel;

    public GuideDetailPresenter() {
        mGuideModel = GuideModel.getInstance();
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
