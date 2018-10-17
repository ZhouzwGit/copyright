package copyright.hxqh.com.copyright.copright.ui.product.adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.product.entity.ProductInfoCollect;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prrel;

/**
 * Created by zzw on 2018/9/18.
 */

public class ProductSaleAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;

    public ProductSaleAdapter(@NonNull Context context, int resource, @NonNull List<ProductInfoCollect> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProductInfoCollect item = (ProductInfoCollect) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        viewHolder.productusage = (TextView) view.findViewById(R.id.productusage_id);
        viewHolder.productname = (TextView) view.findViewById(R.id.productname_id);
        viewHolder.productname.setMovementMethod(ScrollingMovementMethod.getInstance());
        viewHolder.productname.setHorizontallyScrolling(true);
        viewHolder.productname.setFocusable(true);
        viewHolder.productincome = (TextView) view.findViewById(R.id.productincome_id);
        viewHolder.status = (TextView) view.findViewById(R.id.status_id);
        viewHolder.period = view.findViewById(R.id.period_id);
        viewHolder.productname.setText(item.getProductname());
        viewHolder.productincome.setText(item.getPrice());
        viewHolder.status.setText(item.getCollectionstatus());
        viewHolder.period.setText(item.getLifetime().split(" ")[0]+ "至" + item.getLifetimeto().split(" ")[0]);
        return view;
    }
    public static class ViewHolder{
        public TextView productusage;
        public TextView productname;
        public TextView productincome;
        public TextView status;
        public TextView period;
    }
}
