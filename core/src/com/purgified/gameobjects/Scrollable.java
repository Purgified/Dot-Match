package com.purgified.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;

    private float radius;
    private Rectangle bounds;

    private boolean isScrolledRight, isFullyScrolled;

    public Scrollable(float x, float y, float radius) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        this.radius = radius;

        bounds = new Rectangle();
        bounds.set(x, y, radius, radius);

        isScrolledRight = false;
        isFullyScrolled = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if (getX() + radius >= Gdx.graphics.getWidth()) {
            isScrolledRight = true;
        }
        if (getX() >= Gdx.graphics.getWidth()) {
            isFullyScrolled = true;
        }


    }

    public void reset(float newX) {
        position.x = newX;
        isScrolledRight = false;
        isFullyScrolled = false;
    }

    public boolean isScrolledRight() {
        return isScrolledRight;
    }

    public boolean isFullyScrolled() {
        return isFullyScrolled;
    }

    public float getTailX() {
        return position.x + radius;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void incX(int inc) {
        position.x += inc;
    }

    public void incY(int inc) {
        position.y += inc;
    }

    public void updateBounds() {
        bounds.x = position.x;
        bounds.y = position.y;
    }

    public Rectangle getBounds() {
        updateBounds();
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setHorizontalVelocity(float x) {
        velocity.x = x;
    }

    public float getHorizontalVelocity() {
        return velocity.x;
    }

}
