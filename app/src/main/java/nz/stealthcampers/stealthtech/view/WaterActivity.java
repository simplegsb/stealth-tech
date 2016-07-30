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

        final GridItem water = new GridItem();
        water.iconResource = R.drawable.water;
        water.gauge = true;
        water.value = 50;

        Random random = new Random();
        int nextValue = (int) (random.nextFloat() * 100);
        final List<Integer> history = new ArrayList<>();
        for (int index = 0; index < 100; index++)
        {
            history.add(nextValue);
            nextValue += (int) (random.nextFloat() * 10 - 5);
        }

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ViewGroup topView = (ViewGroup) findViewById(R.id.top);
                topView.setVisibility(View.VISIBLE);
                topView.startAnimation(AnimationUtils.loadAnimation(WaterActivity.this, R.anim.fade_in));

                topView.addView(water.getView(WaterActivity.this), 0);
                water.show(WaterActivity.this);

                GraphView historyView = (GraphView) findViewById(R.id.history);
                historyView.setValues(history);
                historyView.setVisibility(View.VISIBLE);
            }
        }, Constants.SHOW_DELAY);
    }
}
