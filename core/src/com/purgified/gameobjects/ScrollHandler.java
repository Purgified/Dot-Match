package com.purgified.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.purgified.gameworld.GameWorld;

import java.util.Arrays;

import Configuration.Configuration;

public class ScrollHandler {

    private GameWorld world;

    private Dot dot1, dot2, dot3, dot4, dot5;
    private Dot nextDot;

    public static final int DOT_RADIUS = Configuration.DOT_RADIUS;
    public static final int DOT_GAP = Gdx.graphics.getWidth() / 5;
    public static final int DOT_POSITION_X = DOT_GAP / 2 - (DOT_RADIUS / 2);
    public static final int DOT_POSITION_X_NEXT = -DOT_GAP / 2 - (DOT_RADIUS / 2);
    public static final float DOT_POSITION_Y = Configuration.DOT_POSITION_Y;



    public ScrollHandler(GameWorld world) {
        this.world = world;

        resetDots();
    }

    public void update(float delta) {
        dot1.update(delta);
        dot2.update(delta);
        dot3.update(delta);
        dot4.update(delta);
        dot5.update(delta);
        nextDot.update(delta);

        checkScrolled();


    }

    // Scrolls the objects and normalizes the positions
    private void checkScrolled() {
        if (dot5.isScrolledRight()) {
            nextDot.setHorizontalVelocity(dot1.getHorizontalVelocity());
            world.getRenderer().nextColor = world.getRenderer().fifthColor;
            if (dot5.isFullyScrolled()) {
                setBarsVelocity(0);
                changeColorsPosition();
                dot5.reset(DOT_POSITION_X_NEXT);
                nextDot.reset(DOT_POSITION_X);
                dot1.reset(nextDot.getX() + DOT_GAP);
                dot2.reset(dot1.getX() + DOT_GAP);
                dot3.reset(dot2.getX() + DOT_GAP);
                dot4.reset(dot3.getX() + DOT_GAP);

                dot5.setCurrentDotColor(world.getRenderer().colors[4]);

            }
        } else if (dot4.isScrolledRight()) {
            dot5.setHorizontalVelocity(dot1.getHorizontalVelocity());
            world.getRenderer().fifthColor = world.getRenderer().fourthColor;
            if (dot4.isFullyScrolled()) {
                setBarsVelocity(0);
                changeColorsPosition();
                dot4.reset(DOT_POSITION_X_NEXT);
                dot5.reset(DOT_POSITION_X);
                nextDot.reset(dot5.getX() + DOT_GAP);
                dot1.reset(nextDot.getX() + DOT_GAP);
                dot2.reset(dot1.getX() + DOT_GAP);
                dot3.reset(dot2.getX() + DOT_GAP);

                dot4.setCurrentDotColor(world.getRenderer().colors[4]);

            }
        } else if (dot3.isScrolledRight()) {
            dot4.setHorizontalVelocity(dot1.getHorizontalVelocity());
            world.getRenderer().fourthColor = world.getRenderer().thirdColor;
            if (dot3.isFullyScrolled()) {
                setBarsVelocity(0);
                changeColorsPosition();
                dot3.reset(DOT_POSITION_X_NEXT);
                dot4.reset(DOT_POSITION_X);
                dot5.reset(dot4.getX() + DOT_GAP);
                nextDot.reset(dot5.getX() + DOT_GAP);
                dot1.reset(nextDot.getX() + DOT_GAP);
                dot2.reset(dot1.getX() + DOT_GAP);

                dot3.setCurrentDotColor(world.getRenderer().colors[4]);

            }
        } else if (dot2.isScrolledRight()) {
            dot3.setHorizontalVelocity(dot1.getHorizontalVelocity());
            world.getRenderer().thirdColor = world.getRenderer().secondColor;
            if (dot2.isFullyScrolled()) {
                setBarsVelocity(0);
                changeColorsPosition();
                dot2.reset(DOT_POSITION_X_NEXT);
                dot3.reset(DOT_POSITION_X);
                dot4.reset(dot3.getX() + DOT_GAP);
                dot5.reset(dot4.getX() + DOT_GAP);
                nextDot.reset(dot5.getX() + DOT_GAP);
                dot1.reset(nextDot.getX() + DOT_GAP);

                dot2.setCurrentDotColor(world.getRenderer().colors[4]);

            }
        } else if (dot1.isScrolledRight()) {
            dot2.setHorizontalVelocity(dot1.getHorizontalVelocity());
            world.getRenderer().secondColor = world.getRenderer().firstColor;
            if (dot1.isFullyScrolled()) {
                setBarsVelocity(0);
                changeColorsPosition();
                dot1.reset(DOT_POSITION_X_NEXT);
                dot2.reset(DOT_POSITION_X);
                dot3.reset(dot2.getX() + DOT_GAP);
                dot4.reset(dot3.getX() + DOT_GAP);
                dot5.reset(dot4.getX() + DOT_GAP);
                nextDot.reset(dot5.getX() + DOT_GAP);

                dot1.setCurrentDotColor(world.getRenderer().colors[4]);

            }
        } else if (nextDot.isScrolledRight()) {
            dot1.setHorizontalVelocity(dot1.getHorizontalVelocity());
            world.getRenderer().firstColor = world.getRenderer().nextColor;
            if (nextDot.isFullyScrolled()) {
                setBarsVelocity(0);
                changeColorsPosition();
                nextDot.reset(DOT_POSITION_X_NEXT);
                dot1.reset(DOT_POSITION_X);
                dot2.reset(dot1.getX() + DOT_GAP);
                dot3.reset(dot2.getX() + DOT_GAP);
                dot4.reset(dot3.getX() + DOT_GAP);
                dot5.reset(dot4.getX() + DOT_GAP);

                nextDot.setCurrentDotColor(world.getRenderer().colors[4]);

            }
        }
    }

