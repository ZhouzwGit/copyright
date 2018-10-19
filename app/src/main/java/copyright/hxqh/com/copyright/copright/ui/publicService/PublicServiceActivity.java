package copyright.hxqh.com.copyright.copright.ui.publicService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.AuthorListActivity;
import copyright.hxqh.com.copyright.copright.ui.author.RoyaltyListActivity;
import copyright.hxqh.com.copyright.copright.ui.contract.ContractListActivity;

/**
 * Created by zzw on 2018/9/28.
 */

public class PublicServiceActivity extends Activity implements View.OnClickListener{
    private RelativeLayout authorlayout,royaltylayout,counsellayout;
    private ImageView backimage,searchimage;
    private TextView titletext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicser_layout);
        findViewById();
        initView();
    }

    public void findViewById() {
      authorlayout = findViewById(R.id.resousceman_id);
      royaltylayout = findViewById(R.id.royaltyman_id);
      counsellayout = findViewById(R.id.counsel_id);
      backimage = findViewById(R.id.back_id);
      searchimage = findViewById(R.id.title_search);
      titletext = findViewById(R.id.menu_title);
        counsellayout = findViewById(R.id.counsel_id);
    }
    public void initView(){
        authorlayout.setOnClickListener(this);
        royaltylayout.setOnClickListener(this);
        counsellayout.setOnClickListener(this);
        backimage.setOnClickListener(this);
        searchimage.setVisibility(View.GONE);
        titletext.setText("公共服务");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.resousceman_id:
                Toast.makeText(PublicServiceActivity.this, "此功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.royaltyman_id:
                Intent intent = new Intent(this, RoyaltyActivity.class);
                startActivity(intent);
                break;
            case R.id.counsel_id:
                Intent intent1 = new Intent(this, ConsultActivity.class);
                startActivity(intent1);
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}
