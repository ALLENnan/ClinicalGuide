package com.allen.guide.module.guide_detail;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.config.Constants;
import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.ICollectListener;
import com.allen.guide.module.listener.IDownLoadListener;
import com.allen.guide.module.listener.IFavourListener;

import java.io.File;

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
    public void isDownload(GuideBean guideBean) {
        File file = new File(Constants.DIR_PATH + guideBean.getTitle() + ".pdf");
        boolean b = file.exists();
        getView().initDownLoadUI(b);
    }

    @Override
    public void doCollect(GuideBean guideBean) {
        mGuideModel.doCollect(guideBean, new ICollectListener() {

            @Override
            public void onSuccess(Boolean isCollect, String msg) {
                getView().updateCollectUI(isCollect);
            }

            @Override
            public void onFail(String msg) {
                getView().showToast(msg);
            }
        });
    }

    @Override
    public void isUserCollect(GuideBean guideBean) {
        mGuideModel.isUserCollect(guideBean, new ICollectListener() {

            @Override
            public void onSuccess(Boolean isCollect, String msg) {
                getView().initCollectUI(isCollect);
            }

            @Override
            public void onFail(String msg) {
                getView().showToast(msg);
            }
        });
    }

    @Override
    public void doFavour(GuideBean guideBean) {
        mGuideModel.updateFavour(guideBean, new IFavourListener() {

            @Override
            public void onSuccess(Boolean isFavour, String msg) {
                getView().updateFavourUI(isFavour);
            }

            @Override
            public void onFail(String msg) {
                getView().showToast(msg);
            }
        });
    }

    @Override
    public void isUserFavour(GuideBean guideBean) {
        mGuideModel.isUserFavour(guideBean, new IFavourListener() {
            @Override
            public void onSuccess(Boolean isFavour, String msg) {
                getView().initFavourUI(isFavour);
            }

            @Override
            public void onFail(String msg) {
                getView().showToast(msg);
            }
        });
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
