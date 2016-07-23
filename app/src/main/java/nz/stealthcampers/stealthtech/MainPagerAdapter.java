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
        return 1;
    }

    @Override
    public Fragment getItem(int position)
    {
        return new GaugeFragment();
    }

    @Override
    public String getPageTitle(int position)
    {
        return "Gauges";
    }
}
