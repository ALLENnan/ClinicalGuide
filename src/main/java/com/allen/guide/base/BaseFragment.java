package com.allen.guide.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author Allen
 * @brief 父类Fragment
 * @date 17/3/1
 */
public abstract class BaseFragment extends Fragment {
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    
    protected abstract void initData();
}
