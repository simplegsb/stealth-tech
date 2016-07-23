package nz.stealthcampers.stealthtech;

public class Gauge
{
    private int iconResource;
    private int value;

    public Gauge(int value, int iconResource)
    {
        this.iconResource = iconResource;
        this.value = value;
    }

    public int getIconResource()
    {
        return iconResource;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void setIconResource(int iconResource)
    {
        this.iconResource = iconResource;
    }
}
