package com.allen.guide.module.others;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.adapter.RecordAdapter;
import com.allen.guide.base.BaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.utils.FileUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.record_listView)
    ListView mRecordListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        mTitleTv.setText("下载记录");
    }

    private void initData() {
        List<String> list = FileUtil.getFileDir(Constants.DIR_PATH);
        RecordAdapter adapter = new RecordAdapter(this, list);
        mRecordListView.setAdapter(adapter);
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
