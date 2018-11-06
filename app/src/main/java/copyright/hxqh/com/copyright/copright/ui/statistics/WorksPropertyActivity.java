package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.MyOnPageChangeListener1;
import copyright.hxqh.com.copyright.copright.custom.ProductSelectCallBack;
import copyright.hxqh.com.copyright.copright.custom.RxBus;
import copyright.hxqh.com.copyright.copright.ui.statistics.adapter.PropertyCountAdapter;
import copyright.hxqh.com.copyright.copright.ui.statistics.adapter.WorksCountAdapter;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResourceKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.RxBusMessage;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.view.LazyViewPager;

/**
 * Created by lianjh on 2018\10\30 0030.
 * Current page
 */

public class WorksPropertyActivity extends FragmentActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.text_resourcekind)
    TextView textresourcekind;
    @Bind(R.id.viewpager)
    LazyViewPager mViewPager;
    @Bind(R.id.select)
    RelativeLayout select;

    @Bind(R.id.recoursetype)
    ImageView recoursetype;

    @Bind(R.id.hsv_content)
    LinearLayout hsv_content;

    @Bind(R.id.hsv_view)
    HorizontalScrollView mHorizontalScrollView;

    Dialog mCameraDialog;

    private PropertyCountAdapter propertyCountAdapter;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private int mScreenWidth;
    private int item_width;
    /**
     * 标签文字
     */
    ArrayList<RelativeLayout> relativeLayouts = new ArrayList<RelativeLayout>();

    String resourcekind;
    private JSONObject json;
    private JSONObject json2;
    private int page = 1;
    int index = 0;

    ArrayList<String> list = new ArrayList<>();
    List<StorageCounts> storageCountsList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksproperty);
        ButterKnife.bind(this);
        initView();
        //这里因为是3.0一下版本，所以需继承FragmentActivity，通过getSupportFragmentManager()获取FragmentManager
        //3.0及其以上版本，只需继承Activity，通过getFragmentManager获取事物
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        //初始化自定义适配器
        propertyCountAdapter =  new PropertyCountAdapter(fm);
        //绑定自定义适配器
        mViewPager.setAdapter(propertyCountAdapter);
        MyOnPageChangeListener1 myOnPageChangeListener = new MyOnPageChangeListener1(relativeLayouts, this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;

        item_width = (int) ((mScreenWidth / 3.0 + 0.5f));
        myOnPageChangeListener.init(0, null, mHorizontalScrollView, mViewPager, new ProductSelectCallBack() {
            @Override
            public void deal(int index) {
                propertyCountAdapter.setStorageCounts(storageCountsList.get(index));
            }
        });
        mViewPager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                propertyCountAdapter.setStorageCounts(storageCountsList.get(position));
                switchTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentManager = getFragmentManager();

    }
    protected void initView() {
        json = HttpConnect.getBasicJson(this);
        json2 = HttpConnect.getWorksPropertyJson(this,resourcekind,"pie");
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("作品转化资产情况");
        backButton = (ImageView) findViewById(R.id.back_id);
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recoursetype.setOnClickListener(recoursetypeOnClickListener);
        AcountUtil.showProgressDialog(this, "");
        getType();
        getData(json2);
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

    View.OnClickListener layoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index = (Integer) v.getTag();
            switchTab(index);
            propertyCountAdapter.setStorageCounts(storageCountsList.get(index));
            mViewPager.setCurrentItem(index, true);
        }
    };

    private View.OnClickListener recoursetypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
                resourcekind = list.get(index);
            }
        });

        // 设置原始数据
        loopView.setItems(list);
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
                textresourcekind.setText(resourcekind);
                json2 = HttpConnect.getWorksPropertyJson(WorksPropertyActivity.this,textresourcekind.getText().toString(),"pie");
                getData2(json2);
                mCameraDialog.dismiss();
            } else if (i == R.id.text_cancel) {
                if (mCameraDialog != null) {
                    mCameraDialog.dismiss();
                }

            }
        }
    };
    private void getData(final JSONObject json) {
        new AsyncTask<String, String, List<StorageCounts>>() {

            @Override
            protected List<StorageCounts> doInBackground(String... strings) {
                List<StorageCounts> products = (List<StorageCounts>) HttpConnect.getPutInStorageCounts(json);
                return products;
            }
            @Override
            protected void onPostExecute(List<StorageCounts> preoducts) {
                super.onPostExecute(preoducts);
                storageCountsList = preoducts;
                initViewPageBar();
                AcountUtil.closeProgressDialog();
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();

    }
    private void getData2(final JSONObject json) {
        new AsyncTask<String, String, List<StorageCounts>>() {

            @Override
            protected List<StorageCounts> doInBackground(String... strings) {
                List<StorageCounts> products = (List<StorageCounts>) HttpConnect.getPutInStorageCounts(json);
                return products;
            }
            @Override
            protected void onPostExecute(List<StorageCounts> preoducts) {
                super.onPostExecute(preoducts);
                storageCountsList = preoducts;
                RxBusMessage rxBusMessage = new RxBusMessage();
                rxBusMessage.setMessage("update");
                rxBusMessage.setStorageCounts(storageCountsList);
                RxBus.getIntanceBus().post(rxBusMessage);
                initViewPageBar();
                AcountUtil.closeProgressDialog();
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();

    }
    private void initViewPageBar(){
        /**
         * 屏幕高宽度
         */
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        String[] labels = { "资产", "资源"};
        for (int i = 0; i < labels.length; i++) {
            RelativeLayout layout = new RelativeLayout(this);
            TextView view = new TextView(this);
            view.setText(labels[i]);
            view.setTextColor(getResources().getColor(R.color.barcolor));
            float textSize = getResources().getDimension(R.dimen.dimens_16_sp);
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            view.setGravity(Gravity.CENTER);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            params.setMargins(0, 0, 0, 1);
            layout.addView(view, params);

            ImageView imageView = new ImageView(this);
            imageView.setBackgroundColor(getResources().getColor(R.color.white));
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 4);
            params1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layout.addView(imageView, params1);

            LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(width/2, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            layout.setLayoutParams(paramss);

            layout.setOnClickListener(layoutOnClickListener);
            layout.setTag(i);
            hsv_content.addView(layout);

            relativeLayouts.add(layout);
        }
        RelativeLayout relativeLayout1 = relativeLayouts.get(0);
        ImageView imageView1 = (ImageView) relativeLayout1.getChildAt(1);
        imageView1.setBackgroundColor(getResources().getColor(R.color.press_button_color));
        TextView textView1 = (TextView) relativeLayout1.getChildAt(0);
        textView1.setTextColor(getResources().getColor(R.color.press_button_color));
        propertyCountAdapter.setCount(labels.length);
        propertyCountAdapter.setStorageCounts(storageCountsList.get(0));
        mViewPager.setCurrentItem(0, true);
        switchTab(0);
    }
    //切换标签
    private void switchTab(int position) {
        //获取在当前窗口内的绝对坐标
        View view = hsv_content.getChildAt(position);
        int[] location = new int[2];
        view.getLocationInWindow(location);
        Log.e("", "view--->x坐标:" + location[0] + "view--->y坐标:" + location[1]);

        //获取屏幕x方向中间点的绝对坐标
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        Log.e("", "screenWidth:" + screenWidth);

        for(RelativeLayout relativeLayout : relativeLayouts) {
            ImageView imageView1 = (ImageView) relativeLayout.getChildAt(1);
            imageView1.setBackgroundColor(getResources().getColor(R.color.white));
            TextView textView1 = (TextView) relativeLayout.getChildAt(0);
            textView1.setTextColor(getResources().getColor(R.color.barcolor));
        }
        RelativeLayout relativeLayout1 = relativeLayouts.get(position);
        ImageView imageView1 = (ImageView) relativeLayout1.getChildAt(1);
        imageView1.setBackgroundColor(getResources().getColor(R.color.press_button_color));
        TextView textView1 = (TextView) relativeLayout1.getChildAt(0);
        textView1.setTextColor(getResources().getColor(R.color.press_button_color));
    }
}
