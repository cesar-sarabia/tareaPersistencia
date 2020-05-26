package com.example.tareamascotasrecycler;

public class MascotaDataModel {
    private int ID;
    private  String name;
    private int image;
    private int favs;

    public MascotaDataModel(String name, int image, int favs) {
        this.name = name;
        this.image = image;
        this.favs = favs;
    }

    public MascotaDataModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getFavs() {
        return favs;
    }

    public void setFavs(int favs) {
        this.favs = favs;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
