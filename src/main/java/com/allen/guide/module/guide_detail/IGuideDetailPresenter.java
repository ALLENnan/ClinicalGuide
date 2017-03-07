package com.allen.guide.module.guide_detail;

import com.allen.guide.model.entities.GuideBean;

public interface IGuideDetailPresenter {
    
    void doDownload(GuideBean guideBean);
    
    void doCollect(GuideBean guideBean);
}
