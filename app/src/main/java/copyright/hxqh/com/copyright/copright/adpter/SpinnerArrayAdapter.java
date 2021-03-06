package copyright.hxqh.com.copyright.copright.adpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class SpinnerArrayAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private ArrayList<String> mStringArray;

//    public SpinnerArrayAdapter(Context context, String[] stringArray) {
//        super(context, android.R.layout.simple_spinner_item, stringArray);
//        mContext = context;
//        mStringArray = stringArray;
//    }

    public SpinnerArrayAdapter(Context context, ArrayList<String> stringArray) {
        super(context, android.R.layout.simple_spinner_item, stringArray);
        mContext = context;
        mStringArray = stringArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //修改Spinner展开后的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(mStringArray.get(position));
        tv.setTextSize(18f);
//        tv.setTextColor(Color.RED);

        return convertView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 修改Spinner选择后结果的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(mStringArray.get(position));
        tv.setTextSize(16f);
//        tv.setTextColor(Color.BLUE);
        return convertView;
    }

}
