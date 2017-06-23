package com.example.administrator.qzsdevelopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.qzsdevelopapp.R;
import com.example.administrator.qzsdevelopapp.bean.SocietyBean;
import com.example.administrator.qzsdevelopapp.bean.SportBean;

import java.util.List;

/**
 *  //社会新闻
 */

public class RecycleSportAdapter extends RecyclerView.Adapter<RecycleSportAdapter.MyViewHolder> implements View.OnClickListener{
    private Context mContext;
    private List<SportBean.Result.listData> mDatas;
    //define interface
    private OnItemClickListener mOnItemClickListener = null;
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }


    public RecycleSportAdapter(Context context, List<SportBean.Result.listData> datas) {
        mDatas = datas;
        mContext=context;
    }
    @Override
    public RecycleSportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_sport, parent, false);
        RecycleSportAdapter.MyViewHolder holder = new RecycleSportAdapter.MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleSportAdapter.MyViewHolder holder, int position) {
        holder.tv_news_summary_digest.setText(mDatas.get(position).content+"");
        holder.tv_news_society_ptime.setText(mDatas.get(position).time+"");
        holder.tv_news_society_title.setText(mDatas.get(position).title+"");
        if (mDatas.get(position).pic!=null){
            Glide.with(mContext).load(mDatas.get(position).pic).into(holder.iv_news_society);
        }else {
            holder.iv_news_society.setImageResource(R.mipmap.ic_launcher);
        }



        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }



    class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_news_society_title,tv_news_society_ptime,tv_news_summary_digest;
        ImageView iv_news_society;

        public MyViewHolder(View view) {
            super(view);
            tv_news_society_title= (TextView) view.findViewById(R.id.tv_news_society_title);
            tv_news_society_ptime= (TextView) view.findViewById(R.id.tv_news_society_ptime);
            tv_news_summary_digest= (TextView) view.findViewById(R.id.tv_news_summary_digest);
            iv_news_society= (ImageView) view.findViewById(R.id.iv_news_society);
        }

    }
}
