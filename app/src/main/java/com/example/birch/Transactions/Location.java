package com.example.birch.Transactions;

public class Location {
    private String country;

    private String address;

    private String city;

    private String store_number;

    private String lon;

    private String region;

    private String postal_code;

    private String lat;

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getStore_number ()
    {
        return store_number;
    }

    public void setStore_number (String store_number)
    {
        this.store_number = store_number;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getPostal_code ()
    {
        return postal_code;
    }

    public void setPostal_code (String postal_code)
    {
        this.postal_code = postal_code;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [country = "+country+", address = "+address+", city = "+city+", store_number = "+store_number+", lon = "+lon+", region = "+region+", postal_code = "+postal_code+", lat = "+lat+"]";
    }
}
