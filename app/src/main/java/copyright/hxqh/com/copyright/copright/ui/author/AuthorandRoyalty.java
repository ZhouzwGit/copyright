package copyright.hxqh.com.copyright.copright.ui.author;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import copyright.hxqh.com.copyright.R;

/**
 * Created by zzw on 2018/9/28.
 */

public class AuthorandRoyalty extends Activity implements View.OnClickListener{
    private RelativeLayout authorlayout,royaltylayout;
    private ImageView backimage,searchimage;
    private TextView titletext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autandres_layout);
        findViewById();
        initView();
    }

    public void findViewById() {
      authorlayout = findViewById(R.id.resousceman_id);
      royaltylayout = findViewById(R.id.royaltyman_id);
      backimage = findViewById(R.id.back_id);
      searchimage = findViewById(R.id.title_search);
      titletext = findViewById(R.id.menu_title);
    }
    public void initView(){
        authorlayout.setOnClickListener(this);
        royaltylayout.setOnClickListener(this);
        backimage.setOnClickListener(this);
        searchimage.setVisibility(View.GONE);
        titletext.setText("来源方及版税");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.resousceman_id:
                Intent intent = new Intent(this,AuthorListActivity.class);
                startActivity(intent);
                break;
            case R.id.royaltyman_id:
                Intent intent2 = new Intent(this,RoyaltyListActivity.class);
                startActivity(intent2);
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}
