package com.gmail.yalovikdima.itacademy.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class DiagramView extends View {

    private int[] numbers = {5, 5, 10};
    private int[] colors  = new int[numbers.length];
    private boolean isInit;

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
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            init();
        }

        float radius = 400f;
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        float center_x, center_y;
        center_x = getWidth() / 2;
        center_y = getHeight() / 2;

        final RectF oval = new RectF();
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        int startAngle = 0;
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        for (int i=0; i<numbers.length; i++) {
            paint.setColor(colors[i]);
            int angle = 360 * numbers[i] / sum;
            canvas.drawArc(oval, startAngle, angle, true, paint);
            startAngle += angle;
        }
        invalidate();
    }

    private void init(){
        Random rnd = new Random();
        for (int i=0; i<numbers.length; i++){
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            colors[i] = color;
        }
        isInit=true;
    }

}
