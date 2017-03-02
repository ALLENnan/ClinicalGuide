package com.allen.guide.model.imples;

import android.util.Log;

import com.allen.guide.App;
import com.allen.guide.config.URLs;
import com.allen.guide.listener.IGuideListener;
import com.allen.guide.model.entities.JFile;
import com.allen.guide.model.interfaces.IHomeModel;
import com.allen.guide.net.VolleyManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class HomeModel implements IHomeModel {

    public HomeModel() {
    }

    @Override
    public void getNetData(final IGuideListener guideListener) {
        VolleyManager.RequestBuilder requestBuilder = new VolleyManager.RequestBuilder();
        requestBuilder.setUrl(URLs.index)
                .setMethod(Request.Method.GET)
                .setClazz(JFile.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("Allen-----", "HomeModel->onResponse: " + ((JFile) response).toString());
                        guideListener.onSuccess(((JFile) response).getRows());
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
        VolleyManager.getInstance(App.getContext()).add(requestBuilder);
    }
}
