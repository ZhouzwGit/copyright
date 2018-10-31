package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;

/**
 * Created by lianjh on 2018\10\30 0030.
 * Current page
 */

public class RightCountActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.rightstorage_id)
    RelativeLayout rightstorage;//权利项入库情况占比
    @Bind(R.id.rightasset_id)
    RelativeLayout rightasset;//权利项转化资产入库占比
    @Bind(R.id.property_id)
    RelativeLayout property;//zichanku
    @Bind(R.id.risk_id)
    RelativeLayout risk;//风险预警
    @Bind(R.id.title_search)
    ImageView searchimage;
    @Bind(R.id.back_id)
    ImageView backImage;
    @Bind(R.id.menu_title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rightcount);
        ButterKnife.bind(this);
        rightstorage.setOnClickListener(this);
        rightasset.setOnClickListener(this);
        property.setOnClickListener(this);
        risk.setOnClickListener(this);
        backImage.setOnClickListener(this);
        title.setText("权利项数量分析");
        searchimage.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rightstorage_id:
                Intent intent0 = new Intent(this, RightPutInStorageActivity.class);
                startActivity(intent0);
                break;
            case R.id.rightasset_id:
                Intent intent = new Intent(this, RightPropertyStorageActivity.class);
                startActivity(intent);
                break;
            case R.id.property_id:
                Intent intent1 = new Intent(this, RightAssetsActivity.class);
                startActivity(intent1);
                break;
            case R.id.risk_id:
                Intent intent2 = new Intent(this, ProductEarlyWarningActivity.class);
                startActivity(intent2);
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}

