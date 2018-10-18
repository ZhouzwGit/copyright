package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.RightInfo;
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.RightAdapter;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;

public class AssetDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout basicInfo;
    private BasicInfoFragment basicInfoFragment;
    private RightInfoFragment rightInfoFragment;
    private RelatedAssetFragment relatedAssetFragment;
    private TextView basicText,rightText,relatedassets;
    private RightAdapter rightAdapter;
    private ImageView backimage;
    private static Asset asset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_detail);
        findViewById();
        initView();
        getRightInfoAdpter(asset.getRightinfoList());
    }


    public void findViewById() {
        basicText  = findViewById(R.id.basicinfo_id);
        rightText = findViewById(R.id.rightinfo_id);
        relatedassets = findViewById(R.id.relatedassets_id);
        backimage = findViewById(R.id.ic_back);
    }
    public void initView(){
        basicText.setOnClickListener(this);
        rightText.setOnClickListener(this);
        relatedassets.setOnClickListener(this);
        backimage.setOnClickListener(this);
        asset = (Asset) getIntent().getExtras().get("asset");
        basicText.performClick();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        Drawable drawable = getResources().getDrawable(R.drawable.rightselectbj);
        Drawable drawable1 = getResources().getDrawable(R.drawable.rightnoselectbj);
        switch (view.getId()){
            case R.id.basicinfo_id:
                basicText.setBackground(drawable);
                relatedassets.setBackground(drawable1);
                rightText.setBackground(drawable1);
                if (basicInfoFragment==null){
                    basicInfoFragment = new BasicInfoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("asset", (Serializable) asset);
                    basicInfoFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container,basicInfoFragment).commit();
                break;
            case R.id.rightinfo_id:
                basicText.setBackground(drawable1);
                relatedassets.setBackground(drawable1);
                rightText.setBackground(drawable);
                if (rightInfoFragment==null){
                    rightInfoFragment = new RightInfoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("rightinfo", rightAdapter);
                    rightInfoFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container,rightInfoFragment).commit();
                break;
            case R.id.relatedassets_id:
                basicText.setBackground(drawable1);
                relatedassets.setBackground(drawable);
                rightText.setBackground(drawable1);
                if (relatedAssetFragment==null){
                    relatedAssetFragment = new RelatedAssetFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("rchrrelList", (Serializable) asset.getRightinfoList());
                    relatedAssetFragment.setArguments(bundle);
                }
                fragmentTransaction.replace(R.id.container,relatedAssetFragment).commit();
                break;
            case R.id.ic_back:
                finish();
                break;
        }
    }
    public void getRightInfoAdpter(List<RightInfo> list){
        rightAdapter = new RightAdapter(R.layout.list_right_card,list);
    }
}
