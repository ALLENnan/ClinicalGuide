package com.allen.guide.model.interfaces;

import com.allen.guide.module.listener.ISlideListener;

import java.util.Set;

public interface ICommonModel {
    Set<String> getHistory();

    void saveHistory(Set<String> strings);

    void clearHistory();
    
    void getSlide(ISlideListener slideListener);
}
