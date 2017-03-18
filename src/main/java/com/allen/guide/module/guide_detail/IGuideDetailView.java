package com.allen.guide.module.guide_detail;

import com.allen.guide.base.IBaseView;

public interface IGuideDetailView extends IBaseView {
    
    void initDownLoadUI(Boolean isDownLoad);
    
    void setDownLoadSuccess(String msg);

    void initFavourUI(Boolean isFavour);

    void updateFavourUI(Boolean isFavour);

    void initCollectUI(Boolean isCollect);

    void updateCollectUI(Boolean isCollect);
}
