package copyright.hxqh.com.copyright.copright.ui.statistics.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lixs.charts.PieChartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.custom.BaseFragment;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.custom.RxBus;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.RxBusMessage;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.Series_data;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.StorageFragment;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by lianjh on 2018\10\30 0030.
 * Current page
 */

public class PropertyFragment  extends BaseFragment {
    @Bind(R.id.storage_count)
    TextView storagecount;
    @Bind(R.id.storage_precent)
    TextView storageprecent;
    @Bind(R.id.unstorage_count)
    TextView unstoragecount;
    @Bind(R.id.unstorage_precent)
    TextView unstorageprecent;
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

    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;

    RxBus rxBus;
    int mNum; //页号


    StorageCounts storageCounts;
    ArrayList<Series_data> serieslist= new ArrayList<>();
    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    Float instorage;
    Float unstorage;

    private int blueColor = Color.rgb(39,71,132);
    private int greenColor = Color.rgb(1,191,157);


    public static PropertyFragment newInstance(int num, StorageCounts storageCounts) {
        PropertyFragment fragment = new PropertyFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        args.putSerializable("storageCounts", storageCounts);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((isVisibleToUser && isResumed())) {
            onResume();
        } else if (!isVisibleToUser) {
            onPause();
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        storageCounts = (StorageCounts) getArguments().getSerializable("storageCounts");
        if (storageCounts.getSeries().get(0).getData() != null){
            serieslist = (ArrayList<Series_data>) storageCounts.getSeries().get(0).getData();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.activity_storage,
                container, false);
        ButterKnife.bind(getActivity());
        findViewById(settingLayout);
        initView();
        return settingLayout;
    }

    private void findViewById(View view) {
        storagecount = view.findViewById(R.id.storage_count);
        storageprecent = view.findViewById(R.id.storage_precent);
        unstoragecount = view.findViewById(R.id.unstorage_count);
        unstorageprecent = view.findViewById(R.id.unstorage_precent);
        linerPie = view.findViewById(R.id.liner_pie);
        textpie = view.findViewById(R.id.text_pie);
        textLbar = view.findViewById(R.id.text_Lbar);
        linerLbar = view.findViewById(R.id.liner_LBar);
        pieChartView = view.findViewById(R.id.pieView);
        barChart = view.findViewById(R.id.frameNewBase);
        imagepie = view.findViewById(R.id.image_pie);
        imageLbar = view.findViewById(R.id.image_Lbar);
        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
    }
    protected void initView() {
        initRxBus();
        if (serieslist!= null){
            initPieDatas();
            initColumnDatas();
        }
        barChart.setVisibility(View.GONE);
        barChart.setOnValueTouchListener(new ValueTouchListener());
        barChart.setValueSelectionEnabled(true);
        linerPie.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                pieChartView.setVisibility(View.VISIBLE);
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
                barChart.setVisibility(View.VISIBLE);
                textpie.setTextColor(Color.parseColor("#218BFF"));
                textLbar.setTextColor(Color.parseColor("#ffffff"));
                linerPie.setBackgroundResource(R.drawable.tab_left_unselector);
                linerLbar.setBackgroundResource(R.drawable.tab_right_selector);
                imagepie.setImageResource(R.drawable.ic_unselector_pie);
                imageLbar.setImageResource(R.drawable.ic_unselector_line);

            }
        });
    }
    private void initRxBus() {
        rxBus = RxBus.getIntanceBus();
        registerRxBus(RxBusMessage.class, new Consumer<RxBusMessage>() {
            @Override
            public void accept(@NonNull RxBusMessage rxBusMessage) throws Exception {
                if (rxBusMessage.getMessage().equals("update")){
                    storageCounts = rxBusMessage.getStorageCounts().get(mNum);
                    serieslist = (ArrayList<Series_data>) storageCounts.getSeries().get(0).getData();
                    if (serieslist!=null){
                        initPieDatas();
                        initColumnDatas();
                    }

                }
            }
        });
    }
    public <T> void registerRxBus(Class<T> eventType, Consumer<T> action) {
        Disposable disposable = rxBus.doSubscribe(eventType, action, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Log.e("NewsMainPresenter", throwable.toString());
            }
        });
        rxBus.addSubscription(this,disposable);
    }

    private void initPieDatas() {
        List<Float> mRatios = new ArrayList<>();
        mDescription.clear();
        mArcColors.clear();
        if (serieslist != null){
            int sum = 0;
            for (int i = 0;i<serieslist.size();i++){
                sum = sum + serieslist.get(i).getValue();
            }
            unstoragecount.setText("0");
            unstorageprecent.setText("(0%)");
            storagecount.setText("0");
            storageprecent.setText("(0%)");
            for (int i = 0;i<serieslist.size();i++){
                if (serieslist.get(i).getName().equals("资产")){
                    text1.setText(serieslist.get(i).getName());
                    text2.setText("资源");
                    unstoragecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    unstorage = Float.valueOf(String.format("%.2f",(float)serieslist.get(i).getValue()/sum));
                    unstorageprecent.setText("("+String.valueOf(unstorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(blueColor);
                    mRatios.add(unstorage);
                }else if (serieslist.get(i).getName().equals("资源")){
                    text1.setText(serieslist.get(i).getName());
                    text2.setText("资产");
                    storagecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    instorage =Float.valueOf(String.format("%.2f",(float)serieslist.get(i).getValue()/sum));
                    storageprecent.setText("("+String.valueOf(instorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(greenColor);
                    mRatios.add(instorage);
                }
                if (serieslist.get(i).getName().contains("未")){
                    text1.setText("已转化");
                    text2.setText("未转化");
                    unstoragecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    unstorage = Float.valueOf(String.format("%.2f",(float)serieslist.get(i).getValue()/sum));
                    unstorageprecent.setText("("+String.valueOf(unstorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(blueColor);
                    mRatios.add(unstorage);
                }else if (serieslist.get(i).getName().contains("已")){
                    text1.setText("已转化");
                    text2.setText("未转化");
                    storagecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    instorage =Float.valueOf(String.format("%.2f",(float)serieslist.get(i).getValue()/sum));
                    storageprecent.setText("("+String.valueOf(instorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(greenColor);
                    mRatios.add(instorage);
                }
            }

        }

        //点击动画开启
        pieChartView.setCanClickAnimation(true);
        pieChartView.setDatas(mRatios, mArcColors, mDescription);
    }
    private void initColumnDatas() {
        //x轴的数据
        List<String> itemX = new ArrayList<>();
        if (serieslist!=null){
            for (int i = 0;i<serieslist.size();i++){
                if (serieslist.get(i).getName().equals("资产")){
                    itemX.add(serieslist.get(i).getName());
                }else if (serieslist.get(i).getName().equals("资源")){
                    itemX.add(serieslist.get(i).getName());
                }
                if (serieslist.get(i).getName().contains("未")){
                    itemX.add(serieslist.get(i).getName());
                }else if (serieslist.get(i).getName().contains("已")){
                    itemX.add(serieslist.get(i).getName());
                }
            }
        }

        //定义X轴刻度值的数据集合
        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();
        for (int j = 0; j < itemX.size(); ++j) {
            axisValuesX.add(new AxisValue(j).setValue(j).setLabel(itemX.get(j)));
        }
        //y轴的数据
        ArrayList<Integer> itemY = new ArrayList<>();
        if (serieslist!=null) {
            for (int i = 0; i < serieslist.size(); i++) {
                if (serieslist.get(i).getName().equals("资产")) {
                    itemY.add(serieslist.get(i).getValue());
                } else if (serieslist.get(i).getName().equals("资源")) {
                    itemY.add(serieslist.get(i).getValue());
                }
                if (serieslist.get(i).getName().contains("未")) {
                    itemY.add(serieslist.get(i).getValue());
                } else if (serieslist.get(i).getName().contains("已")) {
                    itemY.add(serieslist.get(i).getValue());
                }
            }
        }
        int numSubcolumns = 1;
        int numColumns = itemX.size();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue(itemY.get(i), mArcColors.get(i)));
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
            axisX.setTextColor(ChartUtils.COLOR_BLUE);
            axisX.setValues(axisValuesX);
            axisX.setHasTiltedLabels(false);
            axisX.setTextSize(12);// 设置X轴文字大小
            axisX.setHasLines(true); //x 轴分割线
            axisX.setMaxLabelChars(4);
            Axis axisY = new Axis().setHasLines(true);
            if (ConstantsChart.hasAxesNames) {
//                axisX.setName("作品入库情况占比");
                axisY.setName("数量");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        data.setValueLabelTextSize(12);
        data.setFillRatio(0.4F);

        //设置行为属性，支持缩放、滑动以及平移
//        barChart.setInteractive(false);
//        barChart.setZoomType(ZoomType.HORIZONTAL);
//        barChart.setMaxZoom((float) 4);//最大方法比例
//        barChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        barChart.setColumnChartData(data);

//        Viewport v = new Viewport(barChart.getMaximumViewport());
//        v.left = 0;
//        v.right = 3;
//        barChart.setCurrentViewport(v);
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

    @Override
    public void onResume(){
        super.onResume();
        if (getUserVisibleHint()) {
            initPieDatas();
            initColumnDatas();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        //TODO give the signal that the fragment is invisible
    }
}
