package com.example.administrator.qzsdevelopapp.Utils;

import android.content.Context;
import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * @author: fanzhiwei
 * @date: 2016/12/10 0010
 */


public class HttpUtil {


    //===============================================以下XUtils请求==========================================================
/*
    public static final HttpUtils http = new HttpUtils(30000);

    //有参请求
    public static void  post (final Context context, String url, final Map<String, Object> map, final OnResultListener listener )  {
        LogUtil.e("url==",url);
        RequestParams parmas = new RequestParams();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();

            if(entry.getValue() instanceof List){
                List<String> list =  (List<String>)entry.getValue();
                for (String value : list){
                    parmas.addBodyParameter(key, value);// 带参数请求
                    LogUtil.e("重复key",key);
                    LogUtil.e("重复value",value);
                }
            }else{
                String value = entry.getValue()+"";
                LogUtil.e("key",key);
                LogUtil.e("value",value);
                // 发字段
                parmas.addBodyParameter(key, value);// 带参数请求
            }
        }

        http.send(HttpRequest.HttpMethod.POST, url, parmas, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                updateAndCallback(context,listener,responseInfo);
               // listener.onSuccess(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                listener.onError(error,error.getExceptionCode()+"");
                LogUtil.e("错误码：",error.getExceptionCode()+"");
                if(error.getExceptionCode()>=300&&error.getExceptionCode()<500){
                    ToastUtil.showL(context, "您的请求迷路了，请稍后再试");
                }else if(error.getExceptionCode()>=500){
                    ToastUtil.showL(context, "服务器异常，请稍后再试");
                }else{
                    ToastUtil.showL(context, "网络不给力");
                }
            }
        });

    }

    //无参请求
    public static void  post (final Context context, String url,final OnResultListener listener )  {


        LogUtil.e("url==",url);
        if(context==null){
            LogUtil.e("网络请求==","context==null");
            return;
        }
        http.send(HttpRequest.HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                updateAndCallback(context,listener,responseInfo);
                //listener.onSuccess(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                listener.onError(error,error.getExceptionCode()+"");
                LogUtil.e("错误码：",error.getExceptionCode()+"");
                if(error.getExceptionCode()>=300&&error.getExceptionCode()<500){
                    ToastUtil.showL(context, "您的请求迷路了，请稍后再试");
                }else if(error.getExceptionCode()>=500){
                    ToastUtil.showL(context, "服务器异常，请稍后再试");
                }else{
                    ToastUtil.showL(context, "网络不给力");
                }
            }
        });

    }


    *//**
     * 携带有参数的请求
     *//*
    public static void post(final Context context,String url, RequestParams parmas,final OnResultListener listener) {


        LogUtil.e("url==",url);
        http.send(HttpRequest.HttpMethod.POST, url, parmas, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                updateAndCallback(context,listener,responseInfo);
               // listener.onSuccess(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                LogUtil.e("网络问题",msg);
                listener.onError(error,error.getExceptionCode()+"");
                LogUtil.e("错误码：",error.getExceptionCode()+"");
                if(error.getExceptionCode()>=300&&error.getExceptionCode()<500){
                    ToastUtil.showL(context, "您的请求迷路了，请稍后再试");
                }else if(error.getExceptionCode()>=500){
                    ToastUtil.showL(context, "服务器异常，请稍后再试");
                }else{
                    ToastUtil.showL(context, "网络不给力");
                }
            }
        });

    }



    *//** 版本检测，如果通过调取回调 *//*
    public static void updateAndCallback(final Context context, final OnResultListener listener, ResponseInfo<String> responseInfo) {
        String json = responseInfo.result;
        if (listener != null) {
            // 版本更新
            HashMap<String, Object> jsonMap = JsonUtil.parseJsonToMap(json);
            //String version = (String) jsonMap.get("ANDROID_VERSION");
            String version = "";
            if(jsonMap!=null) {
                version = (String) jsonMap.get("ANDROID_VERSION_CODE");
            }
            if (!TextUtils.isEmpty(version) && context!=null&&Integer.parseInt(version)>VersionUtil.getCode(context)) {
                if(context instanceof Activity){
                    final Activity activity = (Activity)context;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UpdateBusiness2 business = new UpdateBusiness2(activity);
                            business.checkUpdateInfo(false);
                        }
                    });
                }
            }
            listener.onSuccess(json);
        }
    }

    *//**
     * 不带参数的不带更新的请求
     *//*
    public static void postNoUpdate(String url, final Context context, final OnResultListener listener) {

        http.send(HttpRequest.HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (listener != null) {
                    listener.onSuccess(responseInfo.result);
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {

                listener.onError(error,msg);
                LogUtil.e("错误码：",error.getExceptionCode()+"");
                if(error.getExceptionCode()>=300&&error.getExceptionCode()<500){
                    ToastUtil.showL(context, "您的请求迷路了，请稍后再试");
                }else if(error.getExceptionCode()>=500){
                    ToastUtil.showL(context, "服务器异常，请稍后再试");
                }else{
                    ToastUtil.showL(context, "网络不给力");
                }
            }
        });

    }*/

