package nz.stealthcampers.stealthtech.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Util;

public class GridItem
{
    public boolean gauge;

    public int iconResource;

    public View.OnClickListener listener;

    public int value;

    private View view;

    public View getView(Activity activity)
    {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, null);

            int size = Util.getGridItemSize(activity);
            view.setLayoutParams(new GridView.LayoutParams(size, size));
            view.setOnClickListener(listener);
            view.setVisibility(View.INVISIBLE);

            int iconPadding = size / 3;
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);
            iconView.setImageResource(iconResource);
            iconView.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);
            Util.colorize(activity, iconView, R.color.colorPrimary);

            if (gauge)
            {
                ProgressBar outline = (ProgressBar) view.findViewById(R.id.outline);
                outline.setMax(120);
                outline.setProgress(0);
                outline.setRotation(120);

                ProgressBar outlineBackground = (ProgressBar) view.findViewById(R.id.outline_background);
                outlineBackground.setMax(120);
                outlineBackground.setProgress(100);
                outlineBackground.setRotation(120);
            }
        }

        return view;
    }

    public void show(Context context)
    {
        view.setVisibility(View.VISIBLE);
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));

        if (gauge || value > 0)
        {
            animateValue(context, value);
        }
    }

    public void animateValue(Context context, int toValue)
    {
        value = toValue;

        if (gauge)
        {
            ProgressBar outline = (ProgressBar) view.findViewById(R.id.outline);
            outline.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofInt(outline, "progress", 0, value);
            animator.setDuration(value * 10);
            animator.start();
        }
        else
        {
            if (value == 1)
            {
                ProgressBar outline = (ProgressBar) view.findViewById(R.id.outline);
                outline.setVisibility(View.VISIBLE);
                outline.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
            }
            else if (value == 0)
            {
                ProgressBar outline = (ProgressBar) view.findViewById(R.id.outline);
                outline.setVisibility(View.INVISIBLE);
                outline.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out));
            }
        }
    }
}
