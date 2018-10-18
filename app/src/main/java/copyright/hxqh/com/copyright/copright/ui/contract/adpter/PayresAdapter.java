package copyright.hxqh.com.copyright.copright.ui.contract.adpter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Payres;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class PayresAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private RightAdapter rightAdapter;
    private int resource;

    public PayresAdapter(@NonNull Context context, int resource, @NonNull List<Payres> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Payres item = (Payres) this.getItem(position);
        rightAdapter = new RightAdapter(getContext(),R.layout.table_title_layout,item.getRightList());
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        viewHolder.seq = (TextView)view.findViewById(R.id.seq_id);
        viewHolder.memo = (TextView)view.findViewById(R.id.memo_id);
        viewHolder.paycost = (TextView)view.findViewById(R.id.paycost_id);
        viewHolder.paymentmethod = (TextView)view.findViewById(R.id.paymentmethod_id);
        viewHolder.deadline = (TextView)view.findViewById(R.id.deadline_id);
        viewHolder.listView = (ListView) view.findViewById(R.id.rightlList);
        viewHolder.seq.setText("条目" + AcountUtil.toChinese(position+1+""));
        viewHolder.paycost.setText(item.getPaycost()+"元");
        viewHolder.paymentmethod.setText(item.getPaymethod());
        viewHolder.deadline.setText(item.getDeadline());
        viewHolder.memo.setText(item.getRemarks()+"");
        viewHolder.listView.setAdapter(rightAdapter);
        return view;
    }

    public static class ViewHolder {
        public TextView seq;
        public TextView memo;
        public TextView paycost;
        public TextView paymentmethod;
        public TextView deadline;
        public ListView listView;

    }
}
