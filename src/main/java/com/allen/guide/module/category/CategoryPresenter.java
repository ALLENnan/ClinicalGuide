package com.allen.guide.module.category;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.ICategoryListener;

import java.util.List;

public class CategoryPresenter extends BasePresenter<ICategoryView> implements ICategoryPresenter, ICategoryListener {
    private IGuideModel mGuideModel;

    public CategoryPresenter() {
        mGuideModel = GuideModel.getInstance();
    }


    @Override
    public void getCategory() {
        mGuideModel.getCategory(this);
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFail(String msg) {
    }

    @Override
    public void onError(String msg) {
        if (getView() != null) {
            getView().showError(msg);
        }
    }

    @Override
    public void onSuccess(List<String> categoryList) {
        if (getView() != null) {
            getView().updataCategory(categoryList);
        }
    }
}
