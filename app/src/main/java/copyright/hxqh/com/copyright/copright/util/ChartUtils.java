package copyright.hxqh.com.copyright.copright.util;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

public abstract class ChartUtils {

    public static final int DEFAULT_COLOR = Color.parseColor("#DFDFDF");
    public static final int DEFAULT_DARKEN_COLOR = Color.parseColor("#DDDDDD");
    public static final int DARKEN_COLOR = Color.parseColor("#84879A");
    public static final int COLOR_BLUE = Color.parseColor("#33B5E5");
    public static final int COLOR_VIOLET = Color.parseColor("#AA66CC");
    public static final int COLOR_GREEN = Color.parseColor("#99CC00");
    public static final int COLOR_ORANGE = Color.parseColor("#FFBB33");
    public static final int COLOR_1 = Color.parseColor("#8E8E38");
    public static final int COLOR_2 = Color.parseColor("#473C8B");
    public static final int COLOR_3 = Color.parseColor("#A4D3EE");
    public static final int COLOR_4 = Color.parseColor("#CDCD00");
    public static final int COLOR_5 = Color.parseColor("#B03060");
    public static final int COLOR_6 = Color.parseColor("#68838B");
    public static final int COLOR_7 = Color.parseColor("#E0FFFF");
    public static final int COLOR_8 = Color.parseColor("#A6D3EE");
    public static final int COLOR_9 = Color.parseColor("#C6CD00");
    public static final int COLOR_10 = Color.parseColor("#B83060");
    public static final int COLOR_11 = Color.parseColor("#63838B");
    public static final int COLOR_12 = Color.parseColor("#Ef4FFF");
    public static final int COLOR_13 = Color.parseColor("#a0F5FF");
    public static final int COLOR_14 = Color.parseColor("#b0FF7F");
    public static final int COLOR_15 = Color.parseColor("#c0FF9F");
    public static final int COLOR_RED = Color.parseColor("#dF4944");
    public static final int[] COLORS = new int[]{COLOR_BLUE, COLOR_VIOLET, COLOR_GREEN, COLOR_ORANGE, COLOR_RED, COLOR_1, COLOR_2, COLOR_3, COLOR_4, COLOR_5, COLOR_6
            , COLOR_7, COLOR_8, COLOR_9, COLOR_10, COLOR_11, COLOR_12, COLOR_13, COLOR_14, COLOR_15};
    private static final float DARKEN_SATURATION = 1.1f;
    private static final float DARKEN_INTENSITY = 0.9f;
    private static int COLOR_INDEX = 0;

    public static final int pickColor() {
        return COLORS[(int) Math.round(Math.random() * (COLORS.length - 1))];
    }

    public static final int nextColor() {
        if (COLOR_INDEX >= COLORS.length) {
            COLOR_INDEX = 0;
        }
        return COLORS[COLOR_INDEX++];
    }

    public static int dp2px(float density, int dp) {
        if (dp == 0) {
            return 0;
        }
        return (int) (dp * density + 0.5f);

    }

    public static int px2dp(float density, int px) {
        return (int) Math.ceil(px / density);
    }

    public static int sp2px(float scaledDensity, int sp) {
        if (sp == 0) {
            return 0;
        }
        return (int) (sp * scaledDensity + 0.5f);
    }

    public static int px2sp(float scaledDensity, int px) {
        return (int) Math.ceil(px / scaledDensity);
    }

    public static int mm2px(Context context, int mm) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, context.getResources()
                .getDisplayMetrics()) + 0.5f);
    }

    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        int alpha = Color.alpha(color);
        Color.colorToHSV(color, hsv);
        hsv[1] = Math.min(hsv[1] * DARKEN_SATURATION, 1.0f);
        hsv[2] = hsv[2] * DARKEN_INTENSITY;
        int tempColor = Color.HSVToColor(hsv);
        return Color.argb(alpha, Color.red(tempColor), Color.green(tempColor), Color.blue(tempColor));
    }

}
