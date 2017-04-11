package com.allen.guide.module.me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.base.BaseActivity;
import com.allen.guide.model.entities.UserBean;
import com.allen.guide.model.imples.UserModel;
import com.allen.guide.model.interfaces.IUserModel;
import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.utils.ToastUtils;
import com.allen.guide.utils.UserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.nickname_tv)
    TextView mNicknameTv;
    @BindView(R.id.phone_num_tv)
    TextView mPhoneNumTv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    private UserBean mUserBean;
    private IUserModel mUserModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        mUserModel = UserModel.getInstance();
        initView();
    }

    private void initView() {
        mTitleTv.setText("个人信息");
        mUserBean = UserUtil.getCurrentUser(this);
        mNicknameTv.setText(mUserBean.getUsername());
        mPhoneNumTv.setText(mUserBean.getPhone_num());
    }


    private void showInputDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_et, null);
        final EditText editText = (EditText) dialogView.findViewById(R.id.edit);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改昵称").setView(dialogView);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = editText.getText().toString();
                        if (!TextUtils.isEmpty(str)) {
                            mNicknameTv.setText(str);
                            mUserBean.setUsername(str);
                            UserUtil.saveCurrentUser(getApplicationContext(), mUserBean);
                            mUserModel.modifyUserInfo(mUserBean.getId(), str, new ModifyListener());
                        }
                    }
                })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .show();
    }

    @OnClick({R.id.back_btn, R.id.nickname_linear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.nickname_linear:
                showInputDialog();
                break;
        }
    }

    class ModifyListener implements IBaseListener {

        @Override
        public void onSuccess(String msg) {
            ToastUtils.showMessage(getApplicationContext(), msg);
        }

        @Override
        public void onFail(String msg) {

        }

        @Override
        public void onError(String msg) {
            ToastUtils.showMessage(getApplicationContext(), msg);

        }
    }
}
