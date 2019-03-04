package com.gmail.yalovikdima.itacademy.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.gmail.yalovikdima.itacademy.R;

import java.util.Calendar;

public class ClockView extends View {

    private float height;
    private float width;
    private float padding;
    private int fontSize;
    private float handTruncation;
    private float hourHandTruncation;
    private float radius;
    private boolean isInit;
    private Rect rect;
    private Paint paint;


    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initClock() {
        height = getHeight();
        width = getWidth();
        padding = 130;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, getResources().getDisplayMetrics());
        float min = Math.min(height, width);
        radius = min / 2 - padding;
        handTruncation = min / 20;
        hourHandTruncation = min / 7;
        paint = new Paint();
        isInit = true;
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }
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
    private void drawSerif(Canvas canvas,  int number, int strokeWidth, int length, int degrees) {
        paint.setColor(Color.WHITE);
        float pX = width / 2;
        float pY = height / 2;
        float bigRadius = radius + padding;
        paint.setStrokeWidth(strokeWidth);
        for (int i = 1; i <= number; i++) {
            canvas.drawLine(pX, pY - bigRadius + length, pX, pY - bigRadius, paint);
            canvas.rotate(degrees, pX, pY);
        }
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour, float widthHand, int colorHand) {
        paint.setStrokeWidth(widthHand);
        paint.setColor(colorHand);
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = (int) (isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation);
        canvas.drawLine(width / 2, height / 2,
                (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius), paint);
    }

    private void drawHands(Canvas canvas) {
        Calendar c = Calendar.getInstance();
        float hour = c.get(Calendar.HOUR_OF_DAY);
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
            double angle = Math.PI / 6 * (num - 3);
            float x = (float) (width / 2 + Math.cos(angle) * radius - rect.width() / 2);
            float y = (float) (height / 2 + Math.sin(angle) * radius + rect.height() / 2);
            canvas.drawText(tmp, x, y, paint);
        }

    }

    private void drawCenter(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, 18, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, height / 2, radius + padding - 10, paint);
    }
}
