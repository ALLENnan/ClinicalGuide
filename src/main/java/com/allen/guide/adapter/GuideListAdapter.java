package com.allen.guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.model.entities.GuideBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Allen
 * @brief 指南列表adapter
 * @date 17/3/2
 */
public class GuideListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<GuideBean> mGuideList;

    public GuideListAdapter(Context context, List<GuideBean> guideList) {
        this.mInflater = LayoutInflater.from(context);
        mGuideList = guideList;
    }

    @Override
    public int getCount() {
        return mGuideList.size();
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
            convertView = mInflater.inflate(R.layout.home_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mGuideList.get(position).getTitle());
        holder.mSource.setText(mGuideList.get(position).getSource());

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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