package com.allen.guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.config.Constants;
import com.allen.guide.utils.FileUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mFileNameList;

    public RecordAdapter(Context context, List<String> fileNameList) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mFileNameList = fileNameList;
    }

    @Override
    public int getCount() {
        return mFileNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_home, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mFileNameList.get(position));
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Constants.DIR_PATH + mFileNameList.get(position));
                FileUtil.openPDF(mContext, file);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.source)
        TextView mSource;
        @BindView(R.id.container)
        LinearLayout mContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}