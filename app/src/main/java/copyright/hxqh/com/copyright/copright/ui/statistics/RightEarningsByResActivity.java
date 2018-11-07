package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lixs.charts.PieChartView;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.custom.CustomLinearLayoutManager;
import copyright.hxqh.com.copyright.copright.ui.statistics.adapter.ResCostAdapter;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResCostingByKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResourceKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResourceName;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.util.ChartUtils;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by lianjh on 2018\11\5 0005.
 * Current page
 */

public class RightEarningsByResActivity extends FragmentActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.text_resourcekind) //做品类型
            TextView textresourcekind;
    @Bind(R.id.recoursetype)
    ImageView recoursetype;
    @Bind(R.id.text_resourcename) //做品名称
            TextView textresourcename;
    @Bind(R.id.recoursename)
    ImageView recoursename;
    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;
    @Bind(R.id.text_detail)
    TextView detail;
    @Bind(R.id.imag_down)
    ImageView imag_down;
    @Bind(R.id.liner_pie)
    LinearLayout linerPie;
    @Bind(R.id.text_pie)
    TextView textpie;
    @Bind(R.id.image_pie)
    ImageView imagepie;
    @Bind(R.id.liner_LBar)
    LinearLayout linerLbar;
    @Bind(R.id.text_Lbar)
    TextView textLbar;
    @Bind(R.id.image_Lbar)
    ImageView imageLbar;
    @Bind(R.id.pieView)
    PieChartView pieChartView;
    @Bind(R.id.frameNewBase)
    ColumnChartView barChart;
    @Bind(R.id.liner_money)
    LinearLayout liner_money;
    @Bind(R.id.money)
    TextView money;
    @Bind(R.id.liner_detail)
    LinearLayout liner_detail;
    @Bind(R.id.liner_form)
    LinearLayout liner_form;
    @Bind(R.id.item_tittle)
    TextView item_tittle;
    CustomLinearLayoutManager layoutManager;
    private JSONObject json;
    private JSONObject json2;
    private int page = 1;
    String resourcekind;
    String resourcename;
    Dialog mCameraDialog;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> listname = new ArrayList<>();
    ResCostAdapter resCostAdapter;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    List<Float> mRatios = new ArrayList<>();
    ResCostingByKind resCostingByKind = new ResCostingByKind();
    Float unstorage;
    private Animation rotate;
    //x轴的数据
    List<String> itemX = new ArrayList<>();
    //y轴的数据
    ArrayList<Float> itemY = new ArrayList<Float>();

    ArrayList<String> text3 = new ArrayList<>();
    List<Float> mRatios3 = new ArrayList<>();
    ArrayList<String> itemY3 = new ArrayList<String>();
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rightcostingbyres);
        ButterKnife.bind(this);
        initView();
    }
    protected void initView() {
        json = HttpConnect.getBasicJson(this);
        json2 = HttpConnect.getRightCostJson(RightEarningsByResActivity.this,resourcekind,"pie",resourcename);
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("资产价值成本分析");
        item_tittle.setText("权利项收益分析");
        backButton = (ImageView) findViewById(R.id.back_id);
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画
        recoursetype.setOnClickListener(recoursetypeOnClickListener);
        recoursename.setOnClickListener(recoursenameOnClickListener);
        liner_detail.setOnClickListener(imag_downOnClickListener);
        layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setScrollEnabled(false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        barChart.setVisibility(View.GONE);
        liner_money.setVisibility(View.GONE);
        barChart.setOnValueTouchListener(new ValueTouchListener());
        barChart.setValueSelectionEnabled(true);
        linerPie.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                pieChartView.setVisibility(View.VISIBLE);
                if (itemY.size()<3){
                    liner_detail.setVisibility(View.GONE);
                }else {
                    liner_detail.setVisibility(View.VISIBLE);
                }
                recyclerView.setVisibility(View.VISIBLE);
                liner_form.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
                liner_money.setVisibility(View.GONE);
                textpie.setTextColor(Color.parseColor("#ffffff"));
                textLbar.setTextColor(Color.parseColor("#218BFF"));
                linerPie.setBackgroundResource(R.drawable.tab_left_selector);
                linerLbar.setBackgroundResource(R.drawable.tab_right_unselector);
                imagepie.setImageResource(R.drawable.ic_pie);
                imageLbar.setImageResource(R.drawable.ic_line);
            }
        });
        linerLbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieChartView.setVisibility(View.GONE);
                liner_detail.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                liner_form.setVisibility(View.GONE);
                barChart.setVisibility(View.VISIBLE);
                liner_money.setVisibility(View.VISIBLE);
                textpie.setTextColor(Color.parseColor("#218BFF"));
                textLbar.setTextColor(Color.parseColor("#ffffff"));
                linerPie.setBackgroundResource(R.drawable.tab_left_unselector);
                linerLbar.setBackgroundResource(R.drawable.tab_right_selector);
                imagepie.setImageResource(R.drawable.ic_unselector_pie);
                imageLbar.setImageResource(R.drawable.ic_unselector_line);

            }
        });
        AcountUtil.showProgressDialog(this, "");
        getType();
        getName();
        getData(json2);
    }
    private View.OnClickListener imag_downOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (startAnaim()) {
                detail.setVisibility(View.GONE);
                resCostAdapter.notifyDataSetChanged();
                resCostAdapter = new ResCostAdapter(RightEarningsByResActivity.this,data, text, (ArrayList<Float>) mRatios);
                recyclerView.setAdapter(resCostAdapter);
            } else {
                resCostAdapter.notifyDataSetChanged();
                resCostAdapter = new ResCostAdapter(RightEarningsByResActivity.this,itemY3,text3, (ArrayList<Float>) mRatios3);
                recyclerView.setAdapter(resCostAdapter);
                detail.setVisibility(View.VISIBLE);
            }
        }
    };
    private View.OnClickListener recoursenameOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            flag = 1;
            showDialog();
        }
    };
    private View.OnClickListener recoursetypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            flag = 0;
            showDialog();
        }
    };
    private void showDialog() {
        mCameraDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.my_dialog, null);
        LoopView loopView = (LoopView) root.findViewById(R.id.loopView);
        root.findViewById(R.id.text_cancel).setOnClickListener(btnlistener);
        root.findViewById(R.id.text_sure).setOnClickListener(btnlistener);
        mCameraDialog.setContentView(root);
        //设置初始位置
        loopView.setInitPosition(0);
        loopView.setCurrentPosition(0);
        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (flag == 0){
                    resourcekind = list.get(index);
                }else if (flag == 1){
                    resourcename = listname.get(index);
                }

            }
        });

        // 设置原始数据
        if (flag == 0){
            loopView.setItems(list);
        }else if (flag == 1){
            loopView.setItems(listname);
        }

        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        mCameraDialog.setCanceledOnTouchOutside(true);
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度

        root.measure(0, 0);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }
    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.text_sure) {
                if (flag == 0){
                    textresourcekind.setText(resourcekind);
                }else if (flag == 1){
                    textresourcename.setText(resourcename);
                }
                json2 = HttpConnect.getRightCostJson(RightEarningsByResActivity.this,textresourcekind.getText().toString(),"pie",textresourcename.getText().toString());
                getData(json2);
                mCameraDialog.dismiss();
            } else if (i == R.id.text_cancel) {
                if (mCameraDialog != null) {
                    mCameraDialog.dismiss();
                }

            }
        }
    };
    private void getName() {
        new AsyncTask<String, String, List<ResourceName>>() {

            @Override
            protected List<ResourceName> doInBackground(String... strings) {
                List<ResourceName> products = (List<ResourceName>) HttpConnect.getResourceNmae(json, page);
                return products;
            }
            @Override
            protected void onPostExecute(List<ResourceName> preoducts) {
                for (int i = 0;i<preoducts.size();i++){
                    listname.add(preoducts.get(i).getResname());
                }
                if (preoducts.size() % 2 == 0){
                    resourcename = preoducts.get(preoducts.size()/2).getResname();
                }else if (preoducts.size() % 2 == 1){
                    resourcename = preoducts.get(preoducts.size()/2+1).getResname();
                }

            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }
    private void getType() {
        new AsyncTask<String, String, List<ResourceKind>>() {

            @Override
            protected List<ResourceKind> doInBackground(String... strings) {
                List<ResourceKind> products = (List<ResourceKind>) HttpConnect.getResourceType(json, page);
                return products;
            }
            @Override
            protected void onPostExecute(List<ResourceKind> preoducts) {
                for (int i = 0;i<preoducts.size();i++){
                    list.add(preoducts.get(i).getResourcekind());
                }
                if (preoducts.size() % 2 == 0){
                    resourcekind = preoducts.get(preoducts.size()/2).getResourcekind();
                }else if (preoducts.size() % 2 == 1){
                    resourcekind = preoducts.get(preoducts.size()/2+1).getResourcekind();
                }

            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }
    private void getData(final JSONObject json) {
        final String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/rightEarningsByRes";
        new AsyncTask<String, String, ResCostingByKind>() {

            @Override
            protected ResCostingByKind doInBackground(String... strings) {
                ResCostingByKind products =  HttpConnect.getResCostingByKind(json,url);
                return products;
            }
            @Override
            protected void onPostExecute(ResCostingByKind preoducts) {
                super.onPostExecute(preoducts);
                resCostingByKind = preoducts;
                if (preoducts!=null){
                    data = (ArrayList<String>) preoducts.getSeries().get(0).getData();
                    text = (ArrayList<String>) preoducts.getXaxis().get(0).getData();
                }
                if (data == null){
                    linerLbar.setEnabled(false);
                    linerPie.setEnabled(false);
                    pieChartView.setVisibility(View.GONE);
                    liner_detail.setVisibility(View.GONE);
                    resCostAdapter = new ResCostAdapter(RightEarningsByResActivity.this,data,text, (ArrayList<Float>) mRatios3);
                    recyclerView.setAdapter(resCostAdapter);
                }else {
                    linerLbar.setEnabled(true);
                    linerPie.setEnabled(true);
                    pieChartView.setVisibility(View.VISIBLE);
                    liner_detail.setVisibility(View.VISIBLE);
                }

                initPieDatas();
                initColumnDatas();

                AcountUtil.closeProgressDialog();
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();

    }
    private void initPieDatas() {
        //小数点保留2位
        java.text.NumberFormat formate = java.text.NumberFormat.getNumberInstance();
        formate.setMaximumFractionDigits(4);
        if (resCostingByKind != null){
            money.setText(resCostingByKind.getTitle().getSubtext().substring(4));
        }
        mRatios.clear();
        mArcColors.clear();
        mDescription.clear();
        itemX.clear();
        itemY.clear();

        if (data != null){
            float sum = 0;
            for (int i = 0;i<data.size();i++){
                sum = sum + Float.valueOf(data.get(i));
            }
            for (int i = 0;i<data.size();i++){
                String num = text.get(i).replaceAll("\n","");
                if (num.length()>4){
                    itemX.add(num.substring(0, 4)+"...");
                }else {
                    itemX.add(num);
                }
//                itemX.add(text.get(i).replaceAll("\n",""));
                itemY.add(Float.valueOf(data.get(i)));
                mDescription.add(text.get(i).replaceAll("\n",""));
                if (Float.valueOf(data.get(i)) == 0 || Float.valueOf(data.get(i)) == 0.0){
                    unstorage = Float.valueOf(0);
                }else {
                    unstorage = Float.valueOf(formate.format((float)Float.valueOf(data.get(i))/sum));
                }
                mRatios.add(unstorage);
                mArcColors.add(ChartUtils.COLORS[i]);
            }
            if (itemY.size() < 3){
                liner_detail.setVisibility(View.GONE);
                resCostAdapter = new ResCostAdapter(this,data, text, (ArrayList<Float>) mRatios);
                recyclerView.setAdapter(resCostAdapter);
            }else {
                itemY3.clear();
                text3.clear();
                mRatios3.clear();
                for (int i=0;i<4;i++){
                    itemY3.add(data.get(i));
                    mRatios3.add(mRatios.get(i));
                    text3.add(itemX.get(i));
                }

                resCostAdapter = new ResCostAdapter(this,itemY3,text3, (ArrayList<Float>) mRatios3);
                recyclerView.setAdapter(resCostAdapter);
            }
        }else {
            Toast.makeText(RightEarningsByResActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
        }

        //点击动画开启
        pieChartView.setCanClickAnimation(true);
        pieChartView.setDatas(mRatios, mArcColors, mDescription);
    }
    private void initColumnDatas() {
        //定义X轴刻度值的数据集合
        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();
        for (int j = 0; j < itemX.size(); ++j) {
            axisValuesX.add(new AxisValue(j).setValue(j).setLabel(itemX.get(j)));
        }
        int numSubcolumns = 1;
        int numColumns = itemX.size();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue(itemY.get(i), Color.rgb(33,139,255)));
            }
            Column column = new Column(values);
            column.setHasLabels(ConstantsChart.hasLabels);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }

        itemX.clear();
        ColumnChartData data = new ColumnChartData(columns);

        if (ConstantsChart.hasAxes) {
            Axis axisX = new Axis();
            axisX.setTextColor(ChartUtils.DARKEN_COLOR);
            axisX.setValues(axisValuesX);
            axisX.setHasTiltedLabels(true);
            axisX.setTextSize(10);// 设置X轴文字大小
            axisX.setHasLines(false); //x 轴分割线
//            axisX.setMaxLabelChars(4);
            Axis axisY = new Axis().setHasLines(false);
            if (ConstantsChart.hasAxesNames) {
//                axisX.setName("作品入库情况占比");
//                axisY.setName("数量");
            }
            data.setAxisXBottom(axisX);
//            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        data.setValueLabelTextSize(10);
        data.setFillRatio(0.4F);
        //设置行为属性，支持缩放、滑动以及平移
        barChart.setInteractive(true);
        barChart.setZoomType(ZoomType.HORIZONTAL);

        barChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        barChart.setColumnChartData(data);

        Viewport v = new Viewport(barChart.getMaximumViewport());
        v.left = 0;
        v.right = 5;
        barChart.setCurrentViewport(v);

    }
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
    private boolean startAnaim() {

        rotate.setInterpolator(new LinearInterpolator());//设置为线性旋转

        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转

        liner_detail.startAnimation(rotate);
        return rotate.getFillAfter();
    }
}
