package com.allen.guide.module.comment;

import com.allen.guide.base.BasePresenter;
import com.allen.guide.model.entities.CommentBean;
import com.allen.guide.model.imples.GuideModel;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.module.listener.ICommentListener;

import java.util.List;

/**
 * @author Allen
 * @brief
 * @date 17/3/1
 */

public class CommentPresenter extends BasePresenter<ICommentView> implements ICommentPresenter, ICommentListener {
    private IGuideModel mGuideModel;

    public CommentPresenter() {
        mGuideModel = GuideModel.getInstance();
    }

    @Override
    public void onSuccess(String msg) {
        getView().hideLoading();
        getView().showToast(msg);
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
    public void getComment(int guideId) {
        mGuideModel.getComment(guideId, this);
    }

    @Override
    public void addComment(int guideId, int userId, String content) {
        mGuideModel.addComment(guideId, userId, content, this);
    }

    @Override
    public void onSuccess(List<CommentBean> commentBeanList) {
        getView().hideLoading();
        getView().updataComment(commentBeanList);
    }
}
