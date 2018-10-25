package copyright.hxqh.com.copyright.copright.ui.publicService.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.Bind;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.publicService.LawVinDicateDetailActivity;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Obligeeinfo;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Tortinfo;
import copyright.hxqh.com.copyright.copright.util.StockUtil;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class TortinfoAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;
    Context context;
    public TortinfoAdapter(@NonNull Context context, int resource, List<Tortinfo> textViewResourceId) {
        super(context, resource, textViewResourceId);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tortinfo item = (Tortinfo) this.getItem(position);
        TortinfoAdapter.ViewHolder viewHolder;
        viewHolder = new TortinfoAdapter.ViewHolder();
        View view = inflater.inflate(resource, null);
        viewHolder.lawvindicateTortpeople = (TextView)view.findViewById(R.id.lawvindicate_tortpeople);
        viewHolder.lawvindicateTorttype = (TextView)view.findViewById(R.id.lawvindicate_torttype);
        viewHolder.lawvindicateRemarks = (TextView)view.findViewById(R.id.lawvindicate_remarks);
        viewHolder.lawvindicateTortURL = (TextView)view.findViewById(R.id.lawvindicate_tortURL);

        viewHolder.lawvindicateTortpeople.setText(item.getTortname());
        viewHolder.lawvindicateTorttype.setText(item.getTorttype());
        viewHolder.lawvindicateRemarks.setText(item.getTortnote());
        String str1 = item.getTorturl();
        String[] splitstr=str1.split("/");
        return view;
    }

    public static class ViewHolder {
        public TextView lawvindicateTortpeople;
        public TextView lawvindicateTorttype;
        public TextView lawvindicateTortURL;
        public TextView lawvindicateRemarks;
    }
}
