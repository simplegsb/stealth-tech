package nz.stealthcampers.stealthtech;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

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
        View gaugeFragment = inflater.inflate(R.layout.gauge_fragment, null);

        gaugeFragment.setLayoutParams(new GridView.LayoutParams(gaugeSize, gaugeSize));
        gaugeFragment.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));

        // Ummm... why do I need to do this?
        int primaryColorWithAlpha = Color.parseColor(context.getResources().getString(R.color.colorPrimary));

        ImageView icon = (ImageView) gaugeFragment.findViewById(R.id.icon);
        icon.setColorFilter(primaryColorWithAlpha, PorterDuff.Mode.SRC_ATOP);
        icon.setImageResource(gauge.getIconResource());

        int value = gauge.getValue();

        ObjectAnimator animation = ObjectAnimator.ofInt(gaugeFragment.findViewById(R.id.gauge), "progress", 0, value);
        animation.setDuration(value * 10);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        return gaugeFragment;
    }
}
