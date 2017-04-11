package com.allen.guide.model.imples;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.allen.guide.App;
import com.allen.guide.R;
import com.allen.guide.config.Constants;
import com.allen.guide.config.URLs;
import com.allen.guide.model.port.JResult;
import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.module.listener.ILoginListener;
import com.allen.guide.model.port.JLogin;
import com.allen.guide.model.port.JRegister;
import com.allen.guide.model.interfaces.IUserModel;
import com.allen.guide.net.VolleyManager;
import com.allen.guide.utils.UserUtil;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UserModel implements IUserModel {
    private Context mContext;
    private static volatile UserModel sUserModel;
    private VolleyManager.RequestBuilder mBuilder;

    private UserModel() {
        mContext = App.getContext();
        mBuilder = new VolleyManager.RequestBuilder();

    }

    public static UserModel getInstance() {
        if (sUserModel == null) {
            synchronized (GuideModel.class) {
                if (sUserModel == null) {
                    sUserModel = new UserModel();
                }
            }
        }
        return sUserModel;
    }

    private void add(VolleyManager.RequestBuilder builder) {
        VolleyManager.getInstance(App.getContext()).add(mBuilder);
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
            loginListener.onPasswordError(App.getContext().getString(R.string.msg_password_blank));
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("phoneNum", phoneNum);
        params.put("password", password);

        mBuilder.setUrl(URLs.LOGIN)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JLogin.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.d("Allen-----", "LoginModel->onResponse: " + response);
                        if (((JLogin) response).isUserExist()) {
                            loginListener.onSuccess("登录成功");
                            //保存当前用户到本地
                            String jsonStr = new Gson().toJson(((JLogin) response).getUser());
                            UserUtil.saveCurrentUser(App.getContext(), jsonStr);
                        } else {
                            loginListener.onFail("手机号或者密码错误");
                        }
                    }
                })
                .setErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loginListener.onError("网络错误");
                        Log.d("Allen-----", "HomeModel->onErrorResponse: ");
                    }
                });
        add(mBuilder);
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

        mBuilder.setUrl(URLs.REGISTER)
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
        add(mBuilder);
    }

    @Override
    public void modifyUserInfo(int id, String username, final IBaseListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put(Constants.PARAM_USERID, id+"");
        params.put(Constants.PARAM_USERNAME, username);

        mBuilder.setUrl(URLs.MODIFY)
                .setMethod(Request.Method.POST)
                .setParams(params)
                .setClazz(JResult.class)
                .setListener(new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        if (((JResult) response).getSucceed()) {
                            listener.onSuccess("修改成功");
                        }
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
