package com.example.administrator.qzsdevelopapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.qzsdevelopapp.R;
import com.example.administrator.qzsdevelopapp.SportDetailActivity;
import com.example.administrator.qzsdevelopapp.Utils.HttpUtil;
import com.example.administrator.qzsdevelopapp.Utils.LogUtil;
import com.example.administrator.qzsdevelopapp.adapter.RecyclerAdapter;
import com.example.administrator.qzsdevelopapp.base.BaseFragment;
import com.example.administrator.qzsdevelopapp.bean.SportBean;
import com.google.gson.Gson;
import com.sch.rfview.AnimRFRecyclerView;
import com.sch.rfview.decoration.DividerItemDecoration;
import com.sch.rfview.manager.AnimRFLinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qzs on 2017/5/5.
 */

public class Fragment_new extends Fragment {
    public AppCompatActivity mactivity;

    private ArrayList<SportBean.Result.listData> list=new ArrayList<SportBean.Result.listData>();
    private MyAdapter adapter;
    private AnimRFRecyclerView mRecyclerView;
    private int num=1;
    private View headerView;
    private View view;
    private View footerView;
    private List<String> datas;
    private Handler mHandler = new Handler();

    public Fragment_new(AppCompatActivity mactivity){
        this.mactivity=mactivity;

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // newData();
        LogUtil.e("进程","2");

        // 自定义的RecyclerView, 也可以在布局文件中正常使用
        view=View.inflate(getActivity(), R.layout.activity_sport,null);
        mRecyclerView= (AnimRFRecyclerView) view.findViewById(R.id.mRecyclerView);
        // 头部
        LogUtil.e("进程","3");
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_view, null);
        // 脚部
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.footer_view, null);

        // 使用重写后的线性布局管理器
        AnimRFLinearLayoutManager manager = new AnimRFLinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), manager.getOrientation(), true));
//            // 添加头部和脚部，如果不添加就使用默认的头部和脚部
        //   mRecyclerView.addHeaderView(headerView);
//            // 设置头部的最大拉伸倍率，默认1.5f，必须写在setHeaderImage()之前
//            mRecyclerView.setScaleRatio(1.7f);
//            // 设置下拉时拉伸的图片，不设置就使用默认的
        mRecyclerView.setHeaderImage((ImageView) headerView.findViewById(R.id.iv_hander));
//            mRecyclerView.addFootView(footerView);
        // 设置刷新动画的颜色
        mRecyclerView.setColor(Color.RED, Color.BLUE);
        mRecyclerView.setHeaderImage((ImageView) headerView.findViewById(R.id.iv_hander));
        // 设置头部恢复动画的执行时间，默认500毫秒
        mRecyclerView.setHeaderImageDurationMillis(300);
        // 设置拉伸到最高时头部的透明度，默认0.5f
        mRecyclerView.setHeaderImageMinAlpha(0.6f);
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 设置适配器
        adapter=new MyAdapter();
        mRecyclerView.setAdapter(adapter);

        LogUtil.e("进程","4");

        // 设置刷新和加载更多数据的监听，分别在onRefresh()和onLoadMore()方法中执行刷新和加载更多操作
        mRecyclerView.setLoadDataListener(new AnimRFRecyclerView.LoadDataListener() {
            @Override
            public void onRefresh() {
                new Thread(new MyRunnable(true)).start();
                LogUtil.e("进程","5");
            }

            @Override
            public void onLoadMore() {
                new Thread(new MyRunnable(false)).start();
                LogUtil.e("进程","6");
            }
        });

        // 刷新
        mRecyclerView.setRefresh(true);



        return view;
    }

    class MyRunnable implements Runnable {

        boolean isRefresh;

        public MyRunnable(boolean isRefresh) {
            this.isRefresh = isRefresh;
        }

        @Override
        public void run() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isRefresh) {
                        newData();

                    } else {
                        addData();

                    }
                }
            }, 2000);
        }
    }

    public void refreshComplate() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void loadMoreComplate() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    private void addData() {

        Map<String,Object> map=new HashMap<>();
        HttpUtil.post(getActivity(), "http://api.jisuapi.com/news/get?channel=头条&start="+num+"&num=10&appkey=49ac426389e3d7af", map, new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                //    LogUtil.e("秦子帅",result+"");
                Gson gson=new Gson();
                SportBean sportBean=gson.fromJson(result,SportBean.class);
                LogUtil.e("解析","111");
                if (sportBean.status==0){
                    num++;
                    //list.clear();
                    list.addAll(sportBean.result.list);
                    loadMoreComplate();
                    // 加载更多完成后调用，必须在UI线程中
                    mRecyclerView.loadMoreComplate();
                }
                //   Log.e("请求",result.toString()+"");
                //         System.out.print(result.toString());
            }

            @Override
            public void onError(Exception error, String msg) {
                Log.e("错误",msg+"");
            }
        });

    }

    public void newData() {
        Map<String,Object> map=new HashMap<>();
        HttpUtil.post(getActivity(), "http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=49ac426389e3d7af", map, new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                //    LogUtil.e("秦子帅",result+"");
                Gson gson=new Gson();
                SportBean sportBean=gson.fromJson(result,SportBean.class);
                LogUtil.e("解析","222");
                if (sportBean.status==0){

                    list.clear();
                    list.addAll(sportBean.result.list);
                    refreshComplate();
                    // 刷新完成后调用，必须在UI线程中
                    mRecyclerView.refreshComplate();
                }
                //   Log.e("请求",result.toString()+"");
                //         System.out.print(result.toString());
            }

            @Override
            public void onError(Exception error, String msg) {
                Log.e("错误",msg+"");
            }
        });

    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_news_sport, parent, false);
//            TextView view = new TextView(getActivity());
//            view.setGravity(Gravity.CENTER);
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    DimensionConvert.dip2px(getActivity(), 50)));
         MyViewHolder mViewHolder = new MyViewHolder(view);
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            // holder.tv_news_summary_digest.setText(list.get(position).content+"");
            holder.tv_news_society_ptime.setText(list.get(position).time+"");
            holder.tv_news_society_title.setText(list.get(position).title+"");
            if (list.get(position).pic!=null){
                Glide.with(getActivity()).load(list.get(position).pic).into(holder.iv_news_society);
            }else {
                holder.iv_news_society.setImageResource(R.mipmap.ic_launcher);
            }

            holder.tv_news_summary_digest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(mactivity,SportDetailActivity.class);
                    intent.putExtra("weburl",list.get(position).url+"");
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_news_society_title,tv_news_society_ptime,tv_news_summary_digest;
        ImageView iv_news_society;
        RelativeLayout item_sport_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_sport_layout= (RelativeLayout) itemView.findViewById(R.id.item_sport_layout);
            tv_news_society_title= (TextView) itemView.findViewById(R.id.tv_news_society_title);
            tv_news_society_ptime= (TextView) itemView.findViewById(R.id.tv_news_society_ptime);
            tv_news_summary_digest= (TextView) itemView.findViewById(R.id.tv_news_summary_digest);
            iv_news_society= (ImageView) itemView.findViewById(R.id.iv_news_society);
        }
    }

}
