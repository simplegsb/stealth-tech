package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Util;

public class ControlPanelActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);

        List<GridItem> items = new ArrayList<>();

        GridItem water = new GridItem();
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

        GridItem gas = new GridItem();
        gas.iconResource = R.drawable.gas;
        gas.gauge = true;
        gas.value = 10;
        items.add(gas);

        GridItem battery = new GridItem();
        battery.iconResource = R.drawable.battery;
        battery.gauge = true;
        battery.value = 100;
        items.add(battery);

        GridItem fuel = new GridItem();
        fuel.iconResource = R.drawable.fuel;
        fuel.gauge = true;
        fuel.value = 70;
        items.add(fuel);

        GridItem light = new GridItem();
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

        final GridItem fridge = new GridItem();
        fridge.iconResource = R.drawable.fridge;
        fridge.value = 1;
        fridge.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fridge.animateValue(ControlPanelActivity.this, Math.abs(fridge.value - 1));
            }
        };
        items.add(fridge);

        GridView gridview = (GridView) findViewById(R.id.controls);
        gridview.setAdapter(new GridAdapter(this, items));
        gridview.setColumnWidth(Util.getGridItemSize(this));
    }
}
