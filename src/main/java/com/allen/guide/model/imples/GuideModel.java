package com.allen.guide.model.imples;

import android.content.Context;
import android.util.Log;

import com.allen.guide.App;
import com.allen.guide.config.Constants;
import com.allen.guide.config.URLs;
import com.allen.guide.listener.IBaseListener;
import com.allen.guide.listener.IGuideListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.entities.JGuide;
import com.allen.guide.model.entities.JResult;
import com.allen.guide.model.interfaces.IGuideModel;
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
    public void doDownload(GuideBean guideBean) {

    }

    @Override
    public void doCollect(GuideBean guideBean, final IBaseListener baseListener) {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.USER_ID, UserUtil.getCurrentUser(App.getContext()).getId() + "");
        params.put(Constants.GUIDE_ID, guideBean.getId() + "");
        params.put(Constants.GUIDE_ID, guideBean.getId() + "");

        mBuilder.setUrl(URLs.COLLECT)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JResult.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("Allen-----", "LoginModel->onResponse: " + response);
                        if (((JResult) response).getSucceed()) {
                            baseListener.onSuccess("收藏成功");
                        } else {
                            baseListener.onFail("收藏失败");
                        }
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        baseListener.onError("网络错误");
                        Log.d("Allen-----", "HomeModel->onErrorResponse: ");
                    }
                });
        add(mBuilder);
    }
}
