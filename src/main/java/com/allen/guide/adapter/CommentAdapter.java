package com.allen.guide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.guide.R;
import com.allen.guide.model.entities.CommentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<CommentBean> mCommentBeanList;

    public CommentAdapter(Context context, List<CommentBean> commentBeanList) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        mCommentBeanList = commentBeanList;
    }

    @Override
    public int getCount() {
        return mCommentBeanList.size();
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
            convertView = mInflater.inflate(R.layout.list_item_comment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mContentTv.setText(mCommentBeanList.get(position).getContent());
        holder.mDateTv.setText(mCommentBeanList.get(position).getDate());
        
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.content_tv)
        TextView mContentTv;
        @BindView(R.id.date_tv)
        TextView mDateTv;
        @BindView(R.id.container)
        LinearLayout mContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}