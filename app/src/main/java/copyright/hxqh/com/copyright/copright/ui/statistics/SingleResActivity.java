package copyright.hxqh.com.copyright.copright.ui.statistics;

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
 * Created by lianjh on 2018\11\2 0002.
 * Current page
 */

public class SingleResActivity  extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.single_use_id)
    RelativeLayout single_use;//单资产利用率排行
    @Bind(R.id.single_change_id)
    RelativeLayout single_change;//单资产转化率
    @Bind(R.id.single_roude_id)
    RelativeLayout single_roude;//3个月内渠道授权占比
    @Bind(R.id.single_sale_id)
    RelativeLayout single_sale;//3个月内销售执行占比
    @Bind(R.id.title_search)
    ImageView searchimage;
    @Bind(R.id.back_id)
    ImageView backImage;
    @Bind(R.id.menu_title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.bind(this);
        single_use.setOnClickListener(this);
        single_change.setOnClickListener(this);
        single_roude.setOnClickListener(this);
        single_sale.setOnClickListener(this);
        backImage.setOnClickListener(this);
        title.setText("资产频度分析");
        searchimage.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.single_use_id:
//                Intent intent0 = new Intent(this, ResCostingByKindActivity.class);
//                startActivity(intent0);
                break;
            case R.id.single_change_id:
//                Intent intent = new Intent(this, RightCostingByResActivity.class);
//                startActivity(intent);
                break;
            case R.id.single_roude_id:
//                Intent intent1 = new Intent(this, ResCostingByAuthorActivity.class);
//                startActivity(intent1);
                break;
            case R.id.single_sale_id:
//                Intent intent1 = new Intent(this, ResCostingByAuthorActivity.class);
//                startActivity(intent1);
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}

