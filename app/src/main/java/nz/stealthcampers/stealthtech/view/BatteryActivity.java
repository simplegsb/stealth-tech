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

public class BatteryActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        final GridItemView batteryView = GridItemView.inflate(this);
        batteryView.iconResource = R.drawable.battery;
        batteryView.gauge = true;
        batteryView.value = MyVan.battery.get(MyVan.battery.size() - 1);

        Switch powerView = (Switch) findViewById(R.id.power);
        powerView.setChecked(MyVan.power);
        powerView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                MyVan.power = !MyVan.power;
            }
        });

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View rootView = findViewById(R.id.root);
                rootView.setVisibility(View.VISIBLE);
                rootView.startAnimation(AnimationUtils.loadAnimation(BatteryActivity.this, R.anim.fade_in));

                ViewGroup topView = (ViewGroup) findViewById(R.id.top);
                topView.addView(batteryView, 0);
                batteryView.setVisibility(View.VISIBLE);

                GraphView freshHistoryView = (GraphView) findViewById(R.id.history);
                freshHistoryView.setValues(MyVan.battery);
                freshHistoryView.setVisibility(View.VISIBLE);
            }
        }, Constants.SHOW_DELAY);
    }
}
