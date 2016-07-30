package nz.stealthcampers.stealthtech.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.widget.ImageView;

public class Util
{
    private static int gridItemSize = -1;

    public static void colorize(Context context, ImageView view, int color)
    {
        // Ummm... why do I need to do this?
        // adding ff to the start of the colors in colors.xml seems to make no difference
        int colorWithAlpha = Color.parseColor(context.getResources().getString(color));

        view.setColorFilter(colorWithAlpha);
    }

    public static int getGridItemSize(Activity activity)
    {
        if (gridItemSize == -1)
        {
            Point screenSize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(screenSize);

            float scale = 0.43f;
            if (screenSize.x > screenSize.y) {
                scale = 0.23f;
            }

            gridItemSize = (int) (screenSize.x * scale);
        }

        return gridItemSize;
    }
}
