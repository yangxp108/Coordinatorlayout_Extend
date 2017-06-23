package com.example.administrator.qzsdevelopapp;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.qzsdevelopapp.Utils.HttpUtil;
import com.example.administrator.qzsdevelopapp.Utils.IntentUtils;
import com.example.administrator.qzsdevelopapp.Utils.LogUtil;
import com.example.administrator.qzsdevelopapp.adapter.MyPagerAdapter;
import com.example.administrator.qzsdevelopapp.base.BaseFragment;
import com.example.administrator.qzsdevelopapp.fragment.Fragment_Sport;
import com.example.administrator.qzsdevelopapp.fragment.Fragment_new;
import com.example.administrator.qzsdevelopapp.fragment.Fragment_video;
import com.example.administrator.qzsdevelopapp.fragment.LinearFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import cn.hugeterry.coordinatortablayout.listener.LoadHeaderImagesListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout moshi_layout;

    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mColorArray;
    private final String[] mTitles = {"头条", "娱乐", "体育"};
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initFragments();
        initViewPager();
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light
        };
        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        //mCoordinatorTabLayout.getActionBar().setDisplayHomeAsUpEnabled(true);
        mCoordinatorTabLayout.getActionBar().setHomeAsUpIndicator(R.mipmap.top_nav);

        mCoordinatorTabLayout.setTitle("QZS")

                .setContentScrimColorArray(mColorArray).setLoadHeaderImagesListener(new LoadHeaderImagesListener() {
            @Override
            public void loadHeaderImages(ImageView imageView, TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        imageView.setImageResource(R.mipmap.bg_android);
                     //   loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_android.jpg");
                        break;
                    case 1:
                        imageView.setImageResource(R.mipmap.bg_js);
                        //loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_js.jpg");
                        break;
                    case 2:
                        imageView.setImageResource(R.mipmap.bg_ios);
                    //    loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_ios.jpg");
                        break;
                }
            }
        }).setupWithViewPager(mViewPager);

       // OKhttp();
    }

    private void loadImages(ImageView imageView, String url) {
        Glide.with(this).load(url).into(imageView);
    }


    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));

    }

    private void initFragments() {
        LinearFragment f1 = new LinearFragment(this);
        Fragment_new f2 = new Fragment_new(this);
        Fragment_video f3 = new Fragment_video(this);
        mFragments.add(f2);
        mFragments.add(f3);
        mFragments.add(f1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about:
                IntentUtils.openUrl(this, "http://www.jianshu.com/p/a57f32c21fb7");
                break;
            case R.id.action_about_me:
               IntentUtils.openUrl(this, "http://www.jianshu.com/p/a57f32c21fb7");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public  void OKhttp(){
        Map<String,Object> map=new HashMap<>();
//        map.put("from","toutiao");
//        map.put("offset","1");
        HttpUtil.post(this, "http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=49ac426389e3d7af",map, new HttpUtil.OnResultListener() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("秦子帅",result+"");
             //   Log.e("请求",result.toString()+"");
       //         System.out.print(result.toString());

            }

            @Override
            public void onError(Exception error, String msg) {
                Log.e("错误",msg+"");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}