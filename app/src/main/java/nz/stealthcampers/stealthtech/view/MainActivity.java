package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Constants;
import nz.stealthcampers.stealthtech.common.Util;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GridItemView> items = new ArrayList<>();

        GridItemView controlPanelView = GridItemView.inflate(this);
        controlPanelView.iconResource = R.drawable.van;
        controlPanelView.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, ControlPanelActivity.class));
            }
        };
        items.add(controlPanelView);

        GridItemView mapView = GridItemView.inflate(this);
        mapView.iconResource = R.drawable.map;
        mapView.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        };
        items.add(mapView);

        GridItemView chatView = GridItemView.inflate(this);
        chatView.iconResource = R.drawable.chat;
        items.add(chatView);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View rootView = findViewById(R.id.root);
                rootView.setVisibility(View.VISIBLE);
                rootView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in));
            }
        }, Constants.SHOW_DELAY);

        GridView gridView = (GridView) findViewById(R.id.activities);
        gridView.setAdapter(new GridAdapter(items));
        gridView.setColumnWidth(Util.getGridItemSize(this));
    }
}
