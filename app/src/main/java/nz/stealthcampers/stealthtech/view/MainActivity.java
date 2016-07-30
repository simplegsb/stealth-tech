package nz.stealthcampers.stealthtech.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import nz.stealthcampers.stealthtech.R;

public class MainActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        PagerTabStrip tabStrip = (PagerTabStrip) pager.findViewById(R.id.tab_strip);
        tabStrip.setDrawFullUnderline(false);
        tabStrip.setTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }
}
