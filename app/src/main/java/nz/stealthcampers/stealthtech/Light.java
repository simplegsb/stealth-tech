package nz.stealthcampers.stealthtech;

import android.graphics.Point;

public class Light
{
    private Point position;
    private boolean on;

    public Light(Point position, boolean on)
    {
        this.on = on;
        this.position = position;
    }

    public Point getPosition()
    {
        return position;
    }

    public boolean isOn()
    {
        return on;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public void toggle()
    {
        on = !on;
    }
}
