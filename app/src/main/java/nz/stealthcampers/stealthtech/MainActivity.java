package nz.stealthcampers.stealthtech;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Gauge> gauges = new ArrayList<>();
        gauges.add(new Gauge(50, R.drawable.water));
        gauges.add(new Gauge(10, R.drawable.gas));
        gauges.add(new Gauge(100, R.drawable.battery));
        gauges.add(new Gauge(70, R.drawable.fuel));

        int gaugeSize = getGaugeSize();

        GridView gridview = (GridView) findViewById(R.id.gauges);
        gridview.setAdapter(new GaugeAdapter(this, gauges, gaugeSize));
        gridview.setColumnWidth(gaugeSize);
    }

    private int getGaugeSize()
    {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        float scale = 0.43f;
        if (screenSize.x > screenSize.y)
        {
            scale = 0.23f;
        }

        return (int) (screenSize.x * scale);
    }
}
