package copyright.hxqh.com.copyright.copright.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import copyright.hxqh.com.copyright.R;

/**
 * Created by zzw on 2018/10/9.
 */
@SuppressLint("AppCompatCustomView")
public class MyCircleImageView extends ImageView {
    private static final Xfermode MASK_XFERMODE;
    private Bitmap mask;
    private Paint paint;
    private int mBorderWidth = 10;
    private int mBorderColor = Color.parseColor("#f2f2f2");

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public MyCircleImageView(Context paramContext) {
        super(paramContext);
    }

    public MyCircleImageView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public MyCircleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        TypedArray a = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircularImage);
        mBorderColor = a.getColor(R.styleable.CircularImage_border_color, mBorderColor);
        final int defalut = (int) (2 * paramContext.getResources().getDisplayMetrics().density + 0.5f);
        mBorderWidth = a.getDimensionPixelOffset(R.styleable.CircularImage_border_width, defalut);
        a.recycle();
    }

    private boolean useDefaultStyle = false;

    public void setUseDefaultStyle(boolean useDefaultStyle) {
        this.useDefaultStyle = useDefaultStyle;
    }

    @Override
    protected void onDraw(Canvas paramCanvas) {
        if (useDefaultStyle) {
            super.onDraw(paramCanvas);
            return;
        }
        final Drawable localDrawable = getDrawable();
        if (localDrawable == null) return;
        if (localDrawable instanceof NinePatchDrawable) {
            return;
        }
        if (this.paint == null) {
            final Paint localPaint = new Paint();
            localPaint.setFilterBitmap(false);
            localPaint.setAntiAlias(true);
            localPaint.setXfermode(MASK_XFERMODE);
            this.paint = localPaint;
        }
        final int width = getWidth();
        final int height = getHeight();        /** 保存layer */
        @SuppressLint("WrongConstant") int layer = paramCanvas.saveLayer(0.0F, 0.0F, width, height, null, 31);        /** 设置drawable的大小 */
        localDrawable.setBounds(0, 0, width, height);        /** 将drawable绑定到bitmap(this.mask)上面（drawable只能通过bitmap显示出来） */
        localDrawable.draw(paramCanvas);
        if ((this.mask == null) || (this.mask.isRecycled())) {
            this.mask = createOvalBitmap(width, height);
        }        /** 将bitmap画到canvas上面 */
        paramCanvas.drawBitmap(this.mask, 0.0F, 0.0F, this.paint);        /** 将画布复制到layer上 */
        paramCanvas.restoreToCount(layer);
        drawBorder(paramCanvas, width, height);
    }

    /**
     * 绘制最外面的边框	 * 	 * @param canvas	 * @param width	 * @param height
     */
    private void drawBorder(Canvas canvas, final int width, final int height) {
        if (mBorderWidth == 0) {
            return;
        }
        final Paint mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);        /**         * 坐标x：view宽度的一般 坐标y：view高度的一般 半径r：因为是view的宽度-border的一半		 */
        canvas.drawCircle(width >> 1, height >> 1, (width - mBorderWidth) >> 1, mBorderPaint);
        canvas = null;
    }

    /**
     * 获取一个bitmap，目的是用来承载drawable;	 * <p>	 * 将这个bitmap放在canvas上面承载，并在其上面画一个椭圆(其实也是一个圆，因为width=height)来固定显示区域	 * 	 * @param width	 * @param height	 * @return
     */
    public Bitmap createOvalBitmap(final int width, final int height) {
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
        Bitmap localBitmap = Bitmap.createBitmap(width, height, localConfig);
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint();
        final int padding = mBorderWidth - 3;        /**         * 设置椭圆的大小(因为椭圆的最外边会和border的最外边重合的，如果图片最外边的颜色很深，有看出有棱边的效果，所以为了让体验更加好，		 * 让其缩进padding px)		 */
        RectF localRectF = new RectF(padding, padding, width - padding, height - padding);
        localCanvas.drawOval(localRectF, localPaint);
        return localBitmap;
    }
    public static final int GET_DATA_SUCCESS = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int SERVER_ERROR = 3;
    //子线程不能操作UI，通过Handler设置图片
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_DATA_SUCCESS:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    setImageBitmap(bitmap);
                    break;
                case NETWORK_ERROR:
                    Toast.makeText(getContext(),"网络连接失败",Toast.LENGTH_SHORT).show();
                    break;
                case SERVER_ERROR:
                    Toast.makeText(getContext(),"服务器发生错误",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    //设置网络图片
    public void setImageURL(final String path) {
        String fullPath = URLEncoder.encode(path);
        fullPath = fullPath.replace("%3A",":");
        fullPath = fullPath.replace("%2F","/");
        fullPath = "http://118.190.115.150:8889" +fullPath;
        //开启一个线程用于联网
        final String finalFullPath = fullPath;
        new Thread() {
            @Override
            public void run() {
                try {
                    //把传过来的路径转成URL
                   /* String urlpath = java.net.URLEncoder.encode(path);*/
                    URL url = new URL(finalFullPath);
                    //获取连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //使用GET方法访问网络
                    connection.setRequestMethod("GET");
                    //超时时间为10秒
                    connection.setConnectTimeout(10000);
                    //获取返回码
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        //使用工厂把网络的输入流生产Bitmap
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        //利用Message把图片发给Handler
                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        msg.what = GET_DATA_SUCCESS;
                        handler.sendMessage(msg);
                        inputStream.close();
                    }else {
                        //服务启发生错误
                        handler.sendEmptyMessage(SERVER_ERROR);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    //网络连接错误
                    handler.sendEmptyMessage(NETWORK_ERROR);
                }
            }
        }.start();
    }
}
