package com.allen.guide.module.comment;

import com.allen.guide.base.IBaseView;
import com.allen.guide.model.entities.CommentBean;

import java.util.List;

public interface ICommentView extends IBaseView {

    void updataComment(List<CommentBean> commentBeanList);
}
