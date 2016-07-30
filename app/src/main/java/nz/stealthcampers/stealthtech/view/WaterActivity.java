package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Constants;

public class WaterActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        ViewGroup root = (ViewGroup) findViewById(R.id.top);

        final GridItem water = new GridItem();
        water.iconResource = R.drawable.water;
        water.gauge = true;
        water.value = 50;
        root.addView(water.getView(this), 0);

        List<Integer> history = new ArrayList<>();
        history.add(5);
        history.add(5);
        history.add(5);
        history.add(5);
        history.add(5);
        history.add(5);
        history.add(10);
        history.add(5);
        history.add(5);
        history.add(15);
        history.add(25);
        history.add(35);
        history.add(45);
        history.add(55);
        history.add(65);
        history.add(75);
        history.add(85);
        history.add(95);
        history.add(100);
        history.add(100);
        history.add(100);
        history.add(100);
        history.add(100);
        history.add(95);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                water.show(WaterActivity.this);
            }
        }, Constants.SHOW_DELAY);
    }
}
