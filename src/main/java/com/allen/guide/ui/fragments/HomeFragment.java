package com.allen.guide.ui.fragments;

import android.view.View;

import com.allen.guide.base.MVPBaseFragment;
import com.allen.guide.presenter.imples.HomePresenter;
import com.allen.guide.ui.interfaces.IHomeView;

/**
 * @author Allen
 * @brief 首页
 * @date 17/2/28
 */
public class HomeFragment extends MVPBaseFragment<IHomeView, HomePresenter> {

    @Override
    protected HomePresenter initPresenter() {
        return null;
    }

    @Override
    protected View initView() {
        return null;
    }
}
