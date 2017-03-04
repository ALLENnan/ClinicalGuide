package com.allen.guide.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.base.MVPBaseActivity;
import com.allen.guide.presenter.imples.LoginPresenter;
import com.allen.guide.ui.interfaces.ILoginView;
import com.allen.guide.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.allen.guide.R.id.right_tv;

/**
 * @author Allen
 * @brief 登录界面
 * @date 17/3/2
 */
public class LoginActivity extends MVPBaseActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(right_tv)
    TextView mRightTv;
    @BindView(R.id.etPhoneNum)
    EditText mEtPhoneNum;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnLogin)
    TextView mBtnLogin;
    @BindView(R.id.login_progressBar)
    ProgressBar mLoginProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    private void initView() {
        mTitleTv.setText(R.string.text_login);
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText(R.string.text_register);
    }

    @OnClick({R.id.back_btn, R.id.tvForget, R.id.btnLogin, right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.tvForget:
//                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btnLogin:
                mPresenter.doLogin(mEtPhoneNum.getText().toString(), mEtPassword.getText().toString());
                break;
            case right_tv:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void setPhoneNumError(String msg) {
        mEtPhoneNum.setError(msg);
    }

    @Override
    public void setPasswordError(String msg) {
        mEtPassword.setError(msg);
    }

    @Override
    public void setNetVerifyError(String msg) {
        ToastUtils.showMessage(this, msg);
    }

    @Override
    public void navigateToMe() {
        finish();
    }

    @Override
    public void showLoading() {
        mLoginProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoginProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String msg) {
    }

    @Override
    public void showToast(String msg) {
    }
}
