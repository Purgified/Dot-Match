package com.purgified.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.purgified.dotmatch.DotMatch;
import com.purgified.gameobjects.ColorDot;
import com.purgified.gameobjects.Dot;
import com.purgified.gameobjects.ScrollHandler;
import com.purgified.helpers.AssetLoader;
import com.purgified.screens.GameOver;
import com.purgified.screens.GameScreen;

import java.util.Random;

import Configuration.Configuration;

public class GameWorld {

    private Random randomNumber;
    private int randomColorDot;

    private ColorDot colorDot;
    private String ringColor = "";
    private Rectangle newBounds;

    private GameRenderer renderer;
    private ScrollHandler scrollHandler;

    private GameScreen gameScreen;
    private DotMatch game;

    private int score = 0;
    private int speed = 0;

    private boolean scored = false, expand = true;
    private boolean playSounds;
    private Dot scoredDot;

    // Ads variables
    private boolean adShown = false;
    // - 1 indicates that the ads will be shown after 2 deaths when the user FIRST launches the game
    private int adCounter = Configuration.AD_FREQUENCY - 1;


    public GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(DotMatch game, GameScreen gameScreen, boolean playSounds) {
        this.game = game;
        this.gameScreen = gameScreen;
        this.playSounds = playSounds;

        this.currentState = GameState.MENU;
        this.scrollHandler = new ScrollHandler(this);

        randomNumber = new Random();

        colorDot = new ColorDot();
        colorDot.setFirstPosition(scrollHandler.getStartingPosition1());
        colorDot.setSecondPosition(scrollHandler.getStartingPosition2());
        colorDot.setThirdPosition(scrollHandler.getStartingPosition3());

        resetRing();
    }

    public void update(float delta) {
        if (colorDot.getRadius() > Configuration.DOT_RADIUS) {
            if (score < 2) {
                speed = Configuration.RING_SPEED_1;
            } else if (score < 5) {
                speed = Configuration.RING_SPEED_2;
            } else if (score < 7) {
                speed = Configuration.RING_SPEED_3;
            } else if (score < 15) {
                speed = Configuration.RING_SPEED_4;
            } else if (score < 30) {
                speed = Configuration.RING_SPEED_5;
            }

            newBounds = new Rectangle(colorDot.getX(), colorDot.getY(), colorDot.getRadius() - speed, colorDot.getRadius() - speed);
        }

        // Scored animation
        if (scored) {
            if (scoredDot.getRadius() < Configuration.DOT_RADIUS + Configuration.DOT_RADIUS_MAX && expand) {
                scoredDot.setRadius(scoredDot.getRadius() + 2);
                scoredDot.incY(-1);
                scoredDot.incX(-1);
                expand = true;

                if (scoredDot.getRadius() >= Configuration.DOT_RADIUS + Configuration.DOT_RADIUS_MAX) {
                    expand = false;
                }
            }

            if (scoredDot.getRadius() >= Configuration.DOT_RADIUS && !expand) {
                scoredDot.setRadius(scoredDot.getRadius() - 2);
                scoredDot.incY(1);
                scoredDot.incX(1);

                if (scoredDot.getRadius() <= Configuration.DOT_RADIUS) {
                    scored = false;
                    expand = true;
                    scoredDot.setRadius(Configuration.DOT_RADIUS);
                }
            }

        }

        if (colorDot.getRadius() <= Configuration.DOT_RADIUS) {
            checkCollisions();
            resetRing();
        } else {
            colorDot.setBounds(newBounds);
        }

        scrollHandler.update(delta);
    }

    private void checkCollisions() {
        if (colorDot.getBounds().overlaps(scrollHandler.getDot1().getBounds())) {
            checkColors(colorDot, scrollHandler.getDot1());
        } else if (colorDot.getBounds().overlaps(scrollHandler.getDot2().getBounds())) {
            checkColors(colorDot, scrollHandler.getDot2());
        } else if (colorDot.getBounds().overlaps(scrollHandler.getDot3().getBounds())) {
            checkColors(colorDot, scrollHandler.getDot3());
        } else if (colorDot.getBounds().overlaps(scrollHandler.getDot4().getBounds())) {
            checkColors(colorDot, scrollHandler.getDot4());
        } else if (colorDot.getBounds().overlaps(scrollHandler.getDot5().getBounds())) {
            checkColors(colorDot, scrollHandler.getDot5());
        } else if (colorDot.getBounds().overlaps(scrollHandler.getNextDot().getBounds())) {
            checkColors(colorDot, scrollHandler.getNextDot());
        }
    }

