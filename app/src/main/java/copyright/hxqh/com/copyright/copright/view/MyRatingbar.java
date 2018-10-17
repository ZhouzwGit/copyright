package copyright.hxqh.com.copyright.copright.view;


import android.content.Context;
import android.widget.ImageView;

import com.example.xlhratingbar_lib.IRatingView;

import copyright.hxqh.com.copyright.R;

/**
 * Created by zzw on 2018/9/18.
 */

public class MyRatingbar implements IRatingView {
    @Override
    public int getStateRes(int posi, int state) {
        int id = R.drawable.ic_dackstar;
        switch (state) {
            case STATE_NONE:
                id = R.drawable.ic_dackstar;
                break;
            case STATE_FULL:
                id = R.drawable.ic_shaningstar;
                break;
            default:
                break;
        }
        return id;
    }

    @Override
    public int getCurrentState(float rating, int numStars, int position) {

        return (int) rating;
    }


    @Override
    public ImageView getRatingView(Context context, int numStars, int posi) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
