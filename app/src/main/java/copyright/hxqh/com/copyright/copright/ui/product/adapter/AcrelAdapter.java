package copyright.hxqh.com.copyright.copright.ui.product.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import copyright.hxqh.com.copyright.copright.ui.product.ChannelActivity;
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
        final Acrel acrel = (Acrel) getItem(position);
        View view = (View) LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView cost = view.findViewById(R.id.cost_id);
        cost.setText(acrel.getCost()+"");
        TextView payment = view.findViewById(R.id.paymentmethod_id);
        payment.setText(acrel.getPayment());
        TextView channelname = view.findViewById(R.id.name_id);
        channelname.setText(acrel.getChannelname());
        TextView rightlimit = view.findViewById(R.id.rightlimit_id);
        rightlimit.setText(acrel.getStartdate()+"至"+acrel.getEnddate());
        TextView textView = view.findViewById(R.id.detais_id);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChannelActivity.class);
                intent.putExtra("acrel",acrel);
                getContext().startActivity(intent);
            }
        });
       /* TextView territoryrestrict = view.findViewById(R.id.territoryrestrict_id);
        territoryrestrict.setText(acrel.getTerritoryrestrict());
        TextView languagerestrict = view.findViewById(R.id.languagerestrict_id);
        languagerestrict.setText(acrel.getLanguagerestrict());
        TextView otherrestrict = view.findViewById(R.id.otherrestrict_id);
        otherrestrict.setText(acrel.getOtherrestrict());*/
        return view;
    }
}
