package com.allen.guide.model.imples;

import android.text.TextUtils;
import android.util.Log;

import com.allen.guide.App;
import com.allen.guide.R;
import com.allen.guide.config.URLs;
import com.allen.guide.listener.IBaseListener;
import com.allen.guide.model.entities.JRegister;
import com.allen.guide.model.interfaces.IRegisterModel;
import com.allen.guide.net.VolleyManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class RegisterModel implements IRegisterModel {

    public RegisterModel() {
    }

    @Override
    public void doRegister(String phoneNum, String password, final IBaseListener baseListener) {
        if (TextUtils.isEmpty(phoneNum)) {
            baseListener.onFail(App.getContext().getString(R.string.msg_phone_blank));
            return;
        }
        if (phoneNum.length() != 11) {
            baseListener.onFail(App.getContext().getString(R.string.msg_phone_error));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            baseListener.onFail(App.getContext().getString(R.string.msg_password_blank));
            return;
        }
        if (password.length() < 6) {
            baseListener.onFail(App.getContext().getString(R.string.msg_password_count));
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("phoneNum", phoneNum);
        params.put("password", password);

        VolleyManager.RequestBuilder requestBuilder = new VolleyManager.RequestBuilder();
        requestBuilder.setUrl(URLs.REGISTER)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JRegister.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("Allen-----", "LoginModel->onResponse: " + response);
                        if (((JRegister) response).isRegistered()) {
                            baseListener.onSuccess("注册成功");
                        } else {
                            baseListener.onFail("该手机号已经被注册");
                        }
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        baseListener.onError("网络错误，请重试");
                        Log.d("Allen-----", "HomeModel->onErrorResponse: ");
                    }
                });
        VolleyManager.getInstance(App.getContext()).add(requestBuilder);
    }
}
