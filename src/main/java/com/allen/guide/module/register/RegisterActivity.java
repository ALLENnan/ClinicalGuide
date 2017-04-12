package com.allen.guide.module.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.base.MVPBaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends MVPBaseActivity<IRegisterView, RegisterPresenter> implements IRegisterView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.etPhoneNum)
    EditText mEtPhoneNum;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.register_progressBar)
    ProgressBar mRegisterProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    private void initView() {
        mTitleTv.setText(R.string.text_register);
    }


    @Override
    public void showLoading() {
        mRegisterProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mRegisterProgressBar.setVisibility(View.GONE);
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

    @OnClick({R.id.back_btn, R.id.btnRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.btnRegister:
                if (mPresenter.isInputLegal(mEtPhoneNum.getText().toString(), mEtPassword.getText().toString())) {
                    Intent intent = new Intent(this, VerifyActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.PARAM_PHONE, mEtPhoneNum.getText().toString());
                    bundle.putString(Constants.PARAM_PASSWORD, mEtPassword.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
