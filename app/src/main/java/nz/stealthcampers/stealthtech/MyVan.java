package nz.stealthcampers.stealthtech;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nz.stealthcampers.stealthtech.common.Constants;
import nz.stealthcampers.stealthtech.model.Light;

public class MyVan
{
    public static List<Integer> battery = new ArrayList<>();

    public static List<Integer> freshWater = new ArrayList<>();

    public static boolean fridge = false;

    public static List<Integer> fuel = new ArrayList<>();

    public static List<Integer> gas = new ArrayList<>();

    public static boolean heater = false;

    public static List<Light> lights = new ArrayList<>();

    public static boolean power = false;

    public static List<Integer> wasteWater = new ArrayList<>();

    public static boolean waterHeater = false;

    public static boolean waterPump = false;

    private static Random random = new Random();

    static
    {
        int sevenDays = Constants.SAMPLES_PER_DAY * 7;

        fridge = random.nextBoolean();
        heater = random.nextBoolean();
        power = random.nextBoolean();
        waterHeater = random.nextBoolean();
        waterPump = random.nextBoolean();

        Light light0 = new Light();
        light0.on = random.nextBoolean();
        light0.position = new Point(25, 25);
        lights.add(light0);

        Light light1 = new Light();
        light1.on = random.nextBoolean();
        light1.position = new Point(60, 25);
        lights.add(light1);

        initValues(battery, sevenDays);
        initValues(freshWater, sevenDays);
        initValues(fuel, sevenDays);
        initValues(gas, sevenDays);
        initValues(wasteWater, sevenDays);
    }

    private static void initValues(List<Integer> values, int count)
    {
        int nextValue = (int) (random.nextFloat() * 100);
        for (int index = 0; index < count; index++)
        {
            values.add(Math.min(Math.max(nextValue, 0), 100));
            nextValue += (int) (random.nextFloat() * 10 - 5);
        }
    }
}
