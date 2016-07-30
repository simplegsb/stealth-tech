package nz.stealthcampers.stealthtech.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Util;

public class GridItemView extends RelativeLayout
{
    public boolean gauge;

    public int iconResource;

    public View.OnClickListener listener;

    public int value;

    public static GridItemView inflate(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return (GridItemView) inflater.inflate(R.layout.grid_item, null);
    }

    public GridItemView(Context context)
    {
        super(context);
    }

    public GridItemView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GridItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void init()
    {
        int size = Util.getGridItemSize((Activity) getContext());

        setOnClickListener(listener);
        if (getParent() instanceof GridView)
        {
            setLayoutParams(new GridView.LayoutParams(size, size));
        }
        else if (getParent() instanceof RelativeLayout)
        {
            setLayoutParams(new RelativeLayout.LayoutParams(size, size));
        }
        else
        {
            throw new IllegalStateException();
        }

        int iconPadding = size / 3;
        ImageView iconView = (ImageView) findViewById(R.id.icon);
        iconView.setImageResource(iconResource);
        iconView.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
        Util.colorize(getContext(), iconView, R.color.colorPrimary);

        if (gauge)
        {
            ProgressBar outline = (ProgressBar) findViewById(R.id.outline);
            outline.setMax(120);
            outline.setProgress(0);
            outline.setRotation(120);

            ProgressBar outlineBackground = (ProgressBar) findViewById(R.id.outline_background);
            outlineBackground.setMax(120);
            outlineBackground.setProgress(100);
            outlineBackground.setRotation(120);
        }
    }

    @Override
    public void setVisibility(int visibility)
    {
        if (getVisibility() == INVISIBLE && visibility == VISIBLE)
        {
            init();

            if (gauge || value > 0)
            {
                animateValue(value);
            }
        }

        super.setVisibility(visibility);
    }

    public void animateValue(int toValue)
    {
        value = toValue;

        if (gauge)
        {
            ProgressBar outline = (ProgressBar) findViewById(R.id.outline);
            outline.setVisibility(VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofInt(outline, "progress", 0, value);
            animator.setDuration(value * 10);
            animator.start();
        }
        else
        {
            if (value == 1)
            {
                ProgressBar outline = (ProgressBar) findViewById(R.id.outline);
                outline.setVisibility(View.VISIBLE);
                outline.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
            }
            else if (value == 0)
            {
                ProgressBar outline = (ProgressBar) findViewById(R.id.outline);
                outline.setVisibility(View.INVISIBLE);
                outline.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
            }
        }
    }
}
