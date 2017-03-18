package com.allen.guide.module.listener;

public interface ICollectListener {
    void onSuccess(Boolean isCollect, String msg);

    void onFail(String msg);
}
