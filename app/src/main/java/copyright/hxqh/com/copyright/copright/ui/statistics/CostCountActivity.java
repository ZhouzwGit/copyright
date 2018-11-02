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
 * Created by lianjh on 2018\10\31 0031.
 * Current page
 */

public class CostCountActivity  extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.costtype_id)
    RelativeLayout costtype;//按作品类型的成本统计
    @Bind(R.id.costright_id)
    RelativeLayout costright;//权利项成本分布
    @Bind(R.id.costauthor_id)
    RelativeLayout costauthor;//作者版税统计TOP10
    @Bind(R.id.title_search)
    ImageView searchimage;
    @Bind(R.id.back_id)
    ImageView backImage;
    @Bind(R.id.menu_title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costcount);
        ButterKnife.bind(this);
        costtype.setOnClickListener(this);
        costright.setOnClickListener(this);
        costauthor.setOnClickListener(this);
        backImage.setOnClickListener(this);
        title.setText("资产价值成本分析");
        searchimage.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.costtype_id:
                Intent intent0 = new Intent(this, ResCostingByKindActivity.class);
                startActivity(intent0);
                break;
            case R.id.costright_id:
                Intent intent = new Intent(this, RightCostingByResActivity.class);
                startActivity(intent);
                break;
            case R.id.costauthor_id:
                Intent intent1 = new Intent(this, ResCostingByAuthorActivity.class);
                startActivity(intent1);
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}
