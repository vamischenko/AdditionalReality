package com.apps.augmentedreality.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RectangleView extends View {
    private Paint paint;

    public RectangleView(Context context) {
        super(context);
        init(null, 0);
    }

    public RectangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RectangleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(
                getLeft()+(getRight()-getLeft())/5,
                getTop()+(getBottom()-getTop())/3,
                getRight()-(getRight()-getLeft())/5,
                getBottom()-(getBottom()-getTop())/3, paint);
        super.onDraw(canvas);
    }
}
