package com.allen.guide.module.others;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.adapter.RecordAdapter;
import com.allen.guide.base.BaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.utils.BaseUtil;
import com.allen.guide.utils.FileUtil;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.record_listView)
    SwipeMenuListView mRecordListView;
    private List<String> mFileNameList;
    private Context mContext;
    private RecordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        mTitleTv.setText("下载记录");
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                openItem.setWidth(BaseUtil.dp2px(getApplicationContext(), 80));
                openItem.setTitle("open");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(BaseUtil.dp2px(getApplicationContext(), 80));
                deleteItem.setTitle("delete");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        mRecordListView.setMenuCreator(creator);

        mRecordListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        File file = new File(Constants.DIR_PATH + mFileNameList.get(position));
                        FileUtil.openPDF(mContext, file);
                        break;
                    case 1:
                        // delete
                        FileUtil.delFile(Constants.DIR_PATH + mFileNameList.get(position));
                        initData();
                        break;
                }
                return false;
            }
        });
        mRecordListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        mRecordListView.setOpenInterpolator(new BounceInterpolator());
    }

    private void initData() {
        mFileNameList = FileUtil.getFileDir(Constants.DIR_PATH);
        mAdapter = new RecordAdapter(this, mFileNameList);
        mRecordListView.setAdapter(mAdapter);
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }


}
