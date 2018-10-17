package copyright.hxqh.com.copyright.copright.ui.product.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.RightDialogActivity;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Right;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prmtrel;

/**
 * Created by zzw on 2018/9/18.
 */

public class PrmtrelAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;

    public PrmtrelAdapter(@NonNull Context context, int resource, @NonNull List<Prmtrel> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Prmtrel item = (Prmtrel) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        view.setBackgroundResource(R.color.white);
        viewHolder.line = (TextView) view.findViewById(R.id.name_id);
        viewHolder.resourceno = (TextView) view.findViewById(R.id.copyright_id);
        viewHolder.resourcename = (TextView) view.findViewById(R.id.cost_id);
        viewHolder.resourceno.setText(item.getResourceno());
        viewHolder.resourceno.setTextSize(12);
        viewHolder.line.setText(position+1+"");
        viewHolder.line.setTextSize(12);
        viewHolder.resourcename.setText(item.getResourcename());
        viewHolder.resourcename.setTextSize(12);
        return view;
    }
    public static class ViewHolder{
        public TextView line;
        public TextView resourceno;
        public TextView resourcename;
    }
}
