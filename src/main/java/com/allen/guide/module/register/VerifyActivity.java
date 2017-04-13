package com.allen.guide.module.register;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.guide.R;
import com.allen.guide.base.MVPBaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class VerifyActivity extends MVPBaseActivity<IRegisterView, RegisterPresenter> implements IRegisterView {

    private static final int SMSDDK_HANDLER = 1;//短信回调

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.verify_et)
    EditText mVerifyEt;
    @BindView(R.id.verify_progressBar)
    ProgressBar mVerifyProgressBar;
    @BindView(R.id.phone_num_tv)
    TextView mPhoneNumTv;

    private String mPhoneNum;
    private String mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        initSMSSDK();
        SendSMS();
    }

    private void getIntentData() {
        mPhoneNum = getIntent().getStringExtra(Constants.PARAM_PHONE);
        mPassword = getIntent().getStringExtra(Constants.PARAM_PASSWORD);
    }

    private void initSMSSDK() {
        SMSSDK.initSDK(this, Constants.VERIFY_KEY, Constants.VERIFY_SECRET);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = SMSDDK_HANDLER;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void SendSMS() {
        SMSSDK.getVerificationCode("86", mPhoneNum);
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    private void initView() {
        mTitleTv.setText("填写验证码");
        mPhoneNumTv.setText("+86 " + mPhoneNum);
    }


    @Override
    public void showLoading() {
        mVerifyProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mVerifyProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        ToastUtils.showMessage(this, msg);
    }

    @Override
    public void showToast(String msg) {
    }

    @Override
    public void setSuccess(String msg) {
        ToastUtils.showMessage(this, msg);
        finish();
    }

    @Override
    public void setFail(String msg) {
        ToastUtils.showMessage(this, msg);
    }

    @OnClick({R.id.back_btn, R.id.next_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.next_btn:
//                mPresenter.doRegister(mEtPhoneNum.getText().toString(), mEtPassword.getText().toString());
                SMSSDK.submitVerificationCode("86", mPhoneNum, mVerifyEt.getText().toString());//对验证码进行验证
                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SMSDDK_HANDLER:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    //回调完成
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //验证码验证成功
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            Toast.makeText(getApplicationContext(), "验证成功",
                                    Toast.LENGTH_SHORT).show();
                            mPresenter.doRegister(mPhoneNum, mPassword);
                        }
                        //已发送验证码 
                        else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            Toast.makeText(getApplicationContext(), "验证码已经发送",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            ((Throwable) data).printStackTrace();
                        }
                    }
                    if (result == SMSSDK.RESULT_ERROR) {
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(getApplicationContext(), des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            //do something
                        }
                    }
                    break;
            }
        }
    };
}
