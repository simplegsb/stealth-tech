package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Constants;

public class WaterActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        final GridItemView water = GridItemView.inflate(this);
        water.iconResource = R.drawable.water;
        water.gauge = true;
        water.value = 50;
        water.secondaryValue = 60;

        Random random = new Random();
        int nextValue = (int) (random.nextFloat() * 100);
        final List<Integer> freshHistory = new ArrayList<>();
        for (int index = 0; index < 100; index++)
        {
            freshHistory.add(Math.min(Math.max(nextValue, 0), 100));
            nextValue += (int) (random.nextFloat() * 10 - 5);
        }

        nextValue = (int) (random.nextFloat() * 100);
        final List<Integer> wasteHistory = new ArrayList<>();
        for (int index = 0; index < 100; index++)
        {
            wasteHistory.add(Math.min(Math.max(nextValue, 0), 100));
            nextValue += (int) (random.nextFloat() * 10 - 5);
        }

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View root = findViewById(R.id.root);
                root.setVisibility(View.VISIBLE);
                root.startAnimation(AnimationUtils.loadAnimation(WaterActivity.this, R.anim.fade_in));

                ViewGroup topView = (ViewGroup) findViewById(R.id.top);
                topView.addView(water, 0);
                water.setVisibility(View.VISIBLE);

                GraphView freshHistoryView = (GraphView) findViewById(R.id.fresh_history);
                freshHistoryView.setValues(freshHistory);
                freshHistoryView.setVisibility(View.VISIBLE);

                GraphView wasteHistoryView = (GraphView) findViewById(R.id.waste_history);
                wasteHistoryView.setColor(R.color.colorPrimaryDark);
                wasteHistoryView.setValues(wasteHistory);
                wasteHistoryView.setVisibility(View.VISIBLE);
            }
        }, Constants.SHOW_DELAY);
    }
}
