package copyright.hxqh.com.copyright.copright.ui.statistics.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lixs.charts.PieChartView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.BaseFragment;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.Series_data;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by lianjh on 2018\10\31 0031.
 * Current page
 */

public class OtherFragment extends BaseFragment {
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

    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;

    private JSONObject json;
    int mNum; //页号

    ArrayList<Series_data> serieslist= new ArrayList<>();
    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    List<Float> mRatios = new ArrayList<>();
    ArrayList<Integer> itemY = new ArrayList<>();
    Float unstorage;

    private int blueColor = Color.rgb(39,71,132);
    private int greenColor = Color.rgb(1,191,157);
    public static OtherFragment newInstance(int num) {
        OtherFragment fragment = new OtherFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.activity_pie,
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
        json =  HttpConnect.getREStYPEJson(getActivity());
        getData(json);
    }
    private void getData(final JSONObject json) {
        final String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/productEarlyWarningsix";
        new AsyncTask<String, String, StorageCounts>() {

            @Override
            protected StorageCounts doInBackground(String... strings) {
                StorageCounts products =  HttpConnect.getRightAssets(json,url);
                return products;
            }
            @Override
            protected void onPostExecute(StorageCounts preoducts) {
                super.onPostExecute(preoducts);
                serieslist = (ArrayList<Series_data>) preoducts.getSeries().get(0).getData();
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
        java.text.NumberFormat formate = java.text.NumberFormat.getNumberInstance();
        formate.setMaximumFractionDigits(2);
        mDescription.clear();
        mArcColors.clear();
        mRatios.clear();
        itemY.clear();
        unstoragecount.setText("0");
        unstorageprecent.setText("(0%)");
        storagecount.setText("0");
        storageprecent.setText("(0%)");

        if (serieslist != null && serieslist.size()!=0){
            mArcColors.add(greenColor);
            mArcColors.add(blueColor);
            int sum = 0;
            for (int i = 0;i<serieslist.size();i++){
                sum = sum + serieslist.get(i).getValue();
            }

            for (int i = 0;i<serieslist.size();i++){
                itemY.add(serieslist.get(i).getValue());
                mDescription.add(serieslist.get(i).getName().replaceAll("\r\n",""));
                unstorage = Float.valueOf(String.format("%.4f",(float)serieslist.get(i).getValue()/sum));
                mRatios.add(unstorage);
            }
            if (mRatios.size()>1){
                text1.setText(mDescription.get(0));
                text2.setText(mDescription.get(1));
                storagecount.setText(String.valueOf(itemY.get(0)));
                unstoragecount.setText(String.valueOf(itemY.get(1)));
                storageprecent.setText("("+String.valueOf(formate.format(mRatios.get(0)*100))+"%)");
                unstorageprecent.setText("("+String.valueOf(formate.format(mRatios.get(1)*100))+"%)");
            }else {
                text1.setText(mDescription.get(0));
                text2.setText("");
                storagecount.setText(String.valueOf(itemY.get(0)));
                storageprecent.setText("("+String.valueOf(formate.format(mRatios.get(0)*100))+"%)");

            }
        }
        //点击动画开启
        pieChartView.setCanClickAnimation(true);
        pieChartView.setDatas(mRatios, mArcColors, mDescription);
    }
}
