package nz.stealthcampers.stealthtech.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Util;
import nz.stealthcampers.stealthtech.model.Gauge;

public class GaugeAdapter extends BaseAdapter
{
    private Context context;

    private List<Gauge> gauges;

    private int gaugeSize;

    public GaugeAdapter(Context context, List<Gauge> gauges, int gaugeSize)
    {
        this.context = context;
        this.gauges = gauges;
        this.gaugeSize= gaugeSize;
    }

    public int getCount()
    {
        return gauges.size();
    }

    public Object getItem(int position)
    {
        return gauges.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Gauge gauge = gauges.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gaugeView = inflater.inflate(R.layout.gauge, null);

        gaugeView.setLayoutParams(new GridView.LayoutParams(gaugeSize, gaugeSize));
        gaugeView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));

        ImageView iconView = (ImageView) gaugeView.findViewById(R.id.icon);
        iconView.setImageResource(gauge.getIconResource());
        Util.colorize(context, iconView, R.color.colorPrimary);

        int value = gauge.getValue();

        ObjectAnimator animation = ObjectAnimator.ofInt(gaugeView.findViewById(R.id.gauge), "progress", 0, value);
        animation.setDuration(value * 10);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        return gaugeView;
    }
}
