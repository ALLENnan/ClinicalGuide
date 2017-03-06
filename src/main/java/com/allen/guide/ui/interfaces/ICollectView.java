package com.allen.guide.ui.interfaces;

import com.allen.guide.base.IBaseView;
import com.allen.guide.model.entities.GuideBean;

import java.util.List;

public interface ICollectView extends IBaseView {

    void updateGuideList(List<GuideBean> guideList);
}
