package copyright.hxqh.com.copyright.copright.custom;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.view.LazyViewPager;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MyOnPageChangeListener1 implements LazyViewPager.OnPageChangeListener, MyOnPageChangeListenerInterface1{
    private HorizontalScrollView mHorizontalScrollView ;
    private LazyViewPager pager;
    private ImageView mImageView;
    private int item_width;

    private int endPosition;
    private int beginPosition;
    private int currentFragmentIndex;
    private boolean isEnd;
    ArrayList<RelativeLayout> relativeLayouts = new ArrayList<RelativeLayout>();
    Context context;
    ProductSelectCallBack productSelectCallBack;

    public MyOnPageChangeListener1(ArrayList<RelativeLayout> relativeLayouts, Context context){
        this.relativeLayouts = relativeLayouts;
        this.context = context;
    }

    @Override
    public void onPageSelected(final int position) {
        Animation animation = new TranslateAnimation(endPosition, position* item_width, 0, 0);

        beginPosition = position * item_width;

        currentFragmentIndex = position;
        if (animation != null) {
            animation.setFillAfter(true);
            animation.setDuration(0);
//            mImageView.startAnimation(animation);
            mHorizontalScrollView.smoothScrollTo((currentFragmentIndex - 1) * item_width , 0);
        }

        int index = position;
        for(RelativeLayout relativeLayout : relativeLayouts) {
            ImageView imageView1 = (ImageView) relativeLayout.getChildAt(1);
            imageView1.setBackgroundColor(context.getResources().getColor(R.color.press_button_color));
            TextView textView1 = (TextView) relativeLayout.getChildAt(0);
            textView1.setTextColor(context.getResources().getColor(R.color.barcolor));
        }
        RelativeLayout relativeLayout1 = relativeLayouts.get(position);
        ImageView imageView1 = (ImageView) relativeLayout1.getChildAt(1);
        imageView1.setBackgroundColor(context.getResources().getColor(R.color.white));
        TextView textView1 = (TextView) relativeLayout1.getChildAt(0);
        textView1.setTextColor(context.getResources().getColor(R.color.press_button_color));
        productSelectCallBack.deal(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        if(!isEnd){
            if(currentFragmentIndex == position){
                endPosition = item_width * currentFragmentIndex +
                        (int)(item_width * positionOffset);
            }
            if(currentFragmentIndex == position+1){
                endPosition = item_width * currentFragmentIndex -
                        (int)(item_width * (1-positionOffset));
            }

            Animation mAnimation = new TranslateAnimation(beginPosition, endPosition, 0, 0);
            mAnimation.setFillAfter(true);
            mAnimation.setDuration(0);
//            mImageView.startAnimation(mAnimation);
            mHorizontalScrollView.invalidate();
            beginPosition = endPosition;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == LazyViewPager.SCROLL_STATE_DRAGGING) {
            isEnd = false;
        } else if (state == LazyViewPager.SCROLL_STATE_SETTLING) {
            isEnd = true;
            beginPosition = currentFragmentIndex * item_width;
            if (pager.getCurrentItem() == currentFragmentIndex) {
                // 未跳入下一个页面
//                mImageView.clearAnimation();
                Animation animation = null;
                // 恢复位置
                animation = new TranslateAnimation(endPosition, currentFragmentIndex * item_width, 0, 0);
                animation.setFillAfter(true);
                animation.setDuration(1);
//                mImageView.startAnimation(animation);
                mHorizontalScrollView.invalidate();
                endPosition = currentFragmentIndex * item_width;
            }
        }
    }

    @Override
    public void init(int item_width, ImageView mImageView, HorizontalScrollView mHorizontalScrollView, LazyViewPager pager, ProductSelectCallBack productSelectCallBack) {
        this.mHorizontalScrollView = mHorizontalScrollView;
        this.item_width = item_width;
        this.mImageView = mImageView;
        this.pager = pager;
        this.productSelectCallBack = productSelectCallBack;
    }
}
