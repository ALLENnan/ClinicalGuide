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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.title_tv)
    TextView mTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitleTv.setText("设置");
    }

    @OnClick({R.id.back_btn, R.id.feedback_linear, R.id.about_linear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.feedback_linear:
                showInputDialog();
                break;
            case R.id.about_linear:
                showMsgDialog();
                break;
        }
    }

    private void showInputDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_et, null);
        final EditText editText = (EditText) dialogView.findViewById(R.id.edit);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("意见反馈").setView(dialogView);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = editText.getText().toString();
                        if (!TextUtils.isEmpty(str)) {
                            //TODO 
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

    private void showMsgDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_tv, null);
        final TextView textView = (TextView) dialogView.findViewById(R.id.text);
        textView.setText(R.string.msg_about);
        
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("关于我们").setView(dialogView);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}