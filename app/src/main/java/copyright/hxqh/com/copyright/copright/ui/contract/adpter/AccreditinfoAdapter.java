package copyright.hxqh.com.copyright.copright.ui.contract.adpter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Accreditinfo;

/**
 * Created by zzw on 2018/9/18.
 */

public class AccreditinfoAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;

    public AccreditinfoAdapter(@NonNull Context context, int resource, @NonNull List<Accreditinfo> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Accreditinfo item = (Accreditinfo) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        view.setBackgroundResource(R.color.white);
        viewHolder.colnum1 = view.findViewById(R.id.colnum1);
        viewHolder.colnum2 = view.findViewById(R.id.colnum2);
        viewHolder.colnum3 = view.findViewById(R.id.colnum3);
        viewHolder.colnum4 = view.findViewById(R.id.colnum4);
        viewHolder.colnum1.setText(item.getProductname());
        viewHolder.colnum2.setText(item.getCost());
        viewHolder.colnum3.setText(item.getPayment());
        viewHolder.colnum4.setText(item.getRemarks());
        viewHolder.colnum1.setTextSize(12);
        viewHolder.colnum2.setTextSize(12);
        viewHolder.colnum3.setTextSize(12);
        viewHolder.colnum4.setTextSize(12);
        return view;
    }

    public static class ViewHolder {
        public TextView colnum1;
        public TextView colnum2;
        public TextView colnum3;
        public TextView colnum4;
    }
}
