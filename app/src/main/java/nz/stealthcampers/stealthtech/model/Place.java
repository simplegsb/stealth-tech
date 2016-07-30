package nz.stealthcampers.stealthtech.model;

import com.google.gson.annotations.SerializedName;

public class Place
{
    public String icon;

    @SerializedName("place_id")
    public String id;

    public String name;
}
