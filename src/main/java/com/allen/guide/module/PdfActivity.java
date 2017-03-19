package com.allen.guide.module;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.base.BaseActivity;
import com.allen.guide.config.Constants;
import com.allen.guide.view.pdfview.PDFViewPager;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PdfActivity extends BaseActivity {
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.right_tv)
    TextView mRightTv;
    @BindView(R.id.llContainer)
    LinearLayout mLlContainer;
    private String mGuideTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        openPDF();
    }

    private void initView() {
        mTitleTv.setText(mGuideTitle);
    }

    private void openPDF() {
        String filePath = Constants.DIR_PATH + mGuideTitle + ".pdf";
        File file = new File(filePath);
        if (file.exists()) {
            PDFViewPager pdfViewPager = new PDFViewPager(this, filePath);
            mLlContainer.addView(pdfViewPager);
        }
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mGuideTitle = (String) intent.getSerializableExtra(Constants.GUIDE_TITLE);
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }
}