    public interface OnResultListener{
        void onSuccess(String result);
        void onError(Exception error, String msg);
    }

    //===============================================以下OKHttpUtils请求==========================================================


    //有参请求
    public static void  post (final Context context, String url, final Map<String, Object> map, final OnResultListener listener )  {
        LogUtil.e("url==",url);
        PostFormBuilder builder =  OkHttpUtils.post();


        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();

            if(entry.getValue() instanceof List){
                List<String> list =  (List<String>)entry.getValue();
                for (String value : list){
                    builder.addParams(key, value);
                    LogUtil.e("重复key",key);
                    LogUtil.e("重复value",value);
                }
            }else{
                String value = entry.getValue()+"";
                LogUtil.e("key",key);
                LogUtil.e("value",value);
                // 发字段
                builder.addParams(key, value);
            }
        }

                 builder.url(url)
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception error, int id) {
                        if(error!=null&&error.getMessage()!=null){
                            listener.onError(error,error.getMessage());
                        }else{
                            listener.onError(new Exception("网络不给力"),"");
                            ToastUtil.shortShow(context, "网络不给力");
                            return;
                        }

                        String e  = error.getMessage();
                        int code  =0;
                        if(!TextUtils.isEmpty(e)){
                            try {
                                e = e.substring(e.length()-3,e.length());
                                code = Integer.valueOf(e);
                            }catch (Exception e1){
                                ToastUtil.shortShow(context, "网络不给力");
                            }
                        }
                        LogUtil.e("code==：",code+"");
                        if(code>=300&&code<500){
                            ToastUtil.shortShow(context, "您的请求迷路了，请稍后再试");
                        }else if(code>=500){
                            ToastUtil.shortShow(context, "服务器异常，请稍后再试");
                        }else{
                            ToastUtil.shortShow(context, "网络不给力");
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("okHttp成功ID",id+"");
                       // LogUtil.e("okHttp成功",response);
                        //updateAndCallback2(context,listener,response);
                        listener.onSuccess(response);
                    }
                });
    }


    //无参请求
    public static void  post (final Context context, String url,final OnResultListener listener )  {

        LogUtil.e("url==",url);
        if(context==null){
            LogUtil.e("网络请求==","context==null");
            return;
        }
        LogUtil.e("url==",url);
        PostFormBuilder builder =  OkHttpUtils.post();
        builder.url(url)
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception error, int id) {
                        if(error!=null&&error.getMessage()!=null){
                            listener.onError(error,error.getMessage());
                        }else{
                            listener.onError(new Exception("网络不给力"),"");
                            ToastUtil.shortShow(context, "网络不给力");
                            return;
                        }

                        LogUtil.e("okHttp错误码：",error.getMessage());

                        String e  = error.getMessage();
                        int code  =0;
                        if(!TextUtils.isEmpty(e)){
                            try {
                                e = e.substring(e.length()-3,e.length());
                                code = Integer.valueOf(e);
                            }catch (Exception e1){
                                ToastUtil.shortShow(context, "网络不给力");
                            }
                        }
                        LogUtil.e("code==：",code+"");

                        if(code>=300&&code<500){
                            ToastUtil.shortShow(context, "您的请求迷路了，请稍后再试");
                        }else if(code>=500){
                            ToastUtil.shortShow(context, "服务器异常，请稍后再试");
                        }else{
                            ToastUtil.shortShow(context, "网络不给力");
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("okHttp成功ID",id+"");
                       // LogUtil.e("okHttp成功",response);
                        //updateAndCallback2(context,listener,response);
                        listener.onSuccess(response);
                    }
                });
    }


    public static void postNoUpdate(final Context context, String url, final Map<String, Object> map, final OnResultListener listener) {
        LogUtil.e("url==",url);
        PostFormBuilder builder =  OkHttpUtils.post();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();

            if(entry.getValue() instanceof List){
                List<String> list =  (List<String>)entry.getValue();
                for (String value : list){
                    builder.addParams(key, value);
                    LogUtil.e("重复key",key);
                    LogUtil.e("重复value",value);
                }
            }else{
                String value = entry.getValue()+"";
                LogUtil.e("key",key);
                LogUtil.e("value",value);
                // 发字段
                builder.addParams(key, value);
            }
        }

        builder.url(url)
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception error, int id) {
                        if(error!=null&&error.getMessage()!=null){
                            listener.onError(error,error.getMessage());
                        }else{
                            listener.onError(new Exception("网络不给力"),"");
                            ToastUtil.shortShow(context, "网络不给力");
                            return;
                        }
                        LogUtil.e("okHttp错误码：",error.getMessage());
                        String e  = error.getMessage();
                        int code  =0;
                        if(!TextUtils.isEmpty(e)){
                            try {
                                e = e.substring(e.length()-3,e.length());
                                code = Integer.valueOf(e);
                            }catch (Exception e1){
                                ToastUtil.shortShow(context, "网络不给力");
                            }
                        }
                        LogUtil.e("code==：",code+"");
                        if(code>=300&&code<500){
                            ToastUtil.shortShow(context, "您的请求迷路了，请稍后再试");
                        }else if(code>=500){
                            ToastUtil.shortShow(context, "服务器异常，请稍后再试");
                        }else{
                            ToastUtil.shortShow(context, "网络不给力");
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("okHttp成功ID",id+"");
                      //  LogUtil.e("okHttp成功",response);
                        listener.onSuccess(response);
                    }
                });
    }

    public static void postNoUpdate(final Context context, String url, final OnResultListener listener) {
        LogUtil.e("url==",url);
        if(context==null){
            LogUtil.e("网络请求==","context==null");
            return;
        }
        LogUtil.e("url==",url);
        PostFormBuilder builder =  OkHttpUtils.post();
        builder.url(url)
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception error, int id) {
                        if(error!=null&&error.getMessage()!=null){
                            listener.onError(error,error.getMessage());
                        }else{
                            listener.onError(new Exception("网络不给力"),"");
                            ToastUtil.shortShow(context, "网络不给力");
                            return;
                        }
                        LogUtil.e("okHttp错误码：",error.getMessage());
                        String e  = error.getMessage();
                        int code  =0;
                        if(!TextUtils.isEmpty(e)){
                            try {
                                e = e.substring(e.length()-3,e.length());
                                code = Integer.valueOf(e);
                            }catch (Exception e1){
                                ToastUtil.shortShow(context, "网络不给力");
                            }
                        }
                        LogUtil.e("code==：",code+"");
                        if(code>=300&&code<500){
                            ToastUtil.shortShow(context, "您的请求迷路了，请稍后再试");
                        }else if(code>=500){
                            ToastUtil.shortShow(context, "服务器异常，请稍后再试");
                        }else{
                            ToastUtil.shortShow(context, "网络不给力");
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("okHttp成功ID",id+"");
                       // LogUtil.e("okHttp成功",response);
                        listener.onSuccess(response);
                    }
                });
    }


