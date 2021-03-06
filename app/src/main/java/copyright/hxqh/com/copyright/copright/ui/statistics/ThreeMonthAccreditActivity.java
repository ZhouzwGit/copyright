package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lixs.charts.PieChartView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.CustomLinearLayoutManager;
import copyright.hxqh.com.copyright.copright.ui.statistics.adapter.ResCostAdapter;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResCostingByKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.util.ChartUtils;

/**
 * Created by lianjh on 2018\11\5 0005.
 * Current page
 */

public class ThreeMonthAccreditActivity extends FragmentActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;
    @Bind(R.id.text_detail)
    TextView detail;
    @Bind(R.id.liner_detail)
    LinearLayout liner_detail;
    @Bind(R.id.item_tittle)
    TextView item_tittle;
    @Bind(R.id.pieView)
    PieChartView pieChartView;

    CustomLinearLayoutManager layoutManager;
    private Animation rotate;

    ResCostAdapter resCostAdapter;
    private JSONObject json;
    List<String> data = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();

    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    List<Float> mRatios = new ArrayList<>();

    Float unstorage;

    ArrayList<String> text3 = new ArrayList<>();
    List<Float> mRatios3 = new ArrayList<>();
    ArrayList<String> itemY3 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threemonth);
        ButterKnife.bind(this);
        initView();
    }
    protected void initView() {
        json = HttpConnect.getREStYPEJson(this);
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("资产频度分析");
        item_tittle.setText("3个月内渠道授权占比");
        backButton = (ImageView) findViewById(R.id.back_id);
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画
        liner_detail.setOnClickListener(imag_downOnClickListener);
        layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setScrollEnabled(false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AcountUtil.showProgressDialog(this, "");
        getData(json);
    }
    private View.OnClickListener imag_downOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (startAnaim()) {
                detail.setVisibility(View.GONE);
                resCostAdapter = new ResCostAdapter(ThreeMonthAccreditActivity.this, (ArrayList<String>) data, text, (ArrayList<Float>) mRatios);
                recyclerView.setAdapter(resCostAdapter);
            } else {
                resCostAdapter = new ResCostAdapter(ThreeMonthAccreditActivity.this,itemY3,text3, (ArrayList<Float>) mRatios3);
                recyclerView.setAdapter(resCostAdapter);
                detail.setVisibility(View.VISIBLE);
            }
        }
    };
    private void getData(final JSONObject json) {
        final String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/threeMonthAccredit";
        new AsyncTask<String, String, StorageCounts>() {

            @Override
            protected StorageCounts doInBackground(String... strings) {
                StorageCounts products =  HttpConnect.getRightAssets(json,url);
                return products;
            }
            @Override
            protected void onPostExecute(StorageCounts preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts!=null){
                    for (int i =0 ; i < preoducts.getSeries().get(0).getData().size();i++){
                        data.add(String.valueOf(preoducts.getSeries().get(0).getData().get(i).getValue()));
                    }
                    text = (ArrayList<String>) preoducts.getLegend().getData();
                }
                initPieDatas();

                AcountUtil.closeProgressDialog();
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();

    }
    private void initPieDatas() {
        mRatios.clear();
        mArcColors.clear();
        mDescription.clear();

        if (data != null){
            float sum = 0;
            for (int i = 0;i<data.size();i++){
                sum = sum + Float.valueOf(data.get(i));
            }
            for (int i = 0;i<data.size();i++){
                mDescription.add(text.get(i).replaceAll("\n",""));
                if (Float.valueOf(data.get(i)) == 0 || Float.valueOf(data.get(i)) == 0.0){
                    unstorage = Float.valueOf(0);
                }else {
                    unstorage = Float.valueOf(String.format("%.2f",(float)Float.valueOf(data.get(i))/sum));
                }
                mRatios.add(unstorage);
                mArcColors.add(ChartUtils.COLORS[i]);
            }

        }else {
            Toast.makeText(ThreeMonthAccreditActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
        }
        if (mDescription.size() < 3){
            liner_detail.setVisibility(View.GONE);
            resCostAdapter = new ResCostAdapter(this, (ArrayList<String>) data,text, (ArrayList<Float>) mRatios);
            recyclerView.setAdapter(resCostAdapter);
        }else {
            itemY3.clear();
            text3.clear();
            mRatios3.clear();
            for (int i=0;i<4;i++){
                itemY3.add(data.get(i));
                mRatios3.add(mRatios.get(i));
                text3.add(mDescription.get(i));
            }
            resCostAdapter = new ResCostAdapter(this,itemY3,text3, (ArrayList<Float>) mRatios3);
            recyclerView.setAdapter(resCostAdapter);
        }
        //点击动画开启
        pieChartView.setCanClickAnimation(true);
        pieChartView.setDatas(mRatios, mArcColors, mDescription);
    }
    private boolean startAnaim() {

        rotate.setInterpolator(new LinearInterpolator());//设置为线性旋转

        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转

        liner_detail.startAnimation(rotate);
        return rotate.getFillAfter();
    }
}
