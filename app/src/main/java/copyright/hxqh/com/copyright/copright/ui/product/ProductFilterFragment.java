package copyright.hxqh.com.copyright.copright.ui.product;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class ProductFilterFragment extends Fragment implements View.OnClickListener{
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;
    private StringBuffer productusages = new StringBuffer();
    private HashMap<Integer,String> stringMap = new HashMap<>();
    private EditText startDate,endDate;
    private Button reset,confirm;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    private RadioGroup near;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.productfilter, null);
        initView(view);
        listterner = (FragmentInteraction) getActivity();
        return view;
    }

    private void initView(View view) {
        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);
        mDrawerContent = getActivity().findViewById(R.id.drawer_content);
        startDate = view.findViewById(R.id.from_date_id);
        startDate.setOnClickListener(this);
        endDate = view.findViewById(R.id.to_date_id);
        endDate.setOnClickListener(this);
        reset = view.findViewById(R.id.reset_id);
        reset.setOnClickListener(this);
        confirm = view.findViewById(R.id.confirm_id);
        confirm.setOnClickListener(this);
        checkBox1 = view.findViewById(R.id.self_id);
        checkBox2 = view.findViewById(R.id.proprietarylicense_id);
        checkBox3 = view.findViewById(R.id.transfer_id);
        checkBox4 = view.findViewById(R.id.nonexclusivelicense_id);
        checkBox5 = view.findViewById(R.id.pledge_id);
        near = view.findViewById(R.id.neardate_id);
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
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
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
                if (checkBox1.isChecked()){
                    productusages.append(checkBox1.getText().toString()).append("#");
                };
                if (checkBox2.isChecked()){
                    productusages.append(checkBox2.getText().toString()).append("#");
                }
                if (checkBox3.isChecked()){
                    productusages.append(checkBox3.getText().toString()).append("#");
                }
                if (checkBox4.isChecked()){
                    productusages.append(checkBox4.getText().toString()).append("#");
                }
                if (checkBox5.isChecked()){
                    productusages.append(checkBox5.getText().toString()).append("#");
                }
                if (productusages.length()!=0){
                    String productusage = productusages.substring(0,productusages.length()-1);
                    map.put("productusage",productusage);
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
        void process(Map<String, String> map);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }
}
