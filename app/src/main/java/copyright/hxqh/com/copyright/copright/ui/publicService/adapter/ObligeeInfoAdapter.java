package copyright.hxqh.com.copyright.copright.ui.publicService.adapter;

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
import copyright.hxqh.com.copyright.copright.ui.author.adpter.PayresAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.RightAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Payres;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Obligeeinfo;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.util.StockUtil;

/**
 * Created by lianjh on 2018\10\17 0017.
 * Current page
 */

public class ObligeeInfoAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;
    Context context;
    public ObligeeInfoAdapter(@NonNull Context context, int resource, List<Obligeeinfo> textViewResourceId) {
        super(context, resource, textViewResourceId);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Obligeeinfo item = (Obligeeinfo) this.getItem(position);
        ObligeeInfoAdapter.ViewHolder viewHolder;
        viewHolder = new ObligeeInfoAdapter.ViewHolder();
        View view = inflater.inflate(resource, null);
        viewHolder.lawauthor = (TextView)view.findViewById(R.id.lawvindicate_lawauthor);
        viewHolder.lawpeople = (TextView)view.findViewById(R.id.lawvindicate_lawpeople);
        viewHolder.id_file = (TextView)view.findViewById(R.id.lawvindicate_id_file);
        viewHolder.lawauthor.setText(item.getName());
        viewHolder.lawpeople.setText(item.getTortcategory());
        String str1 = item.getIdentification();
        String[] splitstr=str1.split("/");
        viewHolder.id_file.setText(splitstr[splitstr.length - 1]);
        viewHolder.id_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StockUtil().downLoadFile(context, item.getIdentification());
            }
        });
        return view;
    }

    public static class ViewHolder {
        public TextView lawauthor;
        public TextView lawpeople;
        public TextView id_file;
    }
}
