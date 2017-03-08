package com.allen.guide.module.guide_detail;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.IDownLoadListener;

public class GuideDetailPresenter extends BasePresenter<IGuideDetailView> implements IGuideDetailPresenter, IBaseListener, IDownLoadListener {
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
        mGuideModel.doDownload(guideBean, this);
    }

    @Override
    public void doCollect(GuideBean guideBean) {
        mGuideModel.doCollect(guideBean, this);
    }

    @Override
    public void onDownLoadSuccess(String msg) {
        getView().setDownLoadSuccess(msg);
    }

    @Override
    public void onDownLoadFail(String msg) {
        getView().showToast(msg);
    }
}
