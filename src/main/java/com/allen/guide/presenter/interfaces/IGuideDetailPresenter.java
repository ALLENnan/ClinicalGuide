package com.allen.guide.presenter.interfaces;

import com.allen.guide.model.entities.GuideBean;

public interface IGuideDetailPresenter {
    
    void doDownload(GuideBean guideBean);
    
    void doCollect(GuideBean guideBean);
}
