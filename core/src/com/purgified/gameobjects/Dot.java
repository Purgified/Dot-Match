package com.purgified.gameobjects;

public class Dot extends Scrollable{

    private String currentDotColor = "";

    public Dot(float x, float y, float radius, String color) {
        super(x, y, radius);
        currentDotColor = color;
    }

    public void setCurrentDotColor(String color) {
        currentDotColor = color;
    }

    public String getCurrentDotColor() {
        return currentDotColor;
    }


}
