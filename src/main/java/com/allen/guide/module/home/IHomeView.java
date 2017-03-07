package com.allen.guide.module.home;

import com.allen.guide.base.IBaseView;
import com.allen.guide.model.entities.GuideBean;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface IHomeView extends IBaseView {
    
    void updateGuideList(List<GuideBean> guideList);
}
