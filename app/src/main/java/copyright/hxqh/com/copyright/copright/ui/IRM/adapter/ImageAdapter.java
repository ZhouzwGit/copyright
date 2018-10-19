package copyright.hxqh.com.copyright.copright.ui.IRM.adapter;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.InputStream;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class ImageAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;
    private Dialog dialog;

    public ImageAdapter(@NonNull Context context, int resource, @NonNull List<String> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = (String) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        viewHolder.imageView = view.findViewById(R.id.image);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        AcountUtil.imageShow2(viewHolder.imageView,item);
        return view;
    }
    public static class ViewHolder {
        public ImageView imageView;
    }
    private void init(ImageView imageView,View view) {
        imageView = (ImageView) view.findViewById(R.id.image);
        //大图所依附的dialog
        dialog = new Dialog(getContext(), R.style.AlertDialog_AppCompat_Light);
        ImageView mImageView = getImageView();
        dialog.setContentView(mImageView);

        //大图的点击事件（点击让他消失）
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    private ImageView getImageView(){
        ImageView iv = new ImageView(getContext());
        //宽高
        iv.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //设置Padding
        iv.setPadding(20,20,20,20);
        return iv;
    }
}
