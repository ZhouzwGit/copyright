package copyright.hxqh.com.copyright.copright.ui.author.adpter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import copyright.hxqh.com.copyright.copright.ui.author.entity.RighterResRel;

/**
 * Created by zzw on 2018/9/18.
 */

public class RightAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;

    public RightAdapter(@NonNull Context context, int resource, @NonNull List<Right> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Right item = (Right) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        view.setBackgroundResource(R.color.white);
        viewHolder.resname = (TextView) view.findViewById(R.id.name_id);
        viewHolder.rightname = (TextView) view.findViewById(R.id.copyright_id);
        viewHolder.rightcost = (TextView) view.findViewById(R.id.cost_id);
        viewHolder.resname.setText(item.getResname());
        viewHolder.resname.setTextSize(12);
        viewHolder.rightname.setText(item.getRightname());
        viewHolder.rightname.setTextSize(12);
        viewHolder.rightcost.setText(item.getRightcost()+"");
        viewHolder.rightcost.setTextSize(12);
        viewHolder.rightname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(getContext(), RightDialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("right",item);
                intent.putExtras(bundle);
                getContext().startActivity(intent);*/
               RightDialogActivity rightDialogActivity = new RightDialogActivity(getContext(),item);
               rightDialogActivity.setCanceledOnTouchOutside(false);
               rightDialogActivity.show();
            }
        });
        return view;
    }
    public static class ViewHolder{
        public TextView resname;
        public TextView rightname;
        public TextView rightcost;
    }
}
