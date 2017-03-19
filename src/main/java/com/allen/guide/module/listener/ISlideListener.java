package com.allen.guide.module.listener;

import com.allen.guide.model.entities.SlideBean;

import java.util.List;

public interface ISlideListener{

    void onSuccess(List<SlideBean> slideBeans);
    
    void onFail(String msg);
}
