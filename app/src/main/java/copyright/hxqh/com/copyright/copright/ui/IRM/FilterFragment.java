package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Resourcekind;
import copyright.hxqh.com.copyright.copright.util.DateSelect;

/**
 * 作者：chs on 2016/10/10 10:07
 * 邮箱：657083984@qq.com
 */

public class FilterFragment extends Fragment implements View.OnClickListener{
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;
    private GridView gridView;
    private SimpleAdapter adapter;
    private StringBuffer resouseKinds = new StringBuffer();
    private HashMap<Integer,String> stringMap = new HashMap<>();
    private EditText startDate,endDate;
    private Button reset,confirm;
    private CheckBox checkBox1,checkBox2,checkBox3;
    private RadioGroup near;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assetfilter, null);
        initView(view);
        listterner = (FragmentInteraction) getActivity();
        return view;
    }

    private void initView(View view) {
        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);
        mDrawerContent = getActivity().findViewById(R.id.drawer_content);
        gridView = view.findViewById(R.id.gv_patrol_item);
        startDate = view.findViewById(R.id.from_date_id);
        startDate.setOnClickListener(this);
        endDate = view.findViewById(R.id.to_date_id);
        endDate.setOnClickListener(this);
        reset = view.findViewById(R.id.reset_id);
        reset.setOnClickListener(this);
        confirm = view.findViewById(R.id.confirm_id);
        confirm.setOnClickListener(this);
        checkBox1 = view.findViewById(R.id.due_id);
        checkBox2 = view.findViewById(R.id.wrong_id);
        checkBox3 = view.findViewById(R.id.history_id);
        near = view.findViewById(R.id.neardate_id);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (stringMap.containsKey(i)){
                    view.setBackgroundResource(R.drawable.shape_patrol_corners_gray);
                    stringMap.remove(i);
                }else {
                    view.setBackgroundResource(R.drawable.shape_patrol_corners_blue);
                    TextView textView = view.findViewById(R.id.text);
                    stringMap.put(i,textView.getText().toString());
                }
                System.out.println(i+ "====" +l);
            }
        });
        getKind();
    }
    public void getKind(){
        new AsyncTask<String,String,List<Resourcekind>>(){
            @Override
            protected List<Resourcekind> doInBackground(String... strings) {
                return HttpConnect.getResourcekind();
            }

            @Override
            protected void onPostExecute(List<Resourcekind> resourcekinds) {
                super.onPostExecute(resourcekinds);
                List<Map<String,String>> dataList = new ArrayList<>();
                for (Resourcekind resourcekind:resourcekinds) {
                    Map<String, String> map=new HashMap<String, String>();
                    map.put("text",resourcekind.getResourcekind());
                    dataList.add(map);
                }
                String[] from = {"text"};
                int[] to = {R.id.text};
                adapter = new SimpleAdapter(FilterFragment.this.getActivity(),dataList,R.layout.gridview_item,from,to);
                gridView.setAdapter(adapter);
            }
        }.execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.from_date_id:
                new DateSelect(getActivity(),startDate).showDialog();
                break;
            case R.id.to_date_id:
                new DateSelect(getActivity(),endDate).showDialog();
                break;
            case R.id.reset_id:
                for (int i=0;i<gridView.getChildCount();i++){
                    gridView.getChildAt(i).setBackgroundResource(R.drawable.shape_patrol_corners_gray);
                }
                stringMap = new HashMap<>();
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                near.clearCheck();
                startDate.setText("");
                endDate.setText("");
                break;
            case R.id.confirm_id:
                Map<String,String> map = new HashMap<>();
                RadioButton radioButton = near.findViewById(near.getCheckedRadioButtonId());
                String data = null;
                if (radioButton!=null){
                    data = radioButton.getText().toString();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                if ("最近1年".equals(data)){
                    calendar.add(Calendar.YEAR,-1);
                    Date last1 = calendar.getTime();
                    String date = format.format(last1)+ "&" + format.format(new Date());
                    map.put("createDate",date);
                }else if ("最近3年".equals(data)){
                    calendar.add(Calendar.YEAR,-3);
                    Date last1 = calendar.getTime();
                    String date = format.format(last1)+ "&" + format.format(new Date());
                    map.put("createDate",date);
                }else if ("最近5年".equals(data)){
                    calendar.add(Calendar.YEAR,-5);
                    Date last1 = calendar.getTime();
                    String date = format.format(last1)+ "&" + format.format(new Date());
                    map.put("createDate",date);
                }else if (startDate.getTextSize() > 0){
                    String date = startDate.getText().toString() + "&" + endDate.getText().toString();
                    map.put("createDate",date);
                }

                if (!stringMap.isEmpty()){
                    Iterator iterator = stringMap.keySet().iterator();
                    while (iterator.hasNext()){
                        int key = (Integer) iterator.next();
                        resouseKinds .append(stringMap.get(key)).append("#");
                    }
                    String resourcekind = resouseKinds.toString().substring(0,resouseKinds.length()-1);
                    map.put("resourceKind",resouseKinds.toString());
                }
                listterner.process(map);
                mDrawerLayout.closeDrawer(mDrawerContent);
                break;
        }
    }
    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private FragmentInteraction listterner;

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        void process(Map<String,String> map);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }
}
