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
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.custom.CustomLinearLayoutManager;
import copyright.hxqh.com.copyright.copright.ui.statistics.adapter.ProductCostAdapter;
import copyright.hxqh.com.copyright.copright.ui.statistics.adapter.ResCostAdapter;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResCostingByKind;
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
 * Created by lianjh on 2018\11\6 0006.
 * Current page
 */

public class AcrelSellCountsActivity extends FragmentActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.text_starttime)
    TextView textStarttime;
    @Bind(R.id.imag_start)
    ImageView imag_start;
    @Bind(R.id.text_endtime)
    TextView textEndtime;
    @Bind(R.id.imag_end)
    ImageView imag_end;
    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;
    @Bind(R.id.liner_detail)
    LinearLayout liner_detail;
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
    @Bind(R.id.item_tittle)
    TextView item_tittle;
    @Bind(R.id.liner_form)
    LinearLayout liner_form;
    private Animation rotate;
    Dialog mCameraDialog;
    private ArrayList<String> list_year = new ArrayList<String>();
    private ArrayList<String> list_mooth = new ArrayList<String>();
    private ArrayList<String> list_day = new ArrayList<String>();
    LoopView loopView_year;
    LoopView loopView_month;
    LoopView loopView_day;
    //被选中或默认显示的时间
    Calendar date = Calendar.getInstance();
    private int year = date.get(Calendar.YEAR);
    private int mooth = date.get(Calendar.MONTH)+1;
    private int day = date.get(Calendar.DATE);
    private int oldDayCounts = 31;
    int flag = 0;
    private JSONObject json;
    ResCostAdapter resCostAdapter;
    CustomLinearLayoutManager layoutManager;

    List<String> data = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();

    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    List<Float> mRatios = new ArrayList<>();
    Float unstorage;

    //x轴的数据
    List<String> itemX = new ArrayList<>();
    //y轴的数据
    ArrayList<Float> itemY = new ArrayList<Float>();

    ArrayList<String> text3 = new ArrayList<>();
    List<Float> mRatios3 = new ArrayList<>();
    ArrayList<String> itemY3 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acrelsell);
        ButterKnife.bind(this);
        initView();
    }
    protected void initView() {
        json = HttpConnect.getProductCostJson(this,"","","bar");
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("资产价值收益分析");
        item_tittle.setText("资产销售数量排行");
        backButton = (ImageView) findViewById(R.id.back_id);
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画
        imag_start.setOnClickListener(startOnClickListener);
        imag_end.setOnClickListener(endOnClickListener);
        liner_detail.setOnClickListener(imag_downOnClickListener);
        layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setScrollEnabled(false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        barChart.setVisibility(View.GONE);
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
                textpie.setTextColor(Color.parseColor("#218BFF"));
                textLbar.setTextColor(Color.parseColor("#ffffff"));
                linerPie.setBackgroundResource(R.drawable.tab_left_unselector);
                linerLbar.setBackgroundResource(R.drawable.tab_right_selector);
                imagepie.setImageResource(R.drawable.ic_unselector_pie);
                imageLbar.setImageResource(R.drawable.ic_unselector_line);

            }
        });
        AcountUtil.showProgressDialog(this, "");
        initDataList();
        getData(json);
    }
    private View.OnClickListener imag_downOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (startAnaim()) {
                detail.setVisibility(View.GONE);
                resCostAdapter = new ResCostAdapter(AcrelSellCountsActivity.this, (ArrayList<String>) data, text, (ArrayList<Float>) mRatios);
                recyclerView.setAdapter(resCostAdapter);
            } else {
                resCostAdapter = new ResCostAdapter(AcrelSellCountsActivity.this,itemY3,text3, (ArrayList<Float>) mRatios3);
                recyclerView.setAdapter(resCostAdapter);
                detail.setVisibility(View.VISIBLE);
            }
        }
    };
    private View.OnClickListener endOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            flag = 2;
            showDialog();
        }
    };
    private View.OnClickListener startOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            flag = 1;
            showDialog();
        }
    };
    private void showDialog() {
        mCameraDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.time_dialog, null);
        loopView_year = (LoopView) root.findViewById(R.id.loopView_year);
        loopView_month = (LoopView) root.findViewById(R.id.loopView_month);
        loopView_day = (LoopView) root.findViewById(R.id.loopView_day);
        root.findViewById(R.id.text_cancel).setOnClickListener(btnlistener);
        root.findViewById(R.id.text_sure).setOnClickListener(btnlistener);
        root.findViewById(R.id.text_reset).setOnClickListener(btnlistener);
        mCameraDialog.setContentView(root);
        //滚动监听,年份
        loopView_year.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                year = Integer.parseInt(list_year.get(index));
                setRightDayCount();
            }
        });

        //滚动监听,月份
        loopView_month.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                mooth = Integer.parseInt(list_mooth.get(index));
                setRightDayCount();
            }
        });

        //滚动监听,天数
        loopView_day.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                day = Integer.parseInt(list_day.get(index));
            }
        });
        loopView_year.setItems(list_year);
        loopView_month.setItems(list_mooth);
        loopView_day.setItems(list_day);
        //默认时间,当前年月日
        for (int i = 0; i < list_year.size(); i++) {
            if (Integer.parseInt(list_year.get(i)) == year) {
                loopView_year.setCurrentPosition(i);
            }
        }
        loopView_month.setCurrentPosition(mooth-1);
        loopView_day.setCurrentPosition(day - 1);

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
    /**
     * 初始化日期的几个集合数据
     */
    private void initDataList() {
        list_year.clear();
        list_mooth.clear();
        list_day.clear();
        //年的时间
        for (int i = 2000; i < 2500; i++) {
            list_year.add("" + i);
        }

        //月的时间
        for (int i = 1; i < 13; i++) {
            list_mooth.add("" + i);
        }

        //日的时间
        for (int i = 1; i < 32; i++) {
            list_day.add("" + i);
        }
    }
    /**
     * 根据年数的月份显示对应的天数
     */
    private void setRightDayCount() {

        int dayCounts = 31;
        //28天的情况，润年2月
        if (leapYear(year) && mooth == 2) {
            dayCounts = 28;
        }
        //29天的情况，平年2月
        else if (!leapYear(year) && mooth == 2) {
            dayCounts = 29;
        }

        //30天的情况，2，4，6，9，11
        else if (mooth == 2 || mooth == 4 || mooth == 6 || mooth == 9 || mooth == 11) {
            dayCounts = 30;
        }

        //31天的情况 ，1，3，5，7，8，10，12
        else {
            dayCounts = 31;
        }

        list_day.clear();
        //日的时间
        for (int i = 1; i <= dayCounts; i++) {
            list_day.add("" + i);
        }

        if (oldDayCounts != dayCounts) {    //如果新老的天数不一样才变换天数
            //重新改变天的数量
            loopView_day.setItems(list_day);

            //判断是否要变更选中的天数，比如选中3-31滑动到2月变成2-28或2-29
            if (dayCounts < day) {
                loopView_day.setCurrentPosition(dayCounts - 1);
            }
            oldDayCounts = dayCounts;
        }


    }

    /**
     * 判断是闰年leapYear还是平年
     */
    private boolean leapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }

        if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }

        return false;
    }

    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.text_sure) {
                if (flag == 1){
                    textStarttime.setText(year + "-" + mooth + "-" + day);
                }else if (flag == 2){
                    textEndtime.setText(year + "-" + mooth + "-" + day);
                }
                json = HttpConnect.getProductCostJson(AcrelSellCountsActivity.this,textStarttime.getText().toString(),textEndtime.getText().toString(),"bar");
                getData(json);
                mCameraDialog.dismiss();
                flag = 0;
            } else if (i == R.id.text_cancel) {
                if (mCameraDialog != null) {
                    mCameraDialog.dismiss();
                    flag = 0;
                }

            }else if (i == R.id.text_reset) {
                for (int j = 0; j < list_year.size(); j++) {
                    if (Integer.parseInt(list_year.get(j)) == date.get(Calendar.YEAR)) {
                        loopView_year.setCurrentPosition(j);
                    }
                }
                loopView_month.setCurrentPosition(date.get(Calendar.MONTH));
                loopView_day.setCurrentPosition(date.get(Calendar.DATE) - 1);
                year = date.get(Calendar.YEAR);
                mooth = date.get(Calendar.MONTH);
                day = date.get(Calendar.DATE);
            }
        }
    };
    private void getData(final JSONObject json) {
        final String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/acrelSellCounts";
        new AsyncTask<String, String, ResCostingByKind>() {

            @Override
            protected ResCostingByKind doInBackground(String... strings) {
                ResCostingByKind products =  HttpConnect.getResCostingByKind(json,url);
                return products;
            }
            @Override
            protected void onPostExecute(ResCostingByKind preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts!=null){
                    data =  preoducts.getSeries().get(0).getData();
                    text = (ArrayList<String>) preoducts.getXaxis().get(0).getData();
                }
                if (data == null){
                    linerLbar.setEnabled(false);
                    linerPie.setEnabled(false);
                    pieChartView.setVisibility(View.GONE);
                    liner_detail.setVisibility(View.GONE);
                    resCostAdapter = new ResCostAdapter(AcrelSellCountsActivity.this, (ArrayList<String>) data,text, (ArrayList<Float>) mRatios3);
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
                itemX.add(text.get(i).replaceAll("\n",""));
                itemY.add(Float.valueOf(data.get(i)));
                mDescription.add(text.get(i).replaceAll("\n",""));
                if (Float.valueOf(data.get(i)) == 0 || Float.valueOf(data.get(i)) == 0.0){
                    unstorage = Float.valueOf(0);
                }else {
                    unstorage = Float.valueOf(String.format("%.4f",(float)Float.valueOf(data.get(i))/sum));
                }
                mRatios.add(unstorage);
                mArcColors.add(ChartUtils.COLORS[i]);
            }

        }else {
            Toast.makeText(AcrelSellCountsActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
        }
        if (itemY.size() < 3){
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
                text3.add(itemX.get(i));
            }
            resCostAdapter = new ResCostAdapter(this,itemY3,text3, (ArrayList<Float>) mRatios3);
            recyclerView.setAdapter(resCostAdapter);
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
