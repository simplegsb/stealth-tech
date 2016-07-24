package nz.stealthcampers.stealthtech;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class LightFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.light_fragment, container, false);

        final ViewGroup vanContainerView = (ViewGroup) rootView.findViewById(R.id.van_container);

        ImageView vanView = (ImageView) rootView.findViewById(R.id.van);
        vanView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
        Util.colorize(getActivity(), vanView, R.color.colorBackgroundDark);

        vanContainerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                vanContainerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                List<Light> lights = new ArrayList<>();
                lights.add(new Light(new Point(25, 25), true));
                lights.add(new Light(new Point(60, 25), false));

                for (final Light light : lights)
                {
                    addLight(light, vanContainerView);
                }
            }
        });

        return rootView;
    }

    private void addLight(final Light light, ViewGroup vanContainerView)
    {
        int lightSize = getLightSize();
        int lightSizeHalf = lightSize / 2;
        int glowSize = lightSize * 2;
        int glowOffsetY = -lightSize / 5;

        final ImageView glowView = new ImageView(getActivity());
        RelativeLayout.LayoutParams glowParams = new RelativeLayout.LayoutParams(glowSize, glowSize);
        glowParams.leftMargin = light.getPosition().x * vanContainerView.getWidth() / 100 - lightSize;
        glowParams.topMargin = light.getPosition().y * vanContainerView.getHeight() / 100 - lightSize + glowOffsetY;
        glowView.setLayoutParams(glowParams);
        glowView.setImageResource(R.drawable.glow);
        glowView.setVisibility(View.INVISIBLE);
        Util.colorize(getActivity(), glowView, R.color.colorAccent);

        vanContainerView.addView(glowView);

        ImageView lightView = new ImageView(getActivity());
        RelativeLayout.LayoutParams lightParams = new RelativeLayout.LayoutParams(lightSize, lightSize);
        lightParams.leftMargin = light.getPosition().x * vanContainerView.getWidth() / 100 - lightSizeHalf;
        lightParams.topMargin = light.getPosition().y * vanContainerView.getHeight() / 100 - lightSizeHalf;
        lightView.setLayoutParams(lightParams);
        lightView.setImageResource(R.drawable.light);
        Util.colorize(getActivity(), lightView, R.color.colorPrimary);

        vanContainerView.addView(lightView);

        lightView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                light.toggle();
                if (light.isOn())
                {
                    glowView.setVisibility(View.VISIBLE);
                }
                else
                {
                    glowView.setVisibility(View.INVISIBLE);
                }
            }
        });

        if (light.isOn())
        {
            glowView.setVisibility(View.VISIBLE);
        }
    }

    private int getLightSize()
    {
        Point screenSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screenSize);

        return (int) (Math.min(screenSize.x, screenSize.y) * 0.1f);
    }
}
