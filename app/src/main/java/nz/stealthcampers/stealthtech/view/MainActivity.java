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

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GridItem> items = new ArrayList<>();

        GridItem controlPanel = new GridItem();
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

        GridItem map = new GridItem();
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

        GridItem chat = new GridItem();
        chat.iconResource = R.drawable.chat;
        items.add(chat);

        GridView gridview = (GridView) findViewById(R.id.activities);
        gridview.setAdapter(new GridAdapter(this, items));
        gridview.setColumnWidth(Util.getGridItemSize(this));
    }
}
