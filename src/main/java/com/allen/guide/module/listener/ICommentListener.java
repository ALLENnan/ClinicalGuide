package com.allen.guide.module.listener;

import com.allen.guide.model.entities.CommentBean;

import java.util.List;

public interface ICommentListener extends IBaseListener {

    void onSuccess(List<CommentBean> commentBeanList);
}
