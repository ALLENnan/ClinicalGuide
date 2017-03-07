package com.allen.guide.module.collect;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.module.listener.IGuideListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */

public class CollectPresenter extends BasePresenter<ICollectView> implements ICollectPresenter, IGuideListener {
    private IGuideModel mGuideModel;

    public CollectPresenter() {
        mGuideModel = GuideModel.getInstance();
    }

    @Override
    public void getCollectGuile() {
        getView().showLoading();
        mGuideModel.getCollectGuide(this);
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
