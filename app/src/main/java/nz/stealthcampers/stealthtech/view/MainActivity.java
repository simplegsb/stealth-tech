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

        GridItemView controlPanel = GridItemView.inflate(this);
        controlPanel.iconResource = R.drawable.van;
        controlPanel.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, ControlPanelActivity.class));
            }
        };
        items.add(controlPanel);

        GridItemView map = GridItemView.inflate(this);
        map.iconResource = R.drawable.map;
        map.listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        };
        items.add(map);

        GridItemView chat = GridItemView.inflate(this);
        chat.iconResource = R.drawable.chat;
        items.add(chat);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View root = findViewById(R.id.root);
                root.setVisibility(View.VISIBLE);
                root.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in));
            }
        }, Constants.SHOW_DELAY);

        GridView gridview = (GridView) findViewById(R.id.activities);
        gridview.setAdapter(new GridAdapter(items));
        gridview.setColumnWidth(Util.getGridItemSize(this));
    }
}
