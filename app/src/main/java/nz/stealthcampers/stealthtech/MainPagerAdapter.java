package nz.stealthcampers.stealthtech;

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
        return 2;
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
        else
        {
            return null;
        }
    }
}
