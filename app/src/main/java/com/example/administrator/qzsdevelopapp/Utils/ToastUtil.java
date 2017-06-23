package com.example.administrator.qzsdevelopapp.Utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 吐司的显示工具
 * Created by fanzhiwei on 2016/10/31.
 */
public class ToastUtil {

    /**
     * 测试 为true； 正式 false；
     */
    private static final boolean isShow = true;

    //长显示
    public static void longShow(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    //短显示
    public static void shortShow(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Adding an Image to the Standard Toast
     * 设置吐司的中心位置 长时
     */
    public static void centerLongToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * Adding an Image to the Standard Toast
     * 设置吐司的中心位置 短时
     */
    public static void centerShortToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * Adding an Image to the Standard Toast
     * 在标准显示方式基础上添加图片
     */
    public static void definedSizeLongToast(Context context, String msg, int size) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
//	   Toast toast = new Toast(context);

        LinearLayout layout = (LinearLayout) toast.getView();
        TextView textView = (TextView) layout.getChildAt(0);
        textView.setTextSize(size);

//       layout.addView(textView,0);

//       Toast toast = Toast.makeText(getApplicationContext(),"Hello, This is Andy!", Toast.LENGTH_LONG);
//       toast.setGravity(Gravity.CENTER, 0, 0);
//       LinearLayout toastView = (LinearLayout) toast.getView();
//       ImageView imageCodeProject = new ImageView(getApplicationContext());
//       imageCodeProject.setImageResource(R.drawable.icon);
//       toastView.addView(imageCodeProject, 0);
        toast.show();
    }
}
