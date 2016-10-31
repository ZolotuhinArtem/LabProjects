package com.example.zolotuhinartem.simpleweather.objects;

import java.io.Serializable;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class City{
    private String city;
    private String country;

    public static final String ATTRIBUTE_CITY = "city_city";
    public static final String ATTRIBUTE_COUNTRY = "city_country";

    public City(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city1 = (City) o;

        if (city != null ? !city.equals(city1.city) : city1.city != null) return false;
        return country != null ? country.equals(city1.country) : city1.country == null;

    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return this.city + "," + this.country;
    }
}
