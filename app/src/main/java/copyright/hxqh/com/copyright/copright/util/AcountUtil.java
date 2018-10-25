package copyright.hxqh.com.copyright.copright.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.UserInfo;

/**
 * Created by zzw on 2018/9/18.
 */

public class AcountUtil {
    public static ProgressDialog progressDialog;

    public static void setUserNameAndPassWord(Context cxt, String userName, String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString("username", userName).putString("password", password).commit();
    }

    public static String getUsername(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString("username", "");
    }

    public static String getPassword(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString("password", "");
    }

    public static void saveUser(Context context, String preferenceName, String key, UserInfo user) throws Exception {
        if (user instanceof Serializable) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(user);//把对象写到流里
                String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                editor.putString(key, temp);
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception("User must implements Serializable");
        }
    }

    public static UserInfo getUser(Context context, String preferenceName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, "");
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        UserInfo user = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            user = (UserInfo) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e1) {

        }
        return user;
    }

    public static String toChinese(String string) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String result = "";
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
            System.out.println("  " + result);
        }
        System.out.println(result);
        return result;
    }

    /**
     * 加载进度条
     */
    public static void showProgressDialog(Context ctx, String message) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = new ProgressDialog(ctx,ProgressDialog.THEME_HOLO_DARK);
       /* Drawable drawable = ctx.getResources().getDrawable(R.color.);
        progressDialog.setIndeterminateDrawable(drawable);*/
        progressDialog.setIndeterminate(true);
        progressDialog.setIcon(R.drawable.loading_animation);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        if (message.equals("")) {
            progressDialog.setMessage("数据加载中...");
        } else {
            progressDialog.setMessage(message);
        }

        progressDialog.show();
    }

    /**
     * 关闭进度条
     **/
    public static void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /* public static boolean searchView(Activity context, Menu menu){
         context.getMenuInflater().inflate(R.menu.menu_search_view, menu);
         //找到searchView
         MenuItem searchItem = menu.findItem(R.id.action_search);
         SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
         return context.onCreateOptionsMenu(menu);
     }*/
    public static void imageShow(ImageView imageView, String url, int round) {
        if (url != null && !url.equals("")) {
            url = url.substring(1);
            url = "http://118.190.115.150:8889" + url;
        }
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.havenoimage) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.havenoimage) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.havenoimage) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(round))// 设置成圆角图片
                .build();
        imageLoader.displayImage(url, imageView, options);
    }

    public static void imageShow2(ImageView imageView, String url) {
        url = "http://118.190.115.150:8889" + url;

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.loading_animation) // 设置图片下载期间显示的图片
                .showImageOnFail(R.mipmap.havenoimage) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .build();
        imageLoader.displayImage(url, imageView, options);
    }
    public static Bitmap circleBitmap(Bitmap source) {

        int width = source.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);

        //设置图片相交情况下的处理方式
        //setXfermode：设置当绘制的图像出现相交情况时候的处理方式的,它包含的常用模式有：
        //PorterDuff.Mode.SRC_IN 取两层图像交集部分,只显示上层图像
        //PorterDuff.Mode.DST_IN 取两层图像交集部分,只显示下层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return bitmap;
    }
}
