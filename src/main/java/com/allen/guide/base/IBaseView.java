package com.allen.guide.base;

/**
 * @author Allen
 * @brief view基层接口, 所有的view接口需要继承IBaseView
 * @date 17/3/1
 */
public interface IBaseView {

    void showLoading();

    void hideLoading();

    void showError(String msg);

    void showToast(String msg);
}
