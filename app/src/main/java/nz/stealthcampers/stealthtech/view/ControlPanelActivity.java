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

import nz.stealthcampers.stealthtech.MyVan;
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

        GridItemView waterView = GridItemView.inflate(this);
        waterView.iconResource = R.drawable.water;
        waterView.gauge = true;
        waterView.value = MyVan.freshWater.get(MyVan.freshWater.size() - 1);
        waterView.secondaryValue = MyVan.wasteWater.get(MyVan.wasteWater.size() - 1);
        waterView.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ControlPanelActivity.this, WaterActivity.class));
            }
        };
        items.add(waterView);

        GridItemView gasView = GridItemView.inflate(this);
        gasView.iconResource = R.drawable.gas;
        gasView.gauge = true;
        gasView.value = MyVan.gas.get(MyVan.gas.size() - 1);
        gasView.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ControlPanelActivity.this, GasActivity.class));
            }
        };
        items.add(gasView);

        GridItemView batteryView = GridItemView.inflate(this);
        batteryView.iconResource = R.drawable.battery;
        batteryView.gauge = true;
        batteryView.value = MyVan.battery.get(MyVan.battery.size() - 1);
        items.add(batteryView);

        GridItemView fuelView = GridItemView.inflate(this);
        fuelView.iconResource = R.drawable.fuel;
        fuelView.gauge = true;
        fuelView.value = MyVan.fuel.get(MyVan.fuel.size() - 1);
        items.add(fuelView);

        GridItemView lightView = GridItemView.inflate(this);
        lightView.iconResource = R.drawable.light;
        lightView.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ControlPanelActivity.this, LightActivity.class));
            }
        };
        items.add(lightView);

        final GridItemView fridgeView = GridItemView.inflate(this);
        fridgeView.iconResource = R.drawable.fridge;
        fridgeView.value = MyVan.fridge ? 1 : 0;
        fridgeView.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fridgeView.animateValue(Math.abs(fridgeView.value - 1));
            }
        };
        items.add(fridgeView);

        final GridItemView heaterView = GridItemView.inflate(this);
        heaterView.iconResource = R.drawable.heater;
        heaterView.value = MyVan.heater ? 1 : 0;
        heaterView.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                heaterView.animateValue(Math.abs(heaterView.value - 1));
            }
        };
        items.add(heaterView);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View rootView = findViewById(R.id.root);
                rootView.setVisibility(View.VISIBLE);
                rootView.startAnimation(AnimationUtils.loadAnimation(ControlPanelActivity.this, R.anim.fade_in));
            }
        }, Constants.SHOW_DELAY);

        GridView gridView = (GridView) findViewById(R.id.controls);
        gridView.setAdapter(new GridAdapter(items));
        gridView.setColumnWidth(Util.getGridItemSize(this));
    }
}
