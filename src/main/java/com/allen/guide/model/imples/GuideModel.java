package com.allen.guide.model.imples;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.allen.guide.App;
import com.allen.guide.config.Constants;
import com.allen.guide.config.URLs;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.interfaces.IGuideModel;
import com.allen.guide.model.port.JCategory;
import com.allen.guide.model.port.JGuide;
import com.allen.guide.model.port.JResult;
import com.allen.guide.model.port.JWord;
import com.allen.guide.model.port.Jcomment;
import com.allen.guide.module.listener.ICategoryListener;
import com.allen.guide.module.listener.ICollectListener;
import com.allen.guide.module.listener.ICommentListener;
import com.allen.guide.module.listener.IDownLoadListener;
import com.allen.guide.module.listener.IFavourListener;
import com.allen.guide.module.listener.IGuideListener;
import com.allen.guide.module.listener.IWordListener;
import com.allen.guide.net.DownloadUtil;
import com.allen.guide.net.VolleyManager;
import com.allen.guide.utils.UserUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class GuideModel implements IGuideModel {
    private Context mContext;
    private static volatile GuideModel sGuideModel;
    private VolleyManager.RequestBuilder mBuilder;

    private GuideModel() {
        mContext = App.getContext();
        mBuilder = new VolleyManager.RequestBuilder();

    }

    public static GuideModel getInstance() {
        if (sGuideModel == null) {
            synchronized (GuideModel.class) {
                if (sGuideModel == null) {
                    sGuideModel = new GuideModel();
                }
            }
        }
        return sGuideModel;
    }

    private void add(VolleyManager.RequestBuilder builder) {
        VolleyManager.getInstance(App.getContext()).add(mBuilder);
    }

    @Override
    public void getGuideList(final IGuideListener guideListener) {
        mBuilder.setUrl(URLs.GUIDE)
                .setMethod(Request.Method.GET)
                .setClazz(JGuide.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("Allen-----", "HomeModel->onResponse: " + ((JGuide) response).toString());
                        guideListener.onSuccess(((JGuide) response).getRows());
                        Log.d("Allen-----", "HomeModel->onResponse: ");
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        guideListener.onError("网络错误");
                        Log.d("Allen-----", "HomeModel->onErrorResponse: ");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void getCollectGuide(final IGuideListener guideListener) {
        int userId = UserUtil.getCurrentUser(mContext).getId();
        String url = URLs.COLLECT + "?userId=" + userId;

        mBuilder.setUrl(url)
                .setMethod(Request.Method.GET)
                .setClazz(JGuide.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        guideListener.onSuccess(((JGuide) response).getRows());
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        guideListener.onError("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void doDownload(GuideBean guideBean, final IDownLoadListener downLoadListener) {

        String fileName = guideBean.getFile();
        if (fileName != null) {
            String url = URLs.GUIDE + "?fileName=" + fileName;
            Log.d("Allen-----", "GuideModel->doDownload: " + url);
            DownloadUtil downloadUtil = new DownloadUtil();
            downloadUtil.start(url, downLoadListener);
        } else {
            downLoadListener.onDownLoadFail("无此文件");
        }
    }

    @Override
    public void doCollect(GuideBean guideBean, final ICollectListener collectListener) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.USER_ID, UserUtil.getCurrentUser(App.getContext()).getId() + "");
        params.put(Constants.GUIDE_ID, guideBean.getId() + "");

        mBuilder.setUrl(URLs.COLLECT)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JResult.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        collectListener.onSuccess(((JResult) response).getSucceed(), null);
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        collectListener.onFail("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void isUserCollect(GuideBean guideBean, final ICollectListener collectListener) {
        int userId = UserUtil.getCurrentUser(mContext).getId();
        String url = URLs.COLLECT + "?userId=" + userId + "&guideId=" + guideBean.getId();

        mBuilder.setUrl(url)
                .setMethod(Request.Method.GET)
                .setClazz(JResult.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        collectListener.onSuccess(((JResult) response).getSucceed(), null);
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        collectListener.onFail("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void getComment(int guideId, final ICommentListener commentListener) {
        String url = URLs.COMMENT + "?guideId=" + guideId;

        mBuilder.setUrl(url)
                .setMethod(Request.Method.GET)
                .setClazz(Jcomment.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        commentListener.onSuccess(((Jcomment) response).getRows());
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        commentListener.onError("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void addComment(int guideId, int userId, String content, final ICommentListener commentListener) {

        if (TextUtils.isEmpty(content)) {
            commentListener.onFail("评论内容不为空");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put(Constants.GUIDE_ID, guideId + "");
        params.put(Constants.USER_ID, userId + "");
        params.put(Constants.COMMENT_CONTENT, content);

        mBuilder.setUrl(URLs.COMMENT)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(Jcomment.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        commentListener.onSuccess(((Jcomment) response).getRows());
                        commentListener.onSuccess("评论成功");
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        commentListener.onError("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void retrieveGuides(String field, String query, final IGuideListener guideListener) {
        if (TextUtils.isEmpty(query)) {
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put(Constants.FIELD, field);
        params.put(Constants.QUERY, query);

        mBuilder.setUrl(URLs.RETRIEVE)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JGuide.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        guideListener.onSuccess(((JGuide) response).getRows());
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        guideListener.onError("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void getKeyWords(final IWordListener wordListener) {
        mBuilder.setUrl(URLs.RETRIEVE)
                .setMethod(Request.Method.GET)
                .setClazz(JWord.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        wordListener.onSuccess(((JWord) response).getRows());
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        wordListener.onError("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void updateFavour(GuideBean guideBean, final IFavourListener favourListener) {

        Map<String, String> params = new HashMap<>();
        params.put(Constants.USER_ID, UserUtil.getCurrentUser(App.getContext()).getId() + "");
        params.put(Constants.GUIDE_ID, guideBean.getId() + "");

        mBuilder.setUrl(URLs.FAVOUR)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JResult.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        favourListener.onSuccess(((JResult) response).getSucceed(), null);
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        favourListener.onFail("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void isUserFavour(GuideBean guideBean, final IFavourListener favourListener) {

        int userId = UserUtil.getCurrentUser(mContext).getId();
        String url = URLs.FAVOUR + "?userId=" + userId + "&guideId=" + guideBean.getId();

        mBuilder.setUrl(url)
                .setMethod(Request.Method.GET)
                .setClazz(JResult.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        favourListener.onSuccess(((JResult) response).getSucceed(), null);
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        favourListener.onFail("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void getCategory(final ICategoryListener listener) {
        mBuilder.setUrl(URLs.CATEGORY)
                .setMethod(Request.Method.GET)
                .setClazz(JCategory.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        listener.onSuccess(((JCategory) response).getRows());
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError("网络错误");
                    }
                });
        add(mBuilder);
    }

    @Override
    public void getGuideList(String category, final IGuideListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.PARAM_CATEGORY, category);

        mBuilder.setUrl(URLs.CATEGORY)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JGuide.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        listener.onSuccess(((JGuide) response).getRows());
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError("网络错误");
                    }
                });
        add(mBuilder);
    }
}
