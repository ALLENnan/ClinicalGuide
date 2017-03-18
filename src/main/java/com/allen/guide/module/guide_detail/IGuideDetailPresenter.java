package com.allen.guide.module.guide_detail;

import com.allen.guide.model.entities.GuideBean;

public interface IGuideDetailPresenter {

    void doDownload(GuideBean guideBean);

    void isDownload(GuideBean guideBean);

    void doCollect(GuideBean guideBean);

    void isUserCollect(GuideBean guideBean);

    void doFavour(GuideBean guideBean);

    void isUserFavour(GuideBean guideBean);
}
