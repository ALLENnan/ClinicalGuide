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
import com.allen.guide.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.allen.guide.R.id.detail_tv;

public class GuideDetailActivity extends MVPBaseActivity<IGuideDetailView, GuideDetailPresenter> implements IGuideDetailView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(detail_tv)
    TextView mDetailTv;
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
        mDetailTv.setText(mGuideBean.toString());
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

    @OnClick({R.id.back_btn, R.id.btn_share, R.id.btn_comment, R.id.btn_collect})
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
        }
    }
}
