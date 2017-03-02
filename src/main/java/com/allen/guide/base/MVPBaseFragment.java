package com.allen.guide.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.allen.guide.net.VolleyManager;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        VolleyManager.getInstance(getActivity()).cancel(this);
    }

    protected abstract T initPresenter();

    protected abstract void initData();
}
