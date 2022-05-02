package com.proyectoFinal.PetHouse.entidades;

/**
 *
 * Esta clase solo va a guardar las coordenadas al momento de hacer la peticion
 * a la api de google maps
 */

public class Coordenadas {
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
