package com.allen.guide.listener;

import com.allen.guide.model.entities.JGuide;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface IGuideListener {

    void onSuccess(List<JGuide.Guide> guideList);

    void onError(String msg);
}
