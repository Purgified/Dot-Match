package com.purgified.gameobjects;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import Configuration.Configuration;

public class ColorDot extends GameObject {

    private Vector2 firstPosition, secondPosition, thirdPosition;

    private String[] rings = {"blue", "green", "purple", "red", "yellow"};
    private String currentRingColor = "";
    private Random randomNumber = new Random();

    private static final int RADIUS = Configuration.RING_RADIUS;

    public ColorDot() {
        super(RADIUS, RADIUS);
    }

    public float getRadius() {
        return getWidth();
    }

    public int getMainRadius() {
        return RADIUS;
    }

    public void resetRadius() {
        setLengths(RADIUS, RADIUS);
    }

    public void setFirstPosition(Vector2 position) {
        this.firstPosition = position;
    }

    public void setSecondPosition(Vector2 position) {
        this.secondPosition = position;
    }

    public void setThirdPosition(Vector2 position) {
        this.thirdPosition = position;
    }

    public Vector2 getFirstPosition() {
        return firstPosition;
    }

    public Vector2 getSecondPosition() {
        return secondPosition;
    }

    public Vector2 getThirdPosition() {
        return thirdPosition;
    }

    public String getRandomRing() {
        int randomRing = randomNumber.nextInt(rings.length);
        currentRingColor = rings[randomRing];
        return currentRingColor;
    }

    public String getCurrentRingColor() {
        return currentRingColor;
    }

}
