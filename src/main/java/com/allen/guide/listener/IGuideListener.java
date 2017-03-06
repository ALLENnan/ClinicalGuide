package com.allen.guide.listener;

import com.allen.guide.model.entities.GuideBean;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface IGuideListener extends IBaseListener{

    void onSuccess(List<GuideBean> guideList);
}
