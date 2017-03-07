package com.allen.guide.model.interfaces;

import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.module.listener.IGuideListener;
import com.allen.guide.model.entities.GuideBean;

public interface IGuideModel {

    void getGuideList(IGuideListener guideListener);

    void getCollectGuide(IGuideListener guideListener);

    void doDownload(GuideBean guideBean);

    void doCollect(GuideBean guideBean, IBaseListener baseListener);
}
