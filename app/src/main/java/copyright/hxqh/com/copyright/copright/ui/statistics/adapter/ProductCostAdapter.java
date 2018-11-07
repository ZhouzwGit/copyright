package copyright.hxqh.com.copyright.copright.ui.statistics.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import copyright.hxqh.com.copyright.R;

/**
 * Created by lianjh on 2018\11\6 0006.
 * Current page
 */

public class ProductCostAdapter extends RecyclerView.Adapter<ProductCostAdapter.ViewHolder>  {
    Context context;
    int width, height;
    ArrayList<Integer> earns_data;
    ArrayList<Integer> cost_data;
    ArrayList<String> text;
    ArrayList<Float> mRatios;
    public ProductCostAdapter(Context context, ArrayList<Integer> earns_data,ArrayList<Integer> cost_data, ArrayList<String> text,ArrayList<Float> mRatios) {
        this.context = context;
        this.earns_data = earns_data;
        this.cost_data = cost_data;
        this.text = text;
        this.mRatios = mRatios;
    }
    @Override
    public ProductCostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        ProductCostAdapter.ViewHolder viewHolder = new ProductCostAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductCostAdapter.ViewHolder holder, int position) {
        java.text.NumberFormat formate = java.text.NumberFormat.getNumberInstance();
        formate.setMaximumFractionDigits(2);
        holder.type.setText(text.get(position).replaceAll("\r\n",""));
        holder.cost.setText(String.valueOf(cost_data.get(position)));
        holder.earns.setText(String.valueOf(earns_data.get(position)));
        holder.precent.setText(String.valueOf(formate.format(mRatios.get(position)*100) +"%"));
    }

    @Override
    public int getItemCount() {
        if (earns_data == null){
            return 0;
        }else {
            return earns_data.size();
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView cost;
        TextView earns;
        TextView precent;

        public ViewHolder(View view) {
            super(view);
            type = (TextView)view.findViewById(R.id.type);
            cost = (TextView)view.findViewById(R.id.cost);
            earns = (TextView)view.findViewById(R.id.earns);
            precent = (TextView)view.findViewById(R.id.precent);
        }
    }
}