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
    List<Float> mRatios = new ArrayList<>();
    ArrayList<Integer> itemY = new ArrayList<>();
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

        itemY.clear();
        mRatios.clear();
        mArcColors.clear();
        mDescription.clear();
        if (serieslist!= null){
            initPieDatas();
        }

    }

    private void initPieDatas() {
        mArcColors.add(greenColor);
        mArcColors.add(blueColor);
        unstoragecount.setText("0");
        unstorageprecent.setText("(0%)");
        storagecount.setText("0");
        storageprecent.setText("(0%)");

        if (serieslist != null){
            int sum = 0;
            for (int i = 0;i<serieslist.size();i++){
                sum = sum + serieslist.get(i).getValue();
            }
            for (int i = 0;i<serieslist.size();i++){
                itemY.add(serieslist.get(i).getValue());
                mDescription.add(serieslist.get(i).getName().replaceAll("\r\n",""));
                unstorage = Float.valueOf(String.format("%.2f",(float)serieslist.get(i).getValue()/sum));
                mRatios.add(unstorage);
            }
            if (mRatios.size()>1){
                text1.setText(mDescription.get(0));
                text2.setText(mDescription.get(1));
                storagecount.setText(String.valueOf(itemY.get(0)));
                unstoragecount.setText(String.valueOf(itemY.get(1)));
                storageprecent.setText("("+String.valueOf(mRatios.get(0)*100)+"%)");
                unstorageprecent.setText("("+String.valueOf(mRatios.get(1)*100)+"%)");
            }else {
                text1.setText(mDescription.get(0));
                text2.setText("");
                storagecount.setText(String.valueOf(itemY.get(0)));
                storageprecent.setText("("+String.valueOf(mRatios.get(0)*100)+"%)");

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
            itemY.clear();
            mRatios.clear();
            mArcColors.clear();
            mDescription.clear();
            initPieDatas();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }
}
