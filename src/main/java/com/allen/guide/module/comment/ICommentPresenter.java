package com.allen.guide.module.comment;

public interface ICommentPresenter {

    /**
     * 获取评论
     *
     * @param guideId 指南id
     */
    void getComment(int guideId);

    /**
     * 添加评论
     *
     * @param guideId 指南id
     * @param userId  用户id
     * @param content 评论内容
     */
    void addComment(int guideId, int userId, String content);
}
