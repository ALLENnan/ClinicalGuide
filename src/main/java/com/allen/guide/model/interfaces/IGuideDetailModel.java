package com.allen.guide.model.interfaces;

import com.allen.guide.listener.IBaseListener;
import com.allen.guide.model.entities.GuideBean;

public interface IGuideDetailModel {

    void doDownload(GuideBean guideBean);

    void doCollect(GuideBean guideBean, IBaseListener baseListener);
}
