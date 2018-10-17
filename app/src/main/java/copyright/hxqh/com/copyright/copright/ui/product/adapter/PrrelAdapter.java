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
        //viewHolder.languages.setText(item.get);
        viewHolder.copyright.setText(item.getCopyright());
        viewHolder.resourcename.setText(item.getResourcename());
        viewHolder.rightno.setText(item.getRightno());
        viewHolder.finishdate.setText(item.getStartdate() + "至" + item.getFinishdate());
        viewHolder.todetail = view.findViewById(R.id.detais_id);
        viewHolder.todetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CopyRightDetailsActivity.class);
                intent.putExtra("copyright",item);
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


    }
}