    // Moves the position of each color in the array by one
    private void changeColorsPosition() {
        // {"blue", "green", "purple", "red", "yellow"}
        String[] copyColors = Arrays.copyOf(world.getRenderer().colors, world.getRenderer().colors.length);
        String[] colors = Arrays.copyOf(world.getRenderer().colors, world.getRenderer().colors.length);

        colors[4] = copyColors[3];
        colors[3] = copyColors[2];
        colors[2] = copyColors[1];
        colors[1] = copyColors[0];
        colors[0] = copyColors[4];

        world.getRenderer().colors = colors;
    }

    // Sets starting bounds and starting colors for dot
    public void resetDots() {
        dot1 = new Dot(DOT_POSITION_X, DOT_POSITION_Y, DOT_RADIUS, "blue");
        dot2 = new Dot(dot1.getX() + DOT_GAP, DOT_POSITION_Y, DOT_RADIUS, "green");
        dot3 = new Dot(dot2.getX() + DOT_GAP, DOT_POSITION_Y, DOT_RADIUS, "purple");
        dot4 = new Dot(dot3.getX() + DOT_GAP, DOT_POSITION_Y, DOT_RADIUS, "red");
        dot5 = new Dot(dot4.getX() + DOT_GAP, DOT_POSITION_Y, DOT_RADIUS, "yellow");

        // Hidden dot on the left of Dot 1 that shows when Dot 5 is out of view to the right
        nextDot = new Dot(DOT_POSITION_X_NEXT, DOT_POSITION_Y, DOT_RADIUS, "yellow");
        System.out.println("Next Dot Color: " + nextDot.getCurrentDotColor());

    }


    public Dot getDot1() {
        return dot1;
    }

    public Dot getDot2() {
        return dot2;
    }

    public Dot getDot3() {
        return dot3;
    }

    public Dot getDot4() {
        return dot4;
    }

    public Dot getDot5() {
        return dot5;
    }

    public Dot getNextDot() {
        return nextDot;
    }

    public Vector2 getStartingPosition1() {
        return (new Vector2(DOT_POSITION_X + DOT_GAP, DOT_POSITION_Y));
    }

    public Vector2 getStartingPosition2() {
        return (new Vector2(DOT_POSITION_X + DOT_GAP + DOT_GAP, DOT_POSITION_Y));
    }

    public Vector2 getStartingPosition3() {
        return (new Vector2(DOT_POSITION_X + DOT_GAP + DOT_GAP + DOT_GAP, DOT_POSITION_Y));
    }

    public void setBarsVelocity(int horizontalSpeed) {
        dot1.setHorizontalVelocity(horizontalSpeed);
        dot2.setHorizontalVelocity(horizontalSpeed);
        dot3.setHorizontalVelocity(horizontalSpeed);
        dot4.setHorizontalVelocity(horizontalSpeed);
        dot5.setHorizontalVelocity(horizontalSpeed);
        nextDot.setHorizontalVelocity(horizontalSpeed);
    }
}
