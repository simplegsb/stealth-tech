package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.Switch;

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

        final GridItemView waterView = GridItemView.inflate(this);
        waterView.iconResource = R.drawable.water;
        waterView.gauge = true;
        waterView.value = MyVan.freshWater.get(MyVan.freshWater.size() - 1);
        waterView.secondaryValue = MyVan.wasteWater.get(MyVan.wasteWater.size() - 1);

        Switch waterHeaterView = (Switch) findViewById(R.id.water_heater);
        waterHeaterView.setChecked(MyVan.waterHeater);
        waterHeaterView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                MyVan.waterHeater = !MyVan.waterHeater;
            }
        });

        Switch waterPumpView = (Switch) findViewById(R.id.water_pump);
        waterPumpView.setChecked(MyVan.waterPump);
        waterPumpView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                MyVan.waterPump = !MyVan.waterPump;
            }
        });

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View rootView = findViewById(R.id.root);
                rootView.setVisibility(View.VISIBLE);
                rootView.startAnimation(AnimationUtils.loadAnimation(WaterActivity.this, R.anim.fade_in));

                ViewGroup topView = (ViewGroup) findViewById(R.id.top);
                topView.addView(waterView, 0);
                waterView.setVisibility(View.VISIBLE);

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
