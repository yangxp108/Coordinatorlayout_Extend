package com.example.administrator.qzsdevelopapp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qzs on 2016/9/26.
 */
public abstract class BaseFragment extends Fragment {
    public View view;
    public Context context;
    public Activity mactivity;

    public BaseFragment(Activity mactivity){
        this.mactivity=mactivity;

    }
//创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=(Context)getActivity();
     //  FragmentManager fm=getChildFragmentManager();
    }
  //加载view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView();
        return view;
    }
 //加载数据
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);
    }
    //将每一个子fragment对象中的布局转换成view对象
    public abstract View initView() ;
    //拿数据填充oncreateView返回的view对象
    public abstract void initData();
}
