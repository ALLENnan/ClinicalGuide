package com.allen.guide.module.collect;

import com.allen.guide.base.IBaseView;
import com.allen.guide.model.entities.GuideBean;

import java.util.List;

public interface ICollectView extends IBaseView {

    void updateGuideList(List<GuideBean> guideList);
}
