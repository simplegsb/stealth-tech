package nz.stealthcampers.stealthtech;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GaugeFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.gauge_fragment, container, false);

        List<Gauge> gauges = new ArrayList<>();
        gauges.add(new Gauge(50, R.drawable.water));
        gauges.add(new Gauge(10, R.drawable.gas));
        gauges.add(new Gauge(100, R.drawable.battery));
        gauges.add(new Gauge(70, R.drawable.fuel));

        int gaugeSize = getGaugeSize();

        GridView gridview = (GridView) rootView.findViewById(R.id.gauges);
        gridview.setAdapter(new GaugeAdapter(getActivity(), gauges, gaugeSize));
        gridview.setColumnWidth(gaugeSize);

        return rootView;
    }

    private int getGaugeSize()
    {
        Point screenSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screenSize);

        float scale = 0.43f;
        if (screenSize.x > screenSize.y)
        {
            scale = 0.23f;
        }

        return (int) (screenSize.x * scale);
    }
}
