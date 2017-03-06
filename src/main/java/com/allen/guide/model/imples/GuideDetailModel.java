package com.allen.guide.model.imples;

import android.util.Log;

import com.allen.guide.App;
import com.allen.guide.config.Constants;
import com.allen.guide.config.URLs;
import com.allen.guide.listener.IBaseListener;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.model.entities.JResult;
import com.allen.guide.model.interfaces.IGuideDetailModel;
import com.allen.guide.net.VolleyManager;
import com.allen.guide.utils.UserUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class GuideDetailModel implements IGuideDetailModel {

    public GuideDetailModel() {
    }

    @Override
    public void doDownload(GuideBean guideBean) {

    }

    @Override
    public void doCollect(GuideBean guideBean, final IBaseListener baseListener) {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.USER_ID, UserUtil.getCurrentUser(App.getContext()).getId() + "");
        params.put(Constants.GUIDE_ID, guideBean.getId() + "");

        VolleyManager.RequestBuilder requestBuilder = new VolleyManager.RequestBuilder();
        requestBuilder.setUrl(URLs.COLLECT)
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
        VolleyManager.getInstance(App.getContext()).add(requestBuilder);

    }
}
