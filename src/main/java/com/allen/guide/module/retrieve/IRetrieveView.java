package com.allen.guide.module.retrieve;

import com.allen.guide.base.IBaseView;
import com.allen.guide.model.entities.GuideBean;

import java.util.List;

public interface IRetrieveView extends IBaseView {
    
    void updateGuideList(List<GuideBean> guideList);
}
