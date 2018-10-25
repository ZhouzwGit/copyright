package copyright.hxqh.com.copyright.copright.ui.IRM.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.RightInfo;
import copyright.hxqh.com.copyright.copright.ui.CopyRightDetailsActivity;

/**
 * Created by zzw on 2018/9/18.
 */

public class RightAdapter2 extends ArrayAdapter<RightInfo>{
    private LayoutInflater inflater;
    private int resource;

    public RightAdapter2(@NonNull Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    public RightAdapter2(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    public RightAdapter2(@NonNull Context context, int resource, @NonNull List<RightInfo> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    public RightAdapter2(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<RightInfo> objects) {
        super(context, resource, textViewResourceId, objects);
        this.resource = resource;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final RightInfo rightInfo = (RightInfo) getItem(i);
        view = inflater.inflate(resource, null);
        if (i==0){
            View view1 = view.findViewById(R.id.line_id);
            view1.setVisibility(View.GONE);
        }
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textView1 = view.findViewById(R.id.name_id);
        viewHolder.textView2 = view.findViewById(R.id.copyright_id);
        viewHolder.textView3 = view.findViewById(R.id.to_date_id);
        viewHolder.textView4 = view.findViewById(R.id.getType_id);
        viewHolder.textView5 = view.findViewById(R.id.rightkind_id);
        viewHolder.textView6 = view.findViewById(R.id.isrelease_id);
        viewHolder.textView7 = view.findViewById(R.id.finishplacearea_id);
        viewHolder.textView8 = view.findViewById(R.id.languages_id);
        //viewHolder.textView9 = view.findViewById(R.id.detais_id);
        viewHolder.textView1.setText(rightInfo.getCopyright());
        viewHolder.textView2.setText(rightInfo.getContractnum());
        viewHolder.textView3.setText(rightInfo.getStartdate() + "至" + rightInfo.getFinishdate());
        viewHolder.textView4 .setText(rightInfo.getGainway());
        viewHolder.textView5 .setText(rightInfo.getRightattribute());
        viewHolder.textView6 .setText(rightInfo.getPersonalproduct());
        viewHolder.textView7 .setText(rightInfo.getFinishplacearea());
        viewHolder.textView8.setText(rightInfo.getLanguages());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CopyRightDetailsActivity.class);
                intent.putExtra("copyright",rightInfo);
                getContext().startActivity(intent);

            }
        });

        return view;
    }
    public static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;
        public TextView textView7;
        public TextView textView8;
        public TextView textView9;
    }
}
