package com.gmail.yalovikdima.itacademy.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.text.DecimalFormat;
import java.util.Random;

public class DiagramView extends View {

    private int[] numbers = {10, 20, 10, 20, 10, 5};
    private int[] colors = new int[numbers.length];
    private float center_x;
    private float center_y;
    private float radius;
    private Rect rect;
    private Paint paint;
    private RectF oval;
    private int startAngle;
    private int sum;
    private int angle;
    private float percent;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private float cosAngleTmp;
    private float sinAngleTmp;
    private float padding;

    public DiagramView(Context context) {
        super(context);
        init();
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center_x = w / 2;
        center_y = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
        paint.setAntiAlias(true);

        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);

        startAngle = 0;
        sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        for (int i = 0; i < numbers.length; i++) {
            angle = 360 * numbers[i] / sum;
            percent = (float) numbers[i] / sum * 100;
            percent = Float.valueOf(decimalFormat.format(percent));
            String tmp = String.valueOf(numbers[i]) + "(" + String.valueOf(percent) + "%)";
            paint.getTextBounds(tmp, 0, tmp.length(), rect);
            paint.setColor(Color.GRAY);
            cosAngleTmp = (float) Math.cos(Math.toRadians(startAngle + (angle >> 1)));
            sinAngleTmp = (float) Math.sin(Math.toRadians(startAngle + (angle >> 1)));
            canvas.drawLine(center_x, center_y,
                    center_x + cosAngleTmp * (padding),
                    center_y + sinAngleTmp * (padding), paint);
            canvas.drawCircle(
                    center_x + cosAngleTmp * (padding),
                    center_y + sinAngleTmp * (padding),
                    10f, paint);
            paint.setColor(Color.BLACK);
            canvas.drawText(tmp,
                    center_x + cosAngleTmp * (radius + 160) - (rect.width() >> 1),
                    center_y + sinAngleTmp * (radius + 100) + (rect.height() >> 1),
                    paint);
            paint.setColor(colors[i]);
            canvas.drawArc(oval, startAngle, angle, true, paint);
            startAngle += angle;
        }
        invalidate();
    }

    private void init() {
        radius = 200f;
        rect = new Rect();
        paint = new Paint();
        oval = new RectF();
        setColorArray();
        padding = radius + 40;
    }

    private void setColorArray() {
        Random rnd = new Random();
        for (int i = 0; i < numbers.length; i++) {
            colors[i] = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }
    }

}
