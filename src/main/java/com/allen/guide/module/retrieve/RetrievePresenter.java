package com.allen.guide.module.retrieve;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.imples.CommonModel;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.ICommonModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.IGuideListener;
import com.allen.guide.module.listener.IWordListener;

import java.util.List;
import java.util.Set;

public class RetrievePresenter extends BasePresenter<IRetrieveView> implements IRetrievePresenter, IGuideListener {
    private IGuideModel mGuideModel;
    private ICommonModel mCommonModel;

    public RetrievePresenter() {
        mGuideModel = GuideModel.getInstance();
        mCommonModel = CommonModel.getInstance();
    }

    @Override
    public void onSuccess(List<GuideBean> guideList) {
        getView().hideLoading();
        getView().updateGuideList(guideList);
    }

    @Override
    public void onSuccess(String msg) {
    }

    @Override
    public void onFail(String msg) {
        getView().hideLoading();
        getView().showToast(msg);
    }

    @Override
    public void onError(String msg) {
        getView().hideLoading();
        getView().showError(msg);
    }

    @Override
    public void retrieveGuiles(String field, String query) {
        getView().showLoading();
        mGuideModel.retrieveGuides(field, query, this);
    }

    @Override
    public void getKeyWords() {
        mGuideModel.getKeyWords(new IWordListener() {
            @Override
            public void onSuccess(List<String> wordList) {
                getView().setKeyWords(wordList);
            }

            @Override
            public void onSuccess(String msg) {

            }

            @Override
            public void onFail(String msg) {

            }

            @Override
            public void onError(String msg) {
                getView().showError(msg);
            }
        });
    }

    @Override
    public Set<String> getHistory() {
        return mCommonModel.getHistory();
    }

    @Override
    public void saveHistory(Set<String> strings) {
        mCommonModel.saveHistory(strings);
    }

    @Override
    public void clearHistory() {
        mCommonModel.clearHistory();
    }
}
