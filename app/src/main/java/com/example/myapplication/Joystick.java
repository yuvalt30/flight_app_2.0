package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

public class Joystick extends View {

    public interface JoystickListener {
        void update(float x, float y);
    }

    // FIELDS //
    private float centerX;
    private float centerY;
    private float radius;
    private Paint paint = new Paint();
    private JoystickListener listener;


    // CONSTRUCTORS //
    public Joystick(Context context) {
        super(context);
    }

    public Joystick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        centerX = (float) 0.5;
        centerY = (float) 0.5;
    }

    public Joystick(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Joystick(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // METHODS //
    @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.BLUE);

        canvas.drawCircle( getWidth() * centerX, getHeight() * centerY, 80, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX() / getWidth();
        float y = event.getY() / getHeight();
        if (-1 <= x && x <= 1)
            centerX = x;
        if (-1 <= y && y <= 1)
            centerY = y;
        if (listener != null)
            listener.update(centerX * 2 - 1, centerY * 2 - 1);
        invalidate();
        return true;
    }

    public void setListener(JoystickListener newListener)
    {
        listener = newListener;
    }
}
