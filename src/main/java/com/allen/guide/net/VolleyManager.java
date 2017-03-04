package com.allen.guide.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.OkHttpClient;

public class VolleyManager {
    private static volatile VolleyManager sVolleyManager;
    private RequestQueue mRequestQueue;
    private Context mContext;

    private VolleyManager(Context context) {
        mContext = context.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(mContext, new OkHttp3Stack(new OkHttpClient()));
    }

    public static VolleyManager getInstance(Context context) {
        if (sVolleyManager == null) {
            synchronized (VolleyManager.class) {
                if (sVolleyManager == null) {
                    sVolleyManager = new VolleyManager(context);
                }
            }
        }
        return sVolleyManager;
    }

    public static class RequestBuilder<T> {

        private Object tag;
        private String url;
        private int method;
        private Response.ErrorListener errorListener;
        private Class<T> clazz;
        private Response.Listener<T> listener;
        private Map<String, String> params;
        private JSONObject jsonObject;

        public Map<String, String> getParams() {
            return params;
        }

        public RequestBuilder setParams(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public JSONObject getJsonObject() {
            return jsonObject;
        }

        public void setJsonObject(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        public Object getTag() {
            return tag;
        }

        public RequestBuilder setTag(Object tag) {
            this.tag = tag;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public RequestBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public int getMethod() {
            return method;
        }

        public RequestBuilder setMethod(int method) {
            this.method = method;
            return this;
        }

        public Class<T> getClazz() {
            return clazz;
        }

        public RequestBuilder setClazz(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        public Response.Listener<T> getListener() {
            return listener;
        }

        public RequestBuilder setListener(Response.Listener<T> listener) {
            this.listener = listener;
            return this;
        }

        public Response.ErrorListener getErrorListener() {
            return errorListener;
        }

        public RequestBuilder setErrorListener(Response.ErrorListener errorListener) {
            this.errorListener = errorListener;
            return this;
        }
    }

    public void add(RequestBuilder requestBuilder) {
        GsonRequest request = null;
        if (requestBuilder.getMethod() == Request.Method.GET) {
            request = new GsonRequest(requestBuilder.getUrl(), requestBuilder.getClazz(),
                    requestBuilder.getListener(), requestBuilder.getErrorListener());
        } else if (requestBuilder.getMethod() == Request.Method.POST) {
            request = new GsonRequest(Request.Method.POST, requestBuilder.getParams(), requestBuilder.getUrl(),
                    requestBuilder.getClazz(), requestBuilder.getListener(), requestBuilder.getErrorListener());
        }
        if (request != null) {
            request.setTag(requestBuilder.getTag());
            mRequestQueue.add(request);//添加请求到队列
        }
    }

    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
