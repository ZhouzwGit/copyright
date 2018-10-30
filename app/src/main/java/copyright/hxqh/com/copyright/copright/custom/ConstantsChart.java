package copyright.hxqh.com.copyright.copright.custom;

import lecho.lib.hellocharts.model.ValueShape;

/**
 * Created by wxs on 2017/12/4.
 */
public class ConstantsChart {
    public static int numberOfLines = 1;
    public static int maxNumberOfLines = 4;
    public static int numberOfPoints = 12;
    public static boolean hasLines = true;
    public static boolean hasPoints = true;
    public static ValueShape shape = ValueShape.CIRCLE;
    public static boolean isFilled = false;
    public static boolean isCubic = true;
    public static boolean pointsHaveDifferentColor;
    public static boolean hasGradientToTransparent = false;

    //柱状图
    public static boolean hasAxes = true;
    public static boolean hasAxesNames = true;
    public static boolean hasLabels = true;
    public static boolean hasLabelForSelected = true;

    //饼状图
    public static boolean hasAxesPie = true;
    public static boolean hasAxesNamesPie = true;
    public static boolean hasLabelsPie = true;
    public static boolean hasLabelForSelectedPie = true;
    public static boolean hasLabelsOutsidePie = true;
    public static boolean hasCenterCirclePie = true;

    //首页折线图
    /**
     * 设置cubic线条：true代表曲状，false代表直线
     */
    public static boolean CubicShouYe = false;
    /**
     * 设置pointcolor点的颜色：true代表设置，false代表不设置，默认与线条颜色相同
     */
    public static boolean PointColorShouYe = true;
    /**
     * 设置isFilled区域的颜色：true代表设置，false代表不设置
     */
    public static boolean isFilledShouYe = true;

    /**
     * 设置可以点击显示point的label hasLabelForSelectedShouYe = true， hasLabelShouYe = false；直接设置point显示数据hasLabelForSelectedShouYe = false， hasLabelShouYe = true
     */
    public static boolean hasLabelForSelectedShouYe = false;
    public static boolean hasLabelShouYe = true;

    //普通主折线图
    /**
     * 设置可以点击显示point的label hasLabelForSelectedShouYe = true， hasLabelShouYe = false；直接设置point显示数据hasLabelForSelectedShouYe = false， hasLabelShouYe = true
     */
    public static boolean hasLabelForSelectedGeneral = true;
    public static boolean hasLabelGeneral = false;
    /**
     * 设置cubic线条：true代表曲状，false代表直线
     */
    public static boolean CubicGeneral = false;

    //普通子折线图
    /**
     * 设置可以点击显示point的label hasLabelForSelectedChild = true， hasLabelChild = false；直接设置point显示数据hasLabelForSelectedChild = false， hasLabelShouYe = true
     */
    public static boolean hasLabelForSelectedChild = true;
    public static boolean hasLabelChild = false;
    /**
     * 设置cubic线条：true代表曲状，false代表直线
     */
    public static boolean CubicChild = false;
}
