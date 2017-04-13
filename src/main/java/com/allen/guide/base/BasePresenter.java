package com.allen.guide.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author Allen
 * @brief Presenter基类，提取公共逻辑，弱引用避免内存泄漏
 * @date 17/3/1
 */
public abstract class BasePresenter<V extends IBaseView> {
    private Reference<V> mViewReference;

    public void attachView(V view) {
        mViewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    protected V getView() {
        if (mViewReference != null) {
            return mViewReference.get();
        }else {
            return null;
        }
    }
}
