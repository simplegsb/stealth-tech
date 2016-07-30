package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nz.stealthcampers.stealthtech.MyVan;
import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Constants;
import nz.stealthcampers.stealthtech.common.Util;
import nz.stealthcampers.stealthtech.model.Light;

public class LightActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        ImageView vanView = (ImageView) findViewById(R.id.van);
        vanView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        Util.colorize(this, vanView, R.color.colorBackgroundDark);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                View rootView = findViewById(R.id.root);
                rootView.setVisibility(View.VISIBLE);
                rootView.startAnimation(AnimationUtils.loadAnimation(LightActivity.this, R.anim.fade_in));

                ViewGroup vanContainerView = (ViewGroup) findViewById(R.id.van_container);
                for (final Light light : MyVan.lights)
                {
                    addLight(light, vanContainerView);
                }
            }
        }, Constants.SHOW_DELAY);
    }

    private void addLight(final Light light, ViewGroup vanContainerView)
    {
        int lightSize = getLightSize();
        int lightSizeHalf = lightSize / 2;
        int glowSize = lightSize * 2;
        int glowOffsetY = -lightSize / 5;

        final ImageView glowView = new ImageView(this);
        RelativeLayout.LayoutParams glowParams = new RelativeLayout.LayoutParams(glowSize, glowSize);
        glowParams.leftMargin = light.position.x * vanContainerView.getWidth() / 100 - lightSize;
        glowParams.topMargin = light.position.y * vanContainerView.getHeight() / 100 - lightSize + glowOffsetY;
        glowView.setLayoutParams(glowParams);
        glowView.setImageResource(R.drawable.glow);
        glowView.setVisibility(View.INVISIBLE);
        Util.colorize(this, glowView, R.color.colorAccent);

        vanContainerView.addView(glowView);

        ImageView lightView = new ImageView(this);
        RelativeLayout.LayoutParams lightParams = new RelativeLayout.LayoutParams(lightSize, lightSize);
        lightParams.leftMargin = light.position.x * vanContainerView.getWidth() / 100 - lightSizeHalf;
        lightParams.topMargin = light.position.y * vanContainerView.getHeight() / 100 - lightSizeHalf;
        lightView.setLayoutParams(lightParams);
        lightView.setImageResource(R.drawable.light);
        Util.colorize(this, lightView, R.color.colorPrimary);

        vanContainerView.addView(lightView);

        lightView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                light.on = !light.on;
                if (light.on)
                {
                    glowView.setVisibility(View.VISIBLE);
                }
                else
                {
                    glowView.setVisibility(View.INVISIBLE);
                }
            }
        });

        if (light.on)
        {
            glowView.setVisibility(View.VISIBLE);
        }
    }

    private int getLightSize()
    {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        return (int) (Math.min(screenSize.x, screenSize.y) * 0.1f);
    }
}
