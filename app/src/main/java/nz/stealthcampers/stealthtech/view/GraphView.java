package nz.stealthcampers.stealthtech.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.List;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Constants;

public class GraphView extends View
{
    private ValueAnimator animator;

    private Paint curvePaint;

    private Paint scalePaint;

    private List<Integer> values;

    public GraphView(Context context)
    {
        super(context);

        init();
    }

    public GraphView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        init();
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init()
    {
        animator = ValueAnimator.ofFloat(0.0f, 1.0f);

        curvePaint = new Paint();
        curvePaint.setStrokeWidth(10.0f);
        curvePaint.setStrokeCap(Paint.Cap.ROUND);
        curvePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        scalePaint = new Paint();
        scalePaint.setStrokeWidth(10.0f);
        scalePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorBackgroundDark));
    }

    public void setColor(int color)
    {
        curvePaint.setColor(ContextCompat.getColor(getContext(), color));
    }

    public void setValues(List<Integer> values)
    {
        this.values = values;
    }

    @Override
    public void setVisibility(int visibility)
    {
        if (getVisibility() == INVISIBLE && visibility == View.VISIBLE)
        {
            animator.setDuration(1000);
            animator.start();

            postInvalidateOnAnimation();
        }

        super.setVisibility(visibility);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (values == null)
        {
            return;
        }

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        for (int index = 0; index < values.size(); index++)
        {
            int rindex = values.size() - 1 - index;
            if ((rindex - hour) % Constants.SAMPLES_PER_DAY == 0)
            {
                float x0 = getCurveX(index);

                canvas.drawLine(x0, 0, x0, getHeight(), scalePaint);
            }
        }

        for (int index = 0; index < values.size() - 1; index++)
        {
            float x0 = getCurveX(index);
            float y0 = getCurveY(index);
            float x1 = getCurveX(index + 1);
            float y1 = getCurveY(index + 1);

            canvas.drawLine(x0, y0, x1, y1, curvePaint);
        }

        postInvalidateOnAnimation();
    }

    private float getCurveX(float index)
    {
        return index / values.size() * getWidth() * 0.9f + getWidth() * 0.05f;
    }

    private float getCurveY(int index)
    {
        float adjustedValue = 100.0f - values.get(index) * animator.getAnimatedFraction();
        return adjustedValue / 100.0f * getHeight() * 0.9f + getHeight() * 0.05f;
    }
}
