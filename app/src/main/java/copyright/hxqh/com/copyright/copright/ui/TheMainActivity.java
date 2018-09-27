package copyright.hxqh.com.copyright.copright.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.IRM.AssetListActivity;
import copyright.hxqh.com.copyright.copright.ui.product.ProductListActivity;
import copyright.hxqh.com.copyright.copright.ui.product.ProductListActivity2;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;

public class TheMainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout irm,product,author,contract;
    private static boolean isExit=false;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit= false;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }
    private void exit(){
        if(!isExit){
            isExit=true;
            Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
                    //利用handler延迟发送更改状态信息
                    handler.sendEmptyMessageDelayed(0,2000);
        }
        else{
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
    }


    public void findViewById() {
       irm = findViewById(R.id.irm_id);
       product = findViewById(R.id.productsandoper_id);
       author = findViewById(R.id.authorandroyalty_id);
       contract =findViewById(R.id.productsandoper_id);
       irm.setOnClickListener(this);
       product.setOnClickListener(this);
       author.setOnClickListener(this);
       contract.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.irm_id:
               /* Intent intent1 = new Intent(this, AssetListActivity.class);
                startActivity(intent1);*/
                break;
            case R.id.productsandoper_id:
                Intent intent2 = new Intent(this, ProductListActivity2.class);
                startActivity(intent2);
                break;
            case R.id.authorandroyalty_id:
                break;
            case R.id.contractmanagement_id:
                break;
        }
    }
}
