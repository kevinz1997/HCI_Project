package com.example.locnt.app_project;

public class DataShop {
    private String name;
    private int img;
    private boolean isFooter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public DataShop(String name, int img) {

        this.name = name;
        this.img = img;
    }

    public DataShop() {
    }

    public boolean isFooter() {
        return isFooter;
    }

    public void setFooter(boolean footer) {
        isFooter = footer;
    }
}
