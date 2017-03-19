package com.allen.guide.module.home;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.model.entities.SlideBean;
import com.allen.guide.model.imples.CommonModel;
import com.allen.guide.model.interfaces.ICommonModel;
import com.allen.guide.module.listener.IGuideListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.ISlideListener;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter, IGuideListener {
    private IGuideModel mGuideModel;
    private ICommonModel mCommonModel;

    public HomePresenter() {
        mGuideModel = GuideModel.getInstance();
        mCommonModel = CommonModel.getInstance();
    }

    @Override
    public void getNetGuile() {
        getView().showLoading();
        mGuideModel.getGuideList(this);
    }

    @Override
    public void getSlide() {
        getView().showLoading();
        mCommonModel.getSlide(new ISlideListener() {
            @Override
            public void onSuccess(List<SlideBean> slideBeans) {
                getView().showSlide(slideBeans);
            }

            @Override
            public void onFail(String msg) {
                getView().showToast(msg);
            }
        });
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
