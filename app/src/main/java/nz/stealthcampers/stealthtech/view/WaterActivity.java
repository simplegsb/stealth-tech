package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import nz.stealthcampers.stealthtech.MyVan;
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
        water.value = MyVan.freshWater.get(MyVan.freshWater.size() - 1);
        water.secondaryValue = MyVan.wasteWater.get(MyVan.wasteWater.size() - 1);

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
                freshHistoryView.setValues(MyVan.freshWater);
                freshHistoryView.setVisibility(View.VISIBLE);

                GraphView wasteHistoryView = (GraphView) findViewById(R.id.waste_history);
                wasteHistoryView.setColor(R.color.colorPrimaryDark);
                wasteHistoryView.setValues(MyVan.wasteWater);
                wasteHistoryView.setVisibility(View.VISIBLE);
            }
        }, Constants.SHOW_DELAY);
    }
}
