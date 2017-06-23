package com.example.administrator.qzsdevelopapp.bean;

import java.util.List;

/**
 * Created by qzs on 2017/5/10.
 */

public class SportBean {
    public String msg;
    public Result result;
    public int status;


    public  class Result{
       public String channel;
        public List<listData> list;
        public String num;

        public class listData{
           public  String   category;
            public String   content;
            public String   pic;
            public String    src;
            public String    time;
            public String    title;
            public String     url;
            public String     weburl;

        }

    }
}
