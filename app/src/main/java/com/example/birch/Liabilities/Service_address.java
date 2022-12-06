package com.example.birch.Liabilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service_address {
    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("street")
    @Expose
    private String street;

    @SerializedName("postal_code")
    @Expose
    private String postal_code;

    @SerializedName("region")
    @Expose
    private String region;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getStreet ()
    {
        return street;
    }

    public void setStreet (String street)
    {
        this.street = street;
    }

    public String getPostal_code ()
    {
        return postal_code;
    }

    public void setPostal_code (String postal_code)
    {
        this.postal_code = postal_code;
    }

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", city = "+city+", street = "+street+", postal_code = "+postal_code+", region = "+region+"]";
    }
}
