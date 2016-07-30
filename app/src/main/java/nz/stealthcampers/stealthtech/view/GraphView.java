package nz.stealthcampers.stealthtech.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import nz.stealthcampers.stealthtech.R;

public class GraphView extends View
{
    private ValueAnimator animator;

    private Paint paint;

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

        paint = new Paint();
        paint.setStrokeWidth(10.0f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(ContextCompat.getColor(getContext(), (R.color.colorPrimary)));
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

        float width = getWidth();
        float height = getHeight();

        for (float index = 0; index < values.size() - 1; index++)
        {
            float x0 = index / values.size() * width;
            float y0 = getAdjustedValue((int) index) / 100.0f * height;
            float x1 = (index + 1) / values.size() * width;
            float y1 = getAdjustedValue((int) index + 1) / 100.0f * height;

            canvas.drawLine(x0, y0, x1, y1, paint);
        }

        postInvalidateOnAnimation();
    }

    private float getAdjustedValue(int index)
    {
        return 100.0f - values.get(index) * animator.getAnimatedFraction();
    }
}
