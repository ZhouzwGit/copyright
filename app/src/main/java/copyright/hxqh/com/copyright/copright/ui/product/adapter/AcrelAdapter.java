package copyright.hxqh.com.copyright.copright.ui.product.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Acrel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

/**
 * Created by zzw on 2018/9/18.
 */

public class AcrelAdapter extends ArrayAdapter {
    private int resourceId;
    public AcrelAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Acrel> objects) {
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Acrel acrel = (Acrel) getItem(position);
        View view = (View) LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView cost = view.findViewById(R.id.cost_id);
        cost.setText(acrel.getCost()+"");
        TextView payment = view.findViewById(R.id.paymentmethod_id);
        payment.setText(acrel.getPayment());
        TextView channelname = view.findViewById(R.id.name_id);
        channelname.setText(acrel.getChannelname());
        TextView rightlimit = view.findViewById(R.id.rightlimit_id);
        rightlimit.setText(acrel.getStartdate()+"至"+acrel.getEnddate());
       /* TextView territoryrestrict = view.findViewById(R.id.territoryrestrict_id);
        territoryrestrict.setText(acrel.getTerritoryrestrict());
        TextView languagerestrict = view.findViewById(R.id.languagerestrict_id);
        languagerestrict.setText(acrel.getLanguagerestrict());
        TextView otherrestrict = view.findViewById(R.id.otherrestrict_id);
        otherrestrict.setText(acrel.getOtherrestrict());*/
        return view;
    }
    /*protected void convert(BaseViewHolder helper, Acrel item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.productcost_id,item.getCost()+"");
        helper.setText(R.id.payment_id,item.getPayment());
        helper.setText(R.id.channelname_id,item.getChannelname());
        helper.setText(R.id.rightlimit_id,item.getStartdate() + "至" + item.getEnddate());
        helper.setText(R.id.territoryrestrict_id,item.getTerritoryrestrict());
        helper.setText(R.id.languagerestrict_id,item.getLanguagerestrict());
        helper.setText(R.id.otherrestrict_id,item.getOtherrestrict());
    }*/
}
