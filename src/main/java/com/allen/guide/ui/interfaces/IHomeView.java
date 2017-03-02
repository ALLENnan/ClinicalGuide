package com.allen.guide.ui.interfaces;

import com.allen.guide.base.IBaseView;
import com.allen.guide.model.entities.JFile;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */
public interface IHomeView extends IBaseView {
    
    void updateGuideList(List<JFile.File> guideList);
}
