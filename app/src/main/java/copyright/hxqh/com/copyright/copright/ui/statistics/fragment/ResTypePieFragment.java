package copyright.hxqh.com.copyright.copright.ui.statistics.fragment;

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

public class ResTypePieFragment extends BaseFragment {
    @Bind(R.id.storage_count)
    TextView storagecount;
    @Bind(R.id.storage_precent)
    TextView storageprecent;
    @Bind(R.id.unstorage_count)
    TextView unstoragecount;
    @Bind(R.id.unstorage_precent)
    TextView unstorageprecent;
    @Bind(R.id.pieView)
    PieChartView pieChartView;
    int mNum; //页号
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;

    StorageCounts storageCounts;
    ArrayList<Series_data> serieslist= new ArrayList<>();
    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    Float instorage;
    Float unstorage;

    private int blueColor = Color.rgb(39,71,132);
    private int greenColor = Color.rgb(1,191,157);


    public static ResTypePieFragment newInstance(int num,StorageCounts storageCounts) {
        ResTypePieFragment fragment = new ResTypePieFragment();
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
        View settingLayout = inflater.inflate(R.layout.fragment_resource,
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
        pieChartView = view.findViewById(R.id.pieView);
        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
    }
    protected void initView() {
        if (serieslist!= null){
            initPieDatas();
        }

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
                if (serieslist.get(i).getName().contains("未截稿")){
                    text1.setText("已截稿");
                    text2.setText("未截稿");
                    unstoragecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    unstorage = Float.valueOf(String.format("%.3f",(float)serieslist.get(i).getValue()/sum));
                    unstorageprecent.setText("("+String.valueOf(unstorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(blueColor);
                    mRatios.add(unstorage);
                }else if (serieslist.get(i).getName().contains("已截稿")){
                    text1.setText("已截稿");
                    text2.setText("未截稿");
                    storagecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    instorage =Float.valueOf(String.format("%.3f",(float)serieslist.get(i).getValue()/sum));
                    storageprecent.setText("("+String.valueOf(instorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(greenColor);
                    mRatios.add(instorage);
                }
                if (serieslist.get(i).getName().contains("无")){
                    text1.setText("有相关合同");
                    text2.setText("无相关合同");
                    unstoragecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    unstorage = Float.valueOf(String.format("%.3f",(float)serieslist.get(i).getValue()/sum));
                    unstorageprecent.setText("("+String.valueOf(unstorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(blueColor);
                    mRatios.add(unstorage);
                }else if (serieslist.get(i).getName().contains("有")){
                    text1.setText("有相关合同");
                    text2.setText("无相关合同");
                    storagecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    instorage =Float.valueOf(String.format("%.3f",(float)serieslist.get(i).getValue()/sum));
                    storageprecent.setText("("+String.valueOf(instorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(greenColor);
                    mRatios.add(instorage);
                }
                if (serieslist.get(i).getName().contains("未设置永")){
                    text1.setText("设置永久");
                    text2.setText("未设置永久");
                    unstoragecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    unstorage = Float.valueOf(String.format("%.3f",(float)serieslist.get(i).getValue()/sum));
                    unstorageprecent.setText("("+String.valueOf(unstorage * 100)+"%)");
                    mDescription.add(serieslist.get(i).getName());
                    mArcColors.add(blueColor);
                    mRatios.add(unstorage);
                }else if (serieslist.get(i).getName().contains("设置永久")){
                    text1.setText("设置永久");
                    text2.setText("未设置永久");
                    storagecount.setText(String.valueOf(serieslist.get(i).getValue()));
                    instorage =Float.valueOf(String.format("%.3f",(float)serieslist.get(i).getValue()/sum));
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

    @Override
    public void onResume(){
        super.onResume();
        if (getUserVisibleHint()) {
            initPieDatas();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }
}
