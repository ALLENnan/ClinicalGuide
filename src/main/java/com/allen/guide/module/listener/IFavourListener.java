package com.allen.guide.module.listener;

public interface IFavourListener {
    void onSuccess(Boolean isFavour, String msg);

    void onFail(String msg);
}
