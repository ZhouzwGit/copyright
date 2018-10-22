package copyright.hxqh.com.copyright.copright.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.entity.UserInfo;
import copyright.hxqh.com.copyright.copright.ui.IRM.AssetListActivity;
import copyright.hxqh.com.copyright.copright.ui.IRM.AssetListActivity2;
import copyright.hxqh.com.copyright.copright.ui.author.AuthorandRoyalty;
import copyright.hxqh.com.copyright.copright.ui.contract.ContractListActivity;
import copyright.hxqh.com.copyright.copright.ui.product.ProductListActivity;
import copyright.hxqh.com.copyright.copright.ui.product.ProductListActivity2;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.ui.publicService.PublicServiceActivity;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

public class TheMainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout irm, product, author, contract,publicSer;
    private LinearLayout main, inform, myinfo,backlog;
    private ImageView mainImage, informImage, myinfoImage,backlogImage;
    private View mainView;
    private Fragment fragment;
    private TextView hello;
    LinearLayout title;
    private TextView titletext,maintext,informtext,myinfotext,backlogtext;
    private static boolean isExit = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            //利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        findViewById();
        getUserData();
    }


    public void findViewById() {
        irm = findViewById(R.id.irm_id);
        product = findViewById(R.id.productsandoper_id);
        author = findViewById(R.id.authorandroyalty_id);
        contract = findViewById(R.id.contractmanagement_id);
        publicSer = findViewById(R.id.publicser_id);
        main = findViewById(R.id.main_layout_id);
        inform = findViewById(R.id.inform_id);
        myinfo = findViewById(R.id.myinfo_id);
        backlog = findViewById(R.id.backlog_id);
        mainView = findViewById(R.id.main_layout);
        mainImage = findViewById(R.id.main);
        informImage = findViewById(R.id.inform);
        myinfoImage = findViewById(R.id.selfmanager);
        backlogImage = findViewById(R.id.backlog_image_id);
        titletext = findViewById(R.id.title_id);
        hello = findViewById(R.id.hello_id);
        maintext = findViewById(R.id.idmain_text);
        informtext = findViewById(R.id.inform_text);
        myinfotext = findViewById(R.id.myinfo_text);
        backlogtext = findViewById(R.id.task_text);
        hello.setText(getdate()+AcountUtil.getUsername(this));

        title = findViewById(R.id.title);
        title.setVisibility(View.GONE);
        main.setOnClickListener(this);
        inform.setOnClickListener(this);
        myinfo.setOnClickListener(this);
        backlog.setOnClickListener(this);
        irm.setOnClickListener(this);
        product.setOnClickListener(this);
        author.setOnClickListener(this);
        contract.setOnClickListener(this);
        publicSer.setOnClickListener(this);
        main.performClick();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        switch (view.getId()) {
            case R.id.irm_id:
                Intent intent1 = new Intent(this, AssetListActivity2.class);
                startActivity(intent1);
                break;
            case R.id.productsandoper_id:
                Intent intent2 = new Intent(this, ProductListActivity2.class);
                startActivity(intent2);
                break;
            case R.id.authorandroyalty_id:
                Intent intent3 = new Intent(this, AuthorandRoyalty.class);
                startActivity(intent3);
                break;
            case R.id.contractmanagement_id:
                Intent intent4 = new Intent(this, ContractListActivity.class);
                startActivity(intent4);
                break;
            case R.id.publicser_id:
                Intent intent5 = new Intent(this, PublicServiceActivity.class);
                startActivity(intent5);
                break;
            case R.id.main_layout_id:
                mainView.setVisibility(View.VISIBLE);
                title.setVisibility(View.GONE);
                if (fragment!=null){
                    fragmentTransaction.remove(fragment);
                }
                maintext.setTextColor(R.color.blue);
                backlogtext.setTextColor(R.color.bottomtext);
                myinfotext.setTextColor(R.color.bottomtext);
                informtext.setTextColor(R.color.bottomtext);
                mainImage.setImageResource(R.mipmap.main_12);
                myinfoImage.setImageResource(R.mipmap.main_2);
                informImage.setImageResource(R.mipmap.main_13);
                backlogImage.setImageResource(R.mipmap.tasktodo);
                break;
            case R.id.inform_id:
                title.setVisibility(View.GONE);
                mainView.setVisibility(View.GONE);
                fragment = new InformtListActivity();
                maintext.setTextColor(R.color.bottomtext);
                backlogtext.setTextColor(R.color.bottomtext);
                myinfotext.setTextColor(R.color.blue);
                informtext.setTextColor(R.color.bottomtext);
                informImage.setImageResource(R.mipmap.main_1);
                myinfoImage.setImageResource(R.mipmap.main_2);
                mainImage.setImageResource(R.mipmap.main_11);
                backlogImage.setImageResource(R.mipmap.tasktodo);
                fragmentTransaction.replace(R.id.container,fragment).commit();
                break;
            case R.id.myinfo_id:
                mainView.setVisibility(View.GONE);
                fragment = new SelfManagerActivity();
                title.setBackgroundColor(R.color.blue);
                maintext.setTextColor(R.color.bottomtext);
                backlogtext.setTextColor(R.color.blue);
                myinfotext.setTextColor(R.color.bottomtext);
                informtext.setTextColor(R.color.bottomtext);
                mainImage.setImageResource(R.mipmap.main_11);
                myinfoImage.setImageResource(R.mipmap.main_3);
                informImage.setImageResource(R.mipmap.main_13);
                backlogImage.setImageResource(R.mipmap.tasktodo);
                fragmentTransaction.replace(R.id.container,fragment).commit();
                break;
            case R.id.backlog_id:
                title.setVisibility(View.GONE);
                mainView.setVisibility(View.GONE);
                fragment = new DaibanFragment();
                maintext.setTextColor(R.color.bottomtext);
                backlogtext.setTextColor(R.color.blue);
                myinfotext.setTextColor(R.color.bottomtext);
                informtext.setTextColor(R.color.bottomtext);
                informImage.setImageResource(R.mipmap.main_13);
                mainImage.setImageResource(R.mipmap.main_11);
                myinfoImage.setImageResource(R.mipmap.main_2);
                backlogImage.setImageResource(R.mipmap.tasktodo1);
                fragmentTransaction.replace(R.id.container,fragment).commit();
                break;
        }
    }
    public void getUserData(){
        new AsyncTask<String,String,UserInfo>(){

            @Override
            protected UserInfo doInBackground(String... strings) {

                return HttpConnect.getUserinfoList(AcountUtil.getUsername(TheMainActivity.this));
            }

            @Override
            protected void onPostExecute(UserInfo userInfo) {
                super.onPostExecute(userInfo);
                try {
                    AcountUtil.saveUser(TheMainActivity.this,"UserInfo","user",userInfo );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
    public String getdate(){
        Date date = new Date();
       if (date.getHours()<11){
           return "早上好,";
       }else if (date.getHours()<13 && date.getHours()>11){
           return "中午好,";
       }else if (date.getHours()>13 && date.getHours()<18){
           return "下午好,";
       }else{
           return "晚上好,";
       }
    }
}
