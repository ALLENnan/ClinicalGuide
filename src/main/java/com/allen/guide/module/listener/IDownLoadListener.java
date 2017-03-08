package com.allen.guide.module.listener;

public interface IDownLoadListener {
    
    void onDownLoadSuccess(String msg);

    void onDownLoadFail(String msg);
}
