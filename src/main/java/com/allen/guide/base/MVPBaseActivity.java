package com.allen.guide.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Allen
 * @brief MVP父类Activity，子类Activity需要复写抽象方法初始化Presenter
 * @date 17/3/1
 */
public abstract class MVPBaseActivity<V extends IBaseView, T extends BasePresenter<V>> extends Activity {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mPresenter = initPresenter();
        mPresenter.attachView((V) this);
        
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T initPresenter();

    protected abstract void initView();
}
