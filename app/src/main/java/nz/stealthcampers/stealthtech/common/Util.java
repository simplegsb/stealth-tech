package nz.stealthcampers.stealthtech.common;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

public class Util
{
    public static void colorize(Context context, ImageView view, int color)
    {
        // Ummm... why do I need to do this?
        // adding ff to the start of the colors in colors.xml seems to make no difference
        int colorWithAlpha = Color.parseColor(context.getResources().getString(color));

        view.setColorFilter(colorWithAlpha);
    }
}
