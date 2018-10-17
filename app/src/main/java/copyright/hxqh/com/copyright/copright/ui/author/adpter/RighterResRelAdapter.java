package copyright.hxqh.com.copyright.copright.ui.author.adpter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.entity.AuthorResRel;
import copyright.hxqh.com.copyright.copright.ui.author.entity.RighterResRel;

/**
 * Created by zzw on 2018/9/18.
 */

public class RighterResRelAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;

    public RighterResRelAdapter(@NonNull Context context, int resource, @NonNull List<RighterResRel> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RighterResRel item = (RighterResRel) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        view.setBackgroundResource(R.color.white);
        viewHolder.assetnum = (TextView) view.findViewById(R.id.assetnum_id);
        viewHolder.productname = (TextView) view.findViewById(R.id.producname_id);
        viewHolder.rightinfo = (TextView) view.findViewById(R.id.rightinfo_id);
        viewHolder.assetstatus = (TextView) view.findViewById(R.id.assetstatus_id);
        viewHolder.assetnum.setText(item.getResourceno());
        viewHolder.assetnum.setTextSize(12);
        viewHolder.productname.setText(item.getResourcename());
        viewHolder.productname.setTextSize(12);
        viewHolder.rightinfo.setText(item.getResourcekind());
        viewHolder.rightinfo.setTextSize(12);
        viewHolder.assetstatus.setText(item.getStatus());
        viewHolder.assetstatus.setTextSize(12);
        return view;
    }
    public static class ViewHolder{
        public TextView assetnum;
        public TextView productname;
        public TextView rightinfo;
        public TextView assetstatus;
    }
}
