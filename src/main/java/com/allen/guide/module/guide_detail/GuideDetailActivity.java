package com.allen.guide.module.guide_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.base.MVPBaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.model.entities.GuideBean;
import com.allen.guide.module.PdfActivity;
import com.allen.guide.module.comment.CommentActivity;
import com.allen.guide.utils.BaseUtil;
import com.allen.guide.utils.IntentUtil;
import com.allen.guide.utils.ToastUtils;
import com.wx.goodview.GoodView;

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
    @BindView(R.id.btn_favour)
    TextView mBtnFavour;
    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.favour_iv)
    ImageView mFavourIv;
    @BindView(R.id.btn_comment)
    TextView mBtnComment;
    @BindView(R.id.collect_iv)
    ImageView mCollectIv;
    @BindView(R.id.btn_collect)
    TextView mBtnCollect;
    private GuideBean mGuideBean;
    private GoodView mFavourView;
    private GoodView mCollectView;


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
        mFavourView = new GoodView(this);
        mCollectView = new GoodView(this);

        mTitleTv.setText("指南详细");
        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText("分享");
        mGuideTitleTv.setText(mGuideBean.getTitle());
        mAuthorTv.setText(mGuideBean.getAuthor());
        mSourceTv.setText(mGuideBean.getSource());
        mDescTv.setText(mGuideBean.getAbstract_cn());

        //判断tab状态
        mPresenter.isUserFavour(mGuideBean);
        mPresenter.isUserCollect(mGuideBean);
        mPresenter.isDownload(mGuideBean);
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


    @Override
    public void initDownLoadUI(Boolean isDownLoad) {
        if (isDownLoad) {
            mBtnDownload.setText("打开");
        }
    }

    @Override
    public void setDownLoadSuccess(String msg) {
        mBtnDownload.setText("打开");
        ToastUtils.showMessage(this, msg);
        //重命名文件
        File oldFile = new File(Constants.DIR_PATH + mGuideBean.getFile());
        File newFile = new File(Constants.DIR_PATH + mGuideBean.getTitle() + ".pdf");
        oldFile.renameTo(newFile);
    }

    @Override
    public void initFavourUI(Boolean isFavour) {
        if (isFavour) {
            mFavourIv.setImageResource(R.mipmap.good_checked);
        } else {
            mFavourIv.setImageResource(R.mipmap.good);
        }
    }

    @Override
    public void updateFavourUI(Boolean isFavour) {
        if (isFavour) {
            mFavourIv.setImageResource(R.mipmap.good_checked);
            if (mFavourView != null) {
                mFavourView.setText("+1");
                mFavourView.show(mFavourIv);
            }
        } else {
            mFavourIv.setImageResource(R.mipmap.good);
        }
    }

    @Override
    public void initCollectUI(Boolean isCollect) {
        if (isCollect) {
            mCollectIv.setImageResource(R.mipmap.collection_checked);
        } else {
            mCollectIv.setImageResource(R.mipmap.collection);
        }
    }

    @Override
    public void updateCollectUI(Boolean isCollect) {
        if (isCollect) {
            mCollectIv.setImageResource(R.mipmap.collection_checked);
            if (mCollectView != null) {
                mCollectView.show(mCollectIv);
            }
        } else {
            mCollectIv.setImageResource(R.mipmap.collection);
        }
    }

    @OnClick({R.id.back_btn, R.id.right_tv, R.id.favour_layout, R.id.comment_layout, R.id.collect_layout, R.id.download_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.right_tv:
                String str = "临床指南：" + "\n《" + mGuideBean.getTitle() + "》\n" + mGuideBean.getAuthor() + "\n"
                        + mGuideBean.getSource() + "\n\n" + mGuideBean.getAbstract_cn();
                BaseUtil.shareText(this, str);
                break;
            case R.id.favour_layout:
                mPresenter.doFavour(mGuideBean);
                break;
            case R.id.comment_layout:
                IntentUtil.startActivity(this, CommentActivity.class, Constants.GUIDE_ID, mGuideBean.getId());
                break;
            case R.id.collect_layout:
                mPresenter.doCollect(mGuideBean);
                break;
            case R.id.download_layout:
                if (mBtnDownload.getText().toString().equals("下载")) {
                    mPresenter.doDownload(mGuideBean);
                } else {
                    IntentUtil.startActivity(this, PdfActivity.class, Constants.GUIDE_TITLE, mGuideBean.getTitle());
                }
                break;
        }
    }
}
