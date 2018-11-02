package copyright.hxqh.com.copyright.copright.ui.statistics.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResCostingByKind;
import copyright.hxqh.com.copyright.copright.util.ChartUtils;

/**
 * Created by lianjh on 2018\11\1 0001.
 * Current page
 */

public class ResCostAdapter extends RecyclerView.Adapter<ResCostAdapter.ViewHolder>  {
    Context context;
    int width, height;
    ArrayList<String> data;
    ArrayList<String> text;
    ArrayList<Float> mRatios;
    public ResCostAdapter(Context context, ArrayList<String> data, ArrayList<String> text,ArrayList<Float> mRatios) {
        this.context = context;
        this.data = data;
        this.text = text;
        this.mRatios = mRatios;
    }
    @Override
    public ResCostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResCostAdapter.ViewHolder holder, int position) {
        holder.type.setText(text.get(position).replaceAll("\n",""));
        holder.count.setText(data.get(position));
        holder.precent.setText(String.valueOf(mRatios.get(position)*100 +"%"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView count;
        TextView precent;

        public ViewHolder(View view) {
            super(view);
            type = (TextView)view.findViewById(R.id.type);
            count = (TextView)view.findViewById(R.id.count);
            precent = (TextView)view.findViewById(R.id.precent);
        }
    }
}
