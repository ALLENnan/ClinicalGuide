package com.allen.guide.model.imples;

import android.content.Context;

import com.allen.guide.App;
import com.allen.guide.config.Constants;
import com.allen.guide.config.URLs;
import com.allen.guide.model.interfaces.ICommonModel;
import com.allen.guide.model.port.JSlide;
import com.allen.guide.module.listener.ISlideListener;
import com.allen.guide.net.VolleyManager;
import com.allen.guide.utils.SharedPreferencesUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Set;

public class CommonModel implements ICommonModel {
    private Context mContext;
    private static volatile CommonModel sGuideModel;
    private VolleyManager.RequestBuilder mBuilder;

    private CommonModel() {
        mContext = App.getContext();
        mBuilder = new VolleyManager.RequestBuilder();

    }

    public static CommonModel getInstance() {
        if (sGuideModel == null) {
            synchronized (CommonModel.class) {
                if (sGuideModel == null) {
                    sGuideModel = new CommonModel();
                }
            }
        }
        return sGuideModel;
    }

    private void add(VolleyManager.RequestBuilder builder) {
        VolleyManager.getInstance(App.getContext()).add(mBuilder);
    }


    @Override
    public Set<String> getHistory() {
        return SharedPreferencesUtil.getDataSet(mContext, Constants.KEY_HISTORY);
    }

    @Override
    public void saveHistory(Set<String> strings) {
        SharedPreferencesUtil.putDataSet(mContext, Constants.KEY_HISTORY, strings);
    }

    @Override
    public void clearHistory() {
        SharedPreferencesUtil.clear(mContext);
    }

    @Override
    public void getSlide(final ISlideListener slideListener) {
        mBuilder.setUrl(URLs.SLIDE)
                .setMethod(Request.Method.GET)
                .setClazz(JSlide.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        slideListener.onSuccess(((JSlide) response).getRows());
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        slideListener.onFail("网络错误");
                    }
                });
        add(mBuilder);
    }
}
