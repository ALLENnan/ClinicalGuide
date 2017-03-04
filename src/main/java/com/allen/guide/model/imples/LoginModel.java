package com.allen.guide.model.imples;

import android.text.TextUtils;
import android.util.Log;

import com.allen.guide.App;
import com.allen.guide.R;
import com.allen.guide.config.URLs;
import com.allen.guide.listener.ILoginListener;
import com.allen.guide.model.entities.JLogin;
import com.allen.guide.model.interfaces.ILoginModel;
import com.allen.guide.net.VolleyManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class LoginModel implements ILoginModel {

    public LoginModel() {
    }

    @Override
    public void doLogin(String phoneNum, String password, final ILoginListener loginListener) {
        if (TextUtils.isEmpty(phoneNum)) {
            loginListener.onPhoneNumError(App.getContext().getString(R.string.msg_phone_blank));
            return;
        }
        if (phoneNum.length() != 11) {
            loginListener.onPhoneNumError(App.getContext().getString(R.string.msg_phone_error));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginListener.onPhoneNumError(App.getContext().getString(R.string.msg_password_blank));
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("phoneNum", phoneNum);
        params.put("password", password);

        VolleyManager.RequestBuilder requestBuilder = new VolleyManager.RequestBuilder();
        requestBuilder.setUrl(URLs.LOGIN)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JLogin.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("Allen-----", "LoginModel->onResponse: " + response);
                        if (((JLogin) response).isUserExist()) {
                            loginListener.onSuccess();
                        } else {
                            loginListener.onNetVerifyError("手机号或者密码错误");
                        }
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loginListener.onNetVerifyError("网络错误");
                        Log.d("Allen-----", "HomeModel->onErrorResponse: ");
                    }
                });
        VolleyManager.getInstance(App.getContext()).add(requestBuilder);
    }
}
