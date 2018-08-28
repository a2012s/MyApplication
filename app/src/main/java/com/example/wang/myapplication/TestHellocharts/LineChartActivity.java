package com.example.wang.myapplication.TestHellocharts;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wang.myapplication.R;
import com.example.wang.myapplication.utils.MyTextTool;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

public class LineChartActivity extends AppCompatActivity {

    private ColorArcProgressBar bar_annulus;
    private int num;


    private int test = 9;
    private PlaceholderFragment fragment;
    private Bundle bundle;
    private int selectNo = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);


        


        bar_annulus = findViewById(R.id.bar_annulus);
        num = (int) (Math.random() * 100 + 1);//1到100的随机数

        bar_annulus.setTitle(num + "/100");
        bar_annulus.setCurrentValues(num);


        fragment = new PlaceholderFragment();
        bundle = new Bundle();

        //fragment保存参数，传入一个Bundle对象
        bar_annulus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = (int) (Math.random() * 100 + 1);//1到100的随机数
                bar_annulus.setCurrentValues(num);
                bar_annulus.setTitle(num + "/100");

                bundle.putString("num", num + "");
                // bundle.putInt("select", (int) (Math.random() * 2 + 1));//1到2

                if (selectNo == 1) {
                    selectNo = 2;
                } else {
                    selectNo = 1;
                }
                bundle.putInt("select", selectNo);//1或2

                fragment.setArguments(bundle);
                // test = num;
                fragment.generateData();


            }
        });


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, fragment).commit();
        }
    }



    /**
     * dp转换成px
     */
    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * A fragment containing a line chart.
     */
    public static class PlaceholderFragment extends Fragment {

        private LineChartView chart;
        private LineChartData data;
        private int numberOfLines = 1;
        private int maxNumberOfLines = 1;//
        private int numberOfPoints = 10;//十个点的统计数据

        float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

        private boolean hasAxes = true;
        private boolean hasAxesNames = false;
        private boolean hasLines = true;
        private boolean hasPoints = true;
        private ValueShape shape = ValueShape.CIRCLE;
        private boolean isFilled = false;
        private boolean hasLabels = false;
        private boolean isCubic = false;
        private boolean hasLabelForSelected = false;
        private boolean pointsHaveDifferentColor;
        private boolean hasGradientToTransparent = false;

        private Toolbar mToolbar;
        List<Line> lines = new ArrayList();
        private Bundle getArgumentss;

        //    横坐标集合，可以设置标注名称，就是x轴的值集合，可以是0-100，也可以是10000-20000
        List mAxisXValues = new ArrayList();
        private String num;
        private int selectN0;


        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_line_chart, container, false);
            //  mToolbar = rootView.findViewById(R.id.toolbar);
            //setSupportActionBar(mToolbar);


            chart = rootView.findViewById(R.id.chart);
            chart.setOnValueTouchListener(new ValueTouchListener());

            toggleCubic();//显示弧线还是直线
