package com.gmail.yalovikdima.itacademy.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {

    private float padding;
    private int fontSize;
    private float handTruncation;
    private float hourHandTruncation;
    private float radius;
    private Rect rect;
    private Paint paint;
    private float cX;
    private float cY;
    private float bigRadius;
    private double angle;
    private int handRadius;
    private float hour;
    private  Calendar c;


    public ClockView(Context context) {
        super(context);
        initClock();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initClock();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initClock();
    }

    @SuppressLint("NewApi")
    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initClock();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float min = Math.min(h, w);
        radius = min / 2 - padding;
        handTruncation = min / 20;
        hourHandTruncation = min / 7;
        cX = w / 2;
        cY = h / 2;
        bigRadius = radius + padding;
    }

    private void initClock() {
        padding = 130;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, getResources().getDisplayMetrics());
        paint = new Paint();
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle(canvas);
        drawCenter(canvas);
        drawNumeral(canvas);
        drawHands(canvas);
        drawSerif(canvas, 12, 10, 70, 30); //big serifs( hours)
        drawSerif(canvas, 30, 7, 60, 15);   //middle serifs (half a hours)
        drawSerif(canvas, 120, 5, 40, 3);   //small serif(minutes)

        postInvalidateDelayed(500);
        invalidate();
    }

    //draw serifs
    private void drawSerif(Canvas canvas, int number, int strokeWidth, int length, int degrees) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(strokeWidth);
        for (int i = 1; i <= number; i++) {
            canvas.drawLine(cX, cY - bigRadius + length, cX, cY - bigRadius, paint);
            canvas.rotate(degrees, cX, cY);
        }
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour, float widthHand, int colorHand) {
        paint.setStrokeWidth(widthHand);
        paint.setColor(colorHand);
        angle = Math.PI * loc / 30 - Math.PI / 2;
        handRadius = (int) (isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation);
        canvas.drawLine(cX, cY,
                (float) (cX+ Math.cos(angle) * handRadius),
                (float) (cY + Math.sin(angle) * handRadius), paint);
    }

        private void drawHands(Canvas canvas) {
        c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + (double) c.get(Calendar.MINUTE) / 60) * 5f
                , true, 25, Color.BLACK);
        drawHand(canvas, c.get(Calendar.MINUTE), false, 20, Color.BLACK);
        drawHand(canvas, c.get(Calendar.SECOND), false, 10, Color.YELLOW);

    }

    private void drawNumeral(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(fontSize);

        for (int num = 1; num <= 12; num++) {
            String tmp = String.valueOf(num);
            paint.getTextBounds(tmp, 0, tmp.length(), rect);
            canvas.drawText(tmp,
                    (float) (cX + Math.cos(Math.PI / 6 * (num - 3)) * radius - rect.width() / 2),
                    (float) (cY + Math.sin(Math.PI / 6 * (num - 3)) * radius + rect.height() / 2),
                    paint);
        }
    }

    private void drawCenter(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(cX, cY, 18, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(cX, cY, radius + padding - 10, paint);
    }
}
