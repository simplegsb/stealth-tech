package nz.stealthcampers.stealthtech.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter
{
    public MainPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
        {
            return new GaugeFragment();
        }
        else if (position == 1)
        {
            return new LightFragment();
        }
        else if (position == 2)
        {
            return new ControlPanelFragment();
        }
        else
        {
            return null;
        }
    }

    @Override
    public String getPageTitle(int position)
    {
        if (position == 0)
        {
            return "Gauges";
        }
        else if (position == 1)
        {
            return "Lights";
        }
        else if (position == 2)
        {
            return "Control Panel";
        }
        else
        {
            return null;
        }
    }
}
