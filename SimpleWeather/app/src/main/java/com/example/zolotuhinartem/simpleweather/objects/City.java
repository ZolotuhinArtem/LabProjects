package com.example.zolotuhinartem.simpleweather.objects;

import java.io.Serializable;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class City{
    private String id;
    private String name;
    private String lat;
    private String lon;
    private String countryCode;

    public static final String ATTRIBUTE_CITY_ID = "city_id";
    public static final String ATTRIBUTE_CITY_NAME = "city_name";
    public static final String ATTRIBUTE_CITY_LAT = "city_lat";
    public static final String ATTRIBUTE_CITY_LON = "city_lon";
    public static final String ATTRIBUTE_CITY_COUNTRY_CODE = "city_country_code";

    public City(String id, String name, String lat, String lon, String countryCode) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.countryCode = countryCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != null ? !id.equals(city.id) : city.id != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (lat != null ? !lat.equals(city.lat) : city.lat != null) return false;
        if (lon != null ? !lon.equals(city.lon) : city.lon != null) return false;
        return countryCode != null ? countryCode.equals(city.countryCode) : city.countryCode == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lon != null ? lon.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        return result;
    }
}