//    //版本检测，如果通过调取回调
//    public static void updateAndCallback2(final Context context, final OnResultListener listener,String json) {
//        if (listener != null) {
//            // 版本更新
//            HashMap<String, Object> jsonMap = JsonUtil.parseJsonToMap(json);
//            String version = "";
//            if(jsonMap!=null) {
//                version = (String) jsonMap.get("ANDROID_VERSION_CODE");
//            }
//
//            if (!TextUtils.isEmpty(version) && context!=null&&Integer.parseInt(version)>VersionUtil.getCode(context)) {
//                if(context instanceof Activity){
//                    final Activity activity = (Activity)context;
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            UpdateBusiness2 business = new UpdateBusiness2(activity);
//                            business.checkUpdateInfo(false);
//                        }
//                    });
//                }
//            }
//            listener.onSuccess(json);
//      }
//    }

    //无参请求
    public static void  get (final Context context, String url,final OnResultListener listener )  {
        LogUtil.e("url==",url);
        if(context==null){
            LogUtil.e("网络请求==","context==null");
            return;
        }
        LogUtil.e("url==",url);
        PostFormBuilder builder =  OkHttpUtils.post();
        builder
                .url(url).addHeader("Authorization","78fba53e02d649e29beac60d119476f3")

                .addParams("area","111")
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception error, int id) {
                        if(error!=null&&error.getMessage()!=null){
                            listener.onError(error,error.getMessage());
                        }else{
                            listener.onError(new Exception("网络不给力"),"");
                            ToastUtil.shortShow(context, "网络不给力");
                            return;
                        }

                        LogUtil.e("okHttp错误码：",error.getMessage());

                        String e  = error.getMessage();
                        int code  =0;
                        if(!TextUtils.isEmpty(e)){
                            try {
                                e = e.substring(e.length()-3,e.length());
                                code = Integer.valueOf(e);
                            }catch (Exception e1){
                                ToastUtil.shortShow(context, "网络不给力");
                            }
                        }
                        LogUtil.e("code==：",code+"");

                        if(code>=300&&code<500){
                            ToastUtil.shortShow(context, "您的请求迷路了，请稍后再试");
                        }else if(code>=500){
                            ToastUtil.shortShow(context, "服务器异常，请稍后再试");
                        }else{
                            ToastUtil.shortShow(context, "网络不给力");
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("okHttp成功ID",id+"");
                        // LogUtil.e("okHttp成功",response);
                        //updateAndCallback2(context,listener,response);
                        listener.onSuccess(response);
                    }
                });
    }
}
