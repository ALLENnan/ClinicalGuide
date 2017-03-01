package com.allen.guide.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Allen
 * @brief MVP父类Fragment
 * @date 17/3/1
 */
public abstract class MVPBaseFragment<V extends IBaseView, T extends BasePresenter<V>> extends Fragment {

    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();
        mPresenter.attachView((V) this);

        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    protected abstract T initPresenter();

    protected abstract View initView();
}
