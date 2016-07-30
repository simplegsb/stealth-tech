package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Constants;
import nz.stealthcampers.stealthtech.common.Util;

public class ControlPanelActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);

        List<GridItemView> items = new ArrayList<>();

        GridItemView water = GridItemView.inflate(this);
        water.iconResource = R.drawable.water;
        water.gauge = true;
        water.value = 50;
        water.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ControlPanelActivity.this, WaterActivity.class));
            }
        };
        items.add(water);

        GridItemView gas = GridItemView.inflate(this);
        gas.iconResource = R.drawable.gas;
        gas.gauge = true;
        gas.value = 10;
        items.add(gas);

        GridItemView battery = GridItemView.inflate(this);
        battery.iconResource = R.drawable.battery;
        battery.gauge = true;
        battery.value = 100;
        items.add(battery);

        GridItemView fuel = GridItemView.inflate(this);
        fuel.iconResource = R.drawable.fuel;
        fuel.gauge = true;
        fuel.value = 70;
        items.add(fuel);

        GridItemView light = GridItemView.inflate(this);
        light.iconResource = R.drawable.light;
        light.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ControlPanelActivity.this, LightActivity.class));
            }
        };
        items.add(light);

        final GridItemView fridge = GridItemView.inflate(this);
        fridge.iconResource = R.drawable.fridge;
        fridge.value = 1;
        fridge.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fridge.animateValue(Math.abs(fridge.value - 1));
            }
        };
        items.add(fridge);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View root = findViewById(R.id.root);
                root.setVisibility(View.VISIBLE);
                root.startAnimation(AnimationUtils.loadAnimation(ControlPanelActivity.this, R.anim.fade_in));
            }
        }, Constants.SHOW_DELAY);

        GridView gridview = (GridView) findViewById(R.id.controls);
        gridview.setAdapter(new GridAdapter(items));
        gridview.setColumnWidth(Util.getGridItemSize(this));
    }
}
