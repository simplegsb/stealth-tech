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

public class GasActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        final GridItemView gasView = GridItemView.inflate(this);
        gasView.iconResource = R.drawable.gas;
        gasView.gauge = true;
        gasView.value = MyVan.gas.get(MyVan.gas.size() - 1);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ViewGroup rootView = (ViewGroup) findViewById(R.id.root);
                rootView.setVisibility(View.VISIBLE);
                rootView.startAnimation(AnimationUtils.loadAnimation(GasActivity.this, R.anim.fade_in));

                rootView.addView(gasView, 0);
                gasView.setVisibility(View.VISIBLE);

                GraphView freshHistoryView = (GraphView) findViewById(R.id.history);
                freshHistoryView.setValues(MyVan.gas);
                freshHistoryView.setVisibility(View.VISIBLE);
            }
        }, Constants.SHOW_DELAY);
    }
}