//            chart.setValueSelectionEnabled(true);//显示数字
            // Generate some random values.

            toggleLabels();


            generateValues();


            generateData();

            // Disable viewport recalculations, see toggleCubic() method for more info.
            chart.setViewportCalculationEnabled(true);

            //((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

            resetViewport();

            return rootView;
        }

        /**
         * 为Toolbar设置Menu
         */
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            //Log.e(TAG, "onCreateOptionsMenu()");
            menu.clear();
            inflater.inflate(R.menu.line_chart, menu);
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_reset) {
                reset();
                generateData();
                return true;
            }
            if (id == R.id.action_add_line) {
                addLineToData();
                return true;
            }
            if (id == R.id.action_toggle_lines) {
                toggleLines();
                return true;
            }
            if (id == R.id.action_toggle_points) {
                togglePoints();
                return true;
            }
            if (id == R.id.action_toggle_gradient) {
                toggleGradient();
                return true;
            }
            if (id == R.id.action_toggle_cubic) {
                toggleCubic();
                return true;
            }
            if (id == R.id.action_toggle_area) {
                toggleFilled();
                return true;
            }
            if (id == R.id.action_point_color) {
                togglePointColor();
                return true;
            }
            if (id == R.id.action_shape_circles) {
                setCircles();
                return true;
            }
            if (id == R.id.action_shape_square) {
                setSquares();
                return true;
            }
            if (id == R.id.action_shape_diamond) {
                setDiamonds();
                return true;
            }
            if (id == R.id.action_toggle_labels) {
                toggleLabels();
                return true;
            }
            if (id == R.id.action_toggle_axes) {
                toggleAxes();
                return true;
            }
            if (id == R.id.action_toggle_axes_names) {
                toggleAxesNames();
                return true;
            }
            if (id == R.id.action_animate) {
                prepareDataAnimation();
                chart.startDataAnimation();
                return true;
            }
            if (id == R.id.action_toggle_selection_mode) {
                toggleLabelForSelected();

                Toast.makeText(getActivity(),
                        "Selection mode set to " + chart.isValueSelectionEnabled() + " select any point.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            if (id == R.id.action_toggle_touch_zoom) {
                chart.setZoomEnabled(!chart.isZoomEnabled());
                Toast.makeText(getActivity(), "IsZoomEnabled " + chart.isZoomEnabled(), Toast.LENGTH_SHORT).show();
                return true;
            }
            if (id == R.id.action_zoom_both) {
                chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
                return true;
            }
            if (id == R.id.action_zoom_horizontal) {
                chart.setZoomType(ZoomType.HORIZONTAL);
                return true;
            }
            if (id == R.id.action_zoom_vertical) {
                chart.setZoomType(ZoomType.VERTICAL);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }


        private void reset() {
            numberOfLines = 1;

            hasAxes = true;
            hasAxesNames = true;
            hasLines = true;
            hasPoints = true;
            shape = ValueShape.CIRCLE;
            isFilled = false;
            hasLabels = false;
            isCubic = false;
            hasLabelForSelected = false;
            pointsHaveDifferentColor = false;

            chart.setValueSelectionEnabled(hasLabelForSelected);
            resetViewport();
        }

        private void resetViewport() {
            // Reset viewport height range to (0,100)
            final Viewport v = new Viewport(chart.getMaximumViewport());
            v.bottom = 0;
            v.top = 100;
            v.left = 0;
            v.right = numberOfPoints - 1;
            chart.setMaximumViewport(v);
            chart.setCurrentViewport(v);
        }

        private void generateValues() {
            for (int i = 0; i < maxNumberOfLines; ++i) {
                for (int j = 0; j < numberOfPoints; ++j) {
                    randomNumbersTab[i][j] = (float) Math.random() * 100f;
                }
            }
        }

        private void generateData() {
            getArgumentss = getArguments();

            mAxisXValues.clear();
            lines.clear();


            String arrayXStr1[] = {"7.9","7.9"};//{"7.9", "7.10", "7.11", "7,12", "7.13", "7.14"};
            Float xValue1[] = {76f,76f};//{76f, 89f, 12f, 50f, 12f, 90.00f};


            String arrayXStr2[] = {"8.9","9.0"};//{"8.9", "8.10", "8.11", "8.12", "8.13", "8.14"};
            Float xValue2[] = {86f,45f};//{86f, 89f, 82f, 50f, 82f, 80.00f};

            String arrayXStr[] = new String[arrayXStr2.length];
            Float xValues[] = new Float[arrayXStr2.length];

            // Map<String, Float> dataMap = new HashMap<>();

            // dataMap.put()


            if (getArgumentss == null) {
                selectN0 = 1;
                num = "6";

            } else {
                // num = getArgumentss.getString("num");
                num = "6";
                selectN0 = getArgumentss.getInt("select", 1);

//                if (selectN0 == 1) {
//                    arrayXStr = arrayXStr1;
//                    xValues = xValue1;
//                } else if (selectN0 == 2) {
//                    arrayXStr = arrayXStr2;
//                    xValues = xValue2;
//
//                } else {
//                    arrayXStr = new String[0];
//                    xValues = new Float[0];
//
//                }
                //numberOfPoints = arrayXStr1.length;

            }
            numberOfPoints = arrayXStr1.length;

            for (int i = 0; i < numberOfPoints; i++) {
                AxisValue axisValue = new AxisValue(i);
//            这句话就关键了，你可以随意设置这个位置显示的东西，string类型的随意来
//            我这边想设置，几月几日几时
                //  axisValue.setLabel(num + "日" + i);
                if (selectN0 == 1) {
                    axisValue.setLabel(arrayXStr1[i]);
                } else {
                    axisValue.setLabel(arrayXStr2[i]);
                }


                mAxisXValues.add(axisValue);
            }


            for (int i = 0; i < numberOfLines; ++i) {

                List<PointValue> values = new ArrayList();
                for (int j = 0; j < numberOfPoints; ++j) {
                    // float xValue = randomNumbersTab[i][j];
                    float xValue;
                    if (selectN0 == 1) {
                        xValue = xValue1[j];
                    } else {
                        xValue = xValue2[j];
                    }


                    values.add(new PointValue(j, xValue));
                }


                Line line = new Line(values);
                line.setColor(ChartUtils.COLORS[i]);
                line.setShape(shape);
                line.setCubic(isCubic);
                line.setFilled(isFilled);
                line.setHasLabels(hasLabels);
                line.setHasLabelsOnlyForSelected(hasLabelForSelected);
                line.setHasLines(hasLines);
                line.setHasPoints(hasPoints);
                //line.setHasGradientToTransparent(hasGradientToTransparent);
                if (pointsHaveDifferentColor) {
                    line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
                }
                lines.add(line);
            }

            data = new LineChartData(lines);

            if (hasAxes) {
                Axis axisX = new Axis().setHasSeparationLine(true).setHasLines(true).setValues(mAxisXValues);
                // Axis axisY = new Axis().setHasLines(false);
                Axis axisY = new Axis().setHasSeparationLine(false).setHasLines(false)
                        .setHasTiltedLabels(false).setHasTiltedLabels(false);
                if (hasAxesNames) {
                    axisX.setName("Axis X");
                    axisY.setName("Axis Y");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            data.setBaseValue(Float.NEGATIVE_INFINITY);
            chart.setLineChartData(data);


            //x轴超过六个，就通过左右滑动查看x轴更多的数据
            Viewport viewportMax = new Viewport(-0.7f, chart.getMaximumViewport().height() * 1.25f, numberOfPoints > 6 ? 6 : numberOfPoints, 0);
            chart.setMaximumViewport(viewportMax);
            //通过left, top, right, bottom四边确定的一个矩形区域。//用来控制视图窗口的缩放。
            Viewport viewport = new Viewport(0, chart.getMaximumViewport().height() * 1.25f, numberOfPoints > 6 ? 6 : numberOfPoints, 0);

            chart.setCurrentViewport(viewport);
            chart.moveTo(0, 0);


            resetViewport();

        }

        /**
         * Adds lines to data, after that data should be set again with
         * {@link LineChartView#setLineChartData(LineChartData)}. Last 4th line has non-monotonically x values.
         */
        private void addLineToData() {
            if (data.getLines().size() >= maxNumberOfLines) {
                Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                ++numberOfLines;
            }

            generateData();
        }

        private void toggleLines() {
            hasLines = !hasLines;

            generateData();
        }

        private void togglePoints() {
            hasPoints = !hasPoints;

            generateData();
        }

        private void toggleGradient() {
            hasGradientToTransparent = !hasGradientToTransparent;

            generateData();
        }

        private void toggleCubic() {
            isCubic = !isCubic;

            generateData();


            if (isCubic) {
                // It is good idea to manually set a little higher max viewport for cubic lines because sometimes line
                // go above or below max/min. To do that use Viewport.inest() method and pass negative value as dy
                // parameter or just set top and bottom values manually.
                // In this example I know that Y values are within (0,100) range so I set viewport height range manually
                // to (-5, 105).
                // To make this works during animations you should use Chart.setViewportCalculationEnabled(false) before
                // modifying viewport.
                // Remember to set viewport after you call setLineChartData().
                final Viewport v = new Viewport(chart.getMaximumViewport());
                v.bottom = -5;
                v.top = 105;
                // You have to set max and current viewports separately.
                chart.setMaximumViewport(v);
                // I changing current viewport with animation in this case.
                chart.setCurrentViewportWithAnimation(v);
            } else {
                // If not cubic restore viewport to (0,100) range.
                final Viewport v = new Viewport(chart.getMaximumViewport());
                v.bottom = 0;
                v.top = 100;

                // You have to set max and current viewports separately.
                // In this case, if I want animation I have to set current viewport first and use animation listener.
                // Max viewport will be set in onAnimationFinished method.
                chart.setViewportAnimationListener(new ChartAnimationListener() {

                    @Override
                    public void onAnimationStarted() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationFinished() {
                        // Set max viewpirt and remove listener.
                        chart.setMaximumViewport(v);
                        chart.setViewportAnimationListener(null);

                    }
                });
                // Set current viewpirt with animation;
                chart.setCurrentViewportWithAnimation(v);
            }

        }

        private void toggleFilled() {
            isFilled = !isFilled;

            generateData();
        }

        private void togglePointColor() {
            pointsHaveDifferentColor = !pointsHaveDifferentColor;

            generateData();
        }

        private void setCircles() {
            shape = ValueShape.CIRCLE;

            generateData();
        }

        private void setSquares() {
            shape = ValueShape.SQUARE;

            generateData();
        }

        private void setDiamonds() {
            shape = ValueShape.DIAMOND;

            generateData();
        }

        private void toggleLabels() {
            hasLabels = !hasLabels;

            if (hasLabels) {
                hasLabelForSelected = false;
                chart.setValueSelectionEnabled(hasLabelForSelected);
            }

            generateData();
        }

        private void toggleLabelForSelected() {
            hasLabelForSelected = !hasLabelForSelected;

            chart.setValueSelectionEnabled(hasLabelForSelected);

            if (hasLabelForSelected) {
                hasLabels = false;
            }

            generateData();
        }

        private void toggleAxes() {
            hasAxes = !hasAxes;

            generateData();
        }

        private void toggleAxesNames() {
            hasAxesNames = !hasAxesNames;

            generateData();
        }

        /**
         * To animate values you have to change targets values and then call {@link Chart#startDataAnimation()}
         * method(don't confuse with View.animate()). If you operate on data that was set before you don't have to call
         * {@link LineChartView#setLineChartData(LineChartData)} again.
         */
        private void prepareDataAnimation() {
            for (Line line : data.getLines()) {
                for (PointValue value : line.getValues()) {
                    // Here I modify target only for Y values but it is OK to modify X targets as well.
                    value.setTarget(value.getX(), (float) Math.random() * 100);
                }
            }
        }

        private class ValueTouchListener implements LineChartOnValueSelectListener {

            @Override
            public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {
                // TODO Auto-generated method stub

            }

        }
    }
}
