package com.example.administrator.qzsdevelopapp.fragment;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.qzsdevelopapp.R;
import com.example.administrator.qzsdevelopapp.Utils.BaseUtils;
import com.example.administrator.qzsdevelopapp.Utils.HttpUtil;
import com.example.administrator.qzsdevelopapp.Utils.LogUtil;
import com.example.administrator.qzsdevelopapp.adapter.RecycleSportAdapter;
import com.example.administrator.qzsdevelopapp.base.BaseFragment;
import com.example.administrator.qzsdevelopapp.bean.SocietyBean;
import com.example.administrator.qzsdevelopapp.bean.SportBean;
import com.google.gson.Gson;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qzs on 2017/5/5.
 */

public class Fragment_Sport extends BaseFragment {
    private ArrayList<SportBean.Result.listData> list=new ArrayList<SportBean.Result.listData>();
    private MultiStateView multiStateView;
    private View view;
    private RecycleSportAdapter adapter;
    private RecyclerView recyclerview;
    public Fragment_Sport(Activity mactivity) {
        super(mactivity);
    }

    @Override
    public View initView() {
        view=View.inflate(context, R.layout.activity_sport,null);
     //   multiStateView= (MultiStateView) view.findViewById(R.id.multiStateView);
        recyclerview= (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void initData() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        Map<String,Object> map=new HashMap<>();
        HttpUtil.post(context, "http://api.jisuapi.com/news/get?channel=体育&start=0&num=10&appkey=49ac426389e3d7af", map, new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
            //    LogUtil.e("秦子帅",result+"");
                Gson gson=new Gson();
                SportBean sportBean=gson.fromJson(result,SportBean.class);
                LogUtil.e("解析",sportBean.status+"");
                if (sportBean.status==0){
                    multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                    list.clear();
                    list.addAll(sportBean.result.list);
                    recyclerview.setLayoutManager(new LinearLayoutManager(recyclerview.getContext()));
                    recyclerview.setAdapter(adapter = new RecycleSportAdapter(context,list));
                    adapter.setOnItemClickListener(new RecycleSportAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                }
                //   Log.e("请求",result.toString()+"");
                //         System.out.print(result.toString());
            }

            @Override
            public void onError(Exception error, String msg) {
                Log.e("错误",msg+"");
                multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
            }
        });

    }




}
