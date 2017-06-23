package com.example.administrator.qzsdevelopapp.bean;

import java.util.List;

/**
 * 社会新闻
 */

public class SocietyBean {
    public int error_code;
    public String reason;
    public SocietyBeanResult result;
    public  class SocietyBeanResult{

        public List<SocietyBeanData> data;
        public String star;

        public class SocietyBeanData{
            public String author_name;
            public String  category;
            public String  date;
            public String  thumbnail_pic_s;
            public String  thumbnail_pic_s02;
            public String  thumbnail_pic_s03;
            public String  title;
            public String  uniquekey;
            public String   url;


        }



    }


}
