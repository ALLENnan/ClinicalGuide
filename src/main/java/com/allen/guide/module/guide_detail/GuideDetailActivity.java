package com.allen.guide.module.guide_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.base.MVPBaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.module.comment.CommentActivity;
import com.allen.guide.utils.FileUtil;
import com.allen.guide.utils.ToastUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideDetailActivity extends MVPBaseActivity<IGuideDetailView, GuideDetailPresenter> implements IGuideDetailView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.guide_title_tv)
    TextView mGuideTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.author_tv)
    TextView mAuthorTv;
    @BindView(R.id.source_tv)
    TextView mSourceTv;
    @BindView(R.id.desc_tv)
    TextView mDescTv;
    @BindView(R.id.btn_download)
    TextView mBtnDownload;
    private GuideBean mGuideBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        ButterKnife.bind(this);
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mGuideBean = (GuideBean) intent.getSerializableExtra(Constants.GUIDE_BEAN);
    }

    private void initView() {
        mTitleTv.setText("指南详细");
        mGuideTitleTv.setText(mGuideBean.getTitle());
        mAuthorTv.setText(mGuideBean.getAuthor());
        mSourceTv.setText(mGuideBean.getSource());
        mDescTv.setText(mGuideBean.getAbstract_cn());

        File file = new File(Constants.DIR_PATH + mGuideBean.getFile());
        if (file.exists()) {
            mBtnDownload.setText("打开");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
        ToastUtils.showMessage(this, msg);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showMessage(this, msg);
    }

    @Override
    protected GuideDetailPresenter initPresenter() {
        return new GuideDetailPresenter();
    }

    @OnClick({R.id.back_btn, R.id.btn_share, R.id.btn_comment, R.id.btn_collect, R.id.btn_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.btn_share:
                break;
            case R.id.btn_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.GUIDE_ID, mGuideBean.getId());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btn_collect:
                mPresenter.doCollect(mGuideBean);
                break;
            case R.id.btn_download:
                if (mBtnDownload.getText().toString().equals("下载")) {
                    mPresenter.doDownload(mGuideBean);
                } else {
                    File file = new File(Constants.DIR_PATH + mGuideBean.getFile());
                    FileUtil.openPDF(this, file);
                }
                break;
        }
    }

    @Override
    public void setDownLoadSuccess(String msg) {
        mBtnDownload.setText("打开");
        ToastUtils.showMessage(this, msg);
    }
}
