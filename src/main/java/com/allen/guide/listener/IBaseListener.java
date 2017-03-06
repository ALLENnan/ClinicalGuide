package com.allen.guide.listener;

public interface IBaseListener {
    
    void onSuccess(String msg);
    
    void onFail(String msg);

    void onError(String msg);
}
