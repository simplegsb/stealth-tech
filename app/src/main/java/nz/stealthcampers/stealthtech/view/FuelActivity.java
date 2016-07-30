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

public class FuelActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);

        final GridItemView fuelView = GridItemView.inflate(this);
        fuelView.iconResource = R.drawable.fuel;
        fuelView.gauge = true;
        fuelView.value = MyVan.fuel.get(MyVan.fuel.size() - 1);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ViewGroup rootView = (ViewGroup) findViewById(R.id.root);
                rootView.setVisibility(View.VISIBLE);
                rootView.startAnimation(AnimationUtils.loadAnimation(FuelActivity.this, R.anim.fade_in));

                rootView.addView(fuelView, 0);
                fuelView.setVisibility(View.VISIBLE);

                GraphView freshHistoryView = (GraphView) findViewById(R.id.history);
                freshHistoryView.setValues(MyVan.fuel);
                freshHistoryView.setVisibility(View.VISIBLE);
            }
        }, Constants.SHOW_DELAY);
    }
}
