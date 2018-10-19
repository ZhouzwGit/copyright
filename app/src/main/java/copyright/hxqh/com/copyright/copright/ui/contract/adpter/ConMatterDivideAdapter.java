package copyright.hxqh.com.copyright.copright.ui.contract.adpter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.ConMatterDivide;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.ConRightDivide;

/**
 * Created by zzw on 2018/9/18.
 */

public class ConMatterDivideAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;
    public ConMatterDivideAdapter(@NonNull Context context, int resource, @NonNull List<ConMatterDivide> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConMatterDivide item = (ConMatterDivide) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        View tableview = view.findViewById(R.id.table1);
        View view1 = view.findViewById(R.id.tabletitle);
        tableview.setBackgroundResource(R.color.white);
        viewHolder.resourcename = view.findViewById(R.id.resourcename_id);
        viewHolder.copyright = tableview.findViewById(R.id.colnum1);
        viewHolder.personalproduct = tableview.findViewById(R.id.colnum2);
        viewHolder.rtmoney = tableview.findViewById(R.id.colnum3);
        viewHolder.rtratio = tableview.findViewById(R.id.colnum4);
        viewHolder.resourcename.setText(item.getResourcename());
        viewHolder.copyright.setTextSize(12);
        viewHolder.personalproduct.setTextSize(12);
        viewHolder.rtmoney.setTextSize(12);
        viewHolder.rtratio.setTextSize(12);
        viewHolder.copyright.setGravity(Gravity.LEFT);
        viewHolder.personalproduct.setGravity(Gravity.LEFT);
        viewHolder.rtmoney.setGravity(Gravity.LEFT);
        viewHolder.rtratio.setGravity(Gravity.LEFT);
        viewHolder.copyright.setText("_");
        viewHolder.personalproduct.setText("_");
        viewHolder.rtmoney.setText(item.getRtmoney()+"元");
        viewHolder.rtratio.setText(item.getRtratio()+"%");
        return view;
    }

    public static class ViewHolder {
        public TextView resourcename;
        public TextView copyright;
        public TextView personalproduct;
        public TextView rtmoney;
        public TextView rtratio;

    }
}
