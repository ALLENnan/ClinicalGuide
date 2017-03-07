package com.allen.guide.module.comment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.adapter.CommentAdapter;
import com.allen.guide.base.MVPBaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.model.entities.CommentBean;
import com.allen.guide.utils.ToastUtils;
import com.allen.guide.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends MVPBaseActivity<ICommentView, CommentPresenter> implements ICommentView {
    @BindView(R.id.back_btn)
    ImageButton mBackBtn;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.comment_listView)
    ListView mCommentListView;
    @BindView(R.id.content_et)
    EditText mContentEt;
    private List<CommentBean> mCommentList;
    private CommentAdapter mAdapter;
    private int mGuideId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        initNetData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mGuideId = (int) intent.getSerializableExtra(Constants.GUIDE_ID);
    }

    protected void initView() {
        mTitleTv.setText("评论");
        mCommentList = new ArrayList<>();
        mAdapter = new CommentAdapter(this, mCommentList);
        mCommentListView.setAdapter(mAdapter);
    }

    private void initNetData() {
        mPresenter.getComment(mGuideId);
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
    public void updataComment(List<CommentBean> commentBeanList) {
        mCommentList.clear();
        mCommentList.addAll(commentBeanList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected CommentPresenter initPresenter() {
        return new CommentPresenter();
    }

    @OnClick({R.id.back_btn, R.id.send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.send_btn:
                int userId = UserUtil.getCurrentUser(this).getId();
                mPresenter.addComment(mGuideId, userId, mContentEt.getText().toString());
                mContentEt.setText("");
                break;
        }
    }
}
