package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import nz.stealthcampers.stealthtech.common.Constants;

public class GridAdapter extends BaseAdapter
{
    private List<GridItemView> items;

    public GridAdapter(List<GridItemView> items)
    {
        this.items = items;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public Object getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final GridItemView item = items.get(position);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                item.setVisibility(View.VISIBLE);
            }
        }, Constants.SHOW_DELAY);

        return item;
    }
}
