package com.allen.guide.model.interfaces;

import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.module.listener.ICategoryListener;
import com.allen.guide.module.listener.ICollectListener;
import com.allen.guide.module.listener.ICommentListener;
import com.allen.guide.module.listener.IDownLoadListener;
import com.allen.guide.module.listener.IFavourListener;
import com.allen.guide.module.listener.IGuideListener;
import com.allen.guide.module.listener.IWordListener;

public interface IGuideModel {
    /**
     * 获取所有指南
     *
     * @param guideListener
     */
    void getGuideList(IGuideListener guideListener);

    void getCollectGuide(IGuideListener guideListener);

    void doDownload(GuideBean guideBean, IDownLoadListener downLoadListener);

    void doCollect(GuideBean guideBean, ICollectListener collectListener);

    void isUserCollect(GuideBean guideBean, ICollectListener collectListener);

    /**
     * 获取评论
     *
     * @param guideId         指南id
     * @param commentListener
     */
    void getComment(int guideId, ICommentListener commentListener);

    /**
     * @param guideId         指南id
     * @param userId          用户id
     * @param content         评论内容
     * @param commentListener
     */
    void addComment(int guideId, int userId, String content, ICommentListener commentListener);

    /**
     * 检索
     *
     * @param field 检索对应字段
     * @param query 检索语句
     */
    void retrieveGuides(String field, String query, IGuideListener guideListener);

    /**
     * 获取检索的关键词
     *
     * @param wordListener
     */
    void getKeyWords(IWordListener wordListener);

    /**
     * 点赞或取消赞
     *
     * @param favourListener
     */
    void updateFavour(GuideBean guideBean, IFavourListener favourListener);

    /**
     * 是否用户点赞
     *
     * @param guideBean
     * @param favourListener
     */
    void isUserFavour(GuideBean guideBean, IFavourListener favourListener);

    /**
     * 获取指南类别表
     */
    void getCategory(ICategoryListener listener);

    /**
     * 获取某类别下的指南
     *
     * @param category
     * @param guideListener
     */
    void getGuideList(String category, IGuideListener guideListener);
}
