package copyright.hxqh.com.copyright.copright.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.UserInfo;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

public class UserinfoActivity extends AppCompatActivity {
    private TextView name, company, apartment, roles, mphonenum, phonenum, email, ip, lasttime, remark, titletext;
    private ImageView backimage, searchImage, userImage;
    private UserInfo userInfo;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfolayout);
        userInfo = AcountUtil.getUser(this, "UserInfo", "user");
        findView();
        initView();
    }

    public void findView() {
        name = findViewById(R.id.username_id);
        company = findViewById(R.id.company_id);
        apartment = findViewById(R.id.office_id);
        roles = findViewById(R.id.rolenames_id);
        mphonenum = findViewById(R.id.mobile_id);
        phonenum = findViewById(R.id.phone_id);
        email = findViewById(R.id.email_id);
        ip = findViewById(R.id.loginip_id);
        lasttime = findViewById(R.id.logindate_id);
        remark = findViewById(R.id.remark_id);
        backimage = findViewById(R.id.back_id);
        searchImage = findViewById(R.id.title_search);
        userImage = findViewById(R.id.userimage_id);
        titletext = findViewById(R.id.menu_title);
    }

    public void initView() {
        name.setText(userInfo.getName());
        company.setText(userInfo.getCompany());
        apartment.setText(userInfo.getOffice());
        roles.setText(userInfo.getRolenames());
        mphonenum.setText(userInfo.getMobile());
        phonenum.setText(userInfo.getPhone());
        email.setText(userInfo.getEmail());
        ip.setText(userInfo.getLoginip());
        lasttime.setText(userInfo.getLogindate());
        remark.setText(userInfo.getRemarks());
        titletext.setText("基本信息");
        searchImage.setVisibility(View.GONE);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String url = userInfo.getPhoto();
        if (url!=null && !url.equals("")){
            url = url.substring(1);
            url = "http://118.190.115.150:8889" + url;
        }
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.havenoimage) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.havenoimage) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.havenoimage) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build();
        imageLoader.displayImage(url,userImage,options);


       /* if (url != null && !url.equals("")){
            url = url.substring(1);
            ((MyImageView)userImage).setImageURL(url);
        }else {
            userImage.setImageResource(R.mipmap.havenoimage);
        }*/
    }

}
