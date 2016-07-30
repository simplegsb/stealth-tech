package nz.stealthcampers.stealthtech.view;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import nz.stealthcampers.stealthtech.R;
import nz.stealthcampers.stealthtech.common.Util;
import nz.stealthcampers.stealthtech.model.Light;

public class LightActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        final ViewGroup vanContainerView = (ViewGroup) findViewById(R.id.van_container);

        ImageView vanView = (ImageView) findViewById(R.id.van);
        vanView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        Util.colorize(this, vanView, R.color.colorBackgroundDark);

        vanContainerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                vanContainerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                List<Light> lights = new ArrayList<>();

                Light light0 = new Light();
                light0.on = true;
                light0.position = new Point(25, 25);
                lights.add(light0);

                Light light1 = new Light();
                light1.position = new Point(60, 25);
                lights.add(light1);

                for (final Light light : lights)
                {
                    addLight(light, vanContainerView);
                }
            }
        });
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
