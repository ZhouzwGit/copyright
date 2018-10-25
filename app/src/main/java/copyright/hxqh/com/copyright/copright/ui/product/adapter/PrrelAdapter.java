package copyright.hxqh.com.copyright.copright.ui.product.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.copright.ui.CopyRightDetailsActivity;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prrel;

/**
 * Created by zzw on 2018/9/18.
 */

public class PrrelAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;

    public PrrelAdapter(@NonNull Context context, int resource, @NonNull List<Prrel> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Prrel item = (Prrel) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        if (position==0){
            View view1 = view.findViewById(R.id.line_id);
            view1.setVisibility(View.GONE);
        }
        view.setBackgroundResource(R.color.white);
       /* viewHolder.resourceno = view.findViewById(R.id.);
        viewHolder.status= view.findViewById(R.id.);*/
        viewHolder.resourcename= view.findViewById(R.id.name_id);
        viewHolder.copyright= view.findViewById(R.id.copyrihtname_id);
        viewHolder.rightno= view.findViewById(R.id.copyright_id);
        viewHolder.finishdate= view.findViewById(R.id.to_date_id);
        viewHolder.finishplacearea =view.findViewById(R.id.district_id);
        viewHolder.finishplacearea.setText(item.getFinishplacearea());
        viewHolder.languages =view.findViewById(R.id.languages_id);
        viewHolder.resourcekind = view.findViewById(R.id.type_id);
        viewHolder.gainway = view.findViewById(R.id.getType_id);
        viewHolder.resourcekind.setText(item.getResourcekind());
        viewHolder.gainway.setText(item.getGainway());
        viewHolder.languages.setText(item.getLanguages());
        viewHolder.copyright.setText(item.getCopyright());
        viewHolder.resourcename.setText(item.getResourcename());
        viewHolder.rightno.setText(item.getRightno());
        viewHolder.finishdate.setText(item.getStartdate() + "至" + item.getFinishdate());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CopyRightDetailsActivity.class);
                intent.putExtra("copyright",item);
                intent.putExtra("type",1);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    public static class ViewHolder {
        public TextView resourceno;
        public TextView status;
        public TextView resourcename;
        public TextView copyright;
        public TextView rightno;
        public TextView languages;
        public TextView finishdate;
        public TextView finishplacearea;
        public TextView todetail;
        public TextView resourcekind;
        public TextView gainway;


    }
}
