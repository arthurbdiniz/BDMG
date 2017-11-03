package com.bdmg.bdmg.Model;


import com.google.android.gms.maps.model.LatLng;

public class Building {

    public CharSequence placeName;
    public CharSequence placeEndereco;
    LatLng placeLatLng;

    public Building(CharSequence placeName, CharSequence placeEndereco, LatLng placeLatLng) {
        this.placeName = placeName;
        this.placeEndereco = placeEndereco;
        this.placeLatLng = placeLatLng;
    }
}