    private void checkColors(ColorDot ring, Dot dot) {
        if (ring.getCurrentRingColor() == dot.getCurrentDotColor()) {
            if (playSounds) {
                AssetLoader.matchMusic.play();
            }
            scored = true;
            scoredDot = dot;
            score++;
        } else {
            if (playSounds) {
                AssetLoader.wrongMusic.play();
            }

            showAd();
            over();
            checkHighscore();
            addAchievement();
            game.getAdsController().hideBannerAd();
            game.setScreen(new GameOver(this));
        }
    }

    private void checkHighscore() {
        if (score > AssetLoader.getHighScore()) {
            AssetLoader.setHighScore(score);
        }
        game.getAdsController().submitScoreGPGS(score);
        AssetLoader.addGamesPlayed();
    }

    public void addAchievement() {

        if (AssetLoader.getGamesPlayed() >= 200)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_200_GAMES);
        else if (AssetLoader.getGamesPlayed() >= 100)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_100_GAMES);
        else if (AssetLoader.getGamesPlayed() >= 50)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_50_GAMES);
        else if (AssetLoader.getGamesPlayed() >= 25)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_25_GAMES);
        else if (AssetLoader.getGamesPlayed() >= 10)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_10_GAMES);

        if (score >= 5)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_5_POINTS);
        if (score >= 10)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_10_POINTS);
        if (score >= 25)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_25_POINTS);
        if (score >= 50)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_50_POINTS);
        if (score >= 100)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_100_POINTS);
        if (score >= 200)
            game.getAdsController().unlockAchievementGPGS(Configuration.ACHIEVEMENT_200_POINTS);
    }

    private void showAd() {
        if (adCounter == Configuration.AD_FREQUENCY) {
            if (!adShown) {
                game.getAdsController().showOrLoadInterstitialAd();
                adShown = true;
            }
            adCounter--;
        } else if (adCounter == 0) {
            adCounter = Configuration.AD_FREQUENCY;
            adShown = false;
        } else {
            adCounter--;
        }
    }

    public void resetRing() {
        colorDot.resetRadius();

        ringColor = colorDot.getRandomRing();

        randomColorDot = randomNumber.nextInt(3);

        switch (randomColorDot) {
            case 0:
                colorDot.setBounds(getRectangleValues(colorDot.getFirstPosition(), colorDot.getMainRadius()));
                break;
            case 1:
                colorDot.setBounds(getRectangleValues(colorDot.getSecondPosition(), colorDot.getMainRadius()));
                break;
            case 2:
                colorDot.setBounds(getRectangleValues(colorDot.getThirdPosition(), colorDot.getMainRadius()));
                break;
        }

    }

    private void resetDots() {
        this.scrollHandler = new ScrollHandler(this);
        renderer.resetColors();
    }

    private void resetBackground() {
        int randomBackground = randomNumber.nextInt(2);

        switch (randomBackground) {
            case 0:
                renderer.background = new Sprite(AssetLoader.background1);
                break;
            case 1:
                renderer.background = new Sprite(AssetLoader.background2);
                break;
//            case 2:
//                renderer.background = new Sprite(AssetLoader.background3);
//                break;
        }

        renderer.background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void reset() {
        game.setScreen(gameScreen);
        resetDots();
        resetRing();
        resetBackground();
        score = 0;
        renderer.setupTween();
        if (Configuration.SHOW_BANNER_AD) {
            game.getAdsController().showBannerAd();
        }
        // Change this later
        ready();
    }



    private Rectangle getRectangleValues(Vector2 position, float radius) {
        Rectangle newRect = new Rectangle(position.x, position.y, radius, radius);
        return newRect;
    }

    public ColorDot getColorDot() {
        return colorDot;
    }

    public String getRingColor() {
        return ringColor;
    }

    public ScrollHandler getScrollHandler() {
        return  scrollHandler;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }

    public GameRenderer getRenderer() {
        return renderer;
    }

    public int getScore() {
        return score;
    }

    // GAMESTATE GETTERS & SETTERS
    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
    }

    public void highscore() {
        currentState = GameState.HIGHSCORE;
    }

    public void over() {
        currentState = GameState.GAMEOVER;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }


}
