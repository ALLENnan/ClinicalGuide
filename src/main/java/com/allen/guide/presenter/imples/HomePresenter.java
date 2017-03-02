package com.allen.guide.presenter.imples;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.listener.IGuideListener;
import com.allen.guide.model.entities.JFile;
import com.allen.guide.model.imples.HomeModel;
import com.allen.guide.model.interfaces.IHomeModel;
import com.allen.guide.presenter.interfaces.IHomePresenter;
import com.allen.guide.ui.interfaces.IHomeView;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter, IGuideListener {
    private IHomeModel mHomeModel;

    public HomePresenter() {
        mHomeModel = new HomeModel();
    }

    @Override
    public void getNetGuile() {
        getView().showLoading();
        mHomeModel.getNetData(this);
    }

    @Override
    public void onSuccess(List<JFile.File> guideList) {
        getView().hideLoading();
        getView().updateGuideList(guideList);
    }

    @Override
    public void onError(String msg) {
        getView().hideLoading();
        getView().showErrorMsg(msg);
    }
}
