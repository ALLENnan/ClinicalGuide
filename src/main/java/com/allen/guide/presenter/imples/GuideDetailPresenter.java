package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.IBaseListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideDetailModel;
import com.allen.guide.model.interfaces.IGuideDetailModel;
import com.allen.guide.presenter.interfaces.IGuideDetailPresenter;
import com.allen.guide.ui.interfaces.IGuideDetailView;

public class GuideDetailPresenter extends BasePresenter<IGuideDetailView> implements IGuideDetailPresenter, IBaseListener {
    private IGuideDetailModel mGuideDetailModel;

    public GuideDetailPresenter() {
        mGuideDetailModel = new GuideDetailModel();
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
        mGuideDetailModel.doCollect(guideBean, this);
    }
}
