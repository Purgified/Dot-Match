package com.purgified.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Configuration.Configuration;

public class AssetLoader {

    public static Texture gameLogo, splashLogo;
    public static Texture backgroundMenu, background1, background2, gameover;
    public static Texture blueTexture, greenTexture, purpleTexture, redTexture, yellowTexture;
    public static Texture blueRTexture, greenRTexture, purpleRTexture, redRTexture, yellowRTexture;

    public static Texture highscoresButton, menuButton, retryButton;
    public static Texture badgesButton, soundOnButton, soundOffButton, playButton;

    public static TextureRegion blueDot, greenDot, purpleDot, redDot, yellowDot;
    public static TextureRegion blueRing, greenRing, purpleRing, redRing, yellowRing;

    public static BitmapFont font;

    public static Music backgroundMusic, matchMusic, wrongMusic;

    private static Preferences prefs;


    public static void load() {
        font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        font.getData().setScale(Configuration.FONT_SIZE, Configuration.FONT_SIZE);
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/background_music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        matchMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/match.mp3"));
        wrongMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/wrong.mp3"));
        matchMusic.setVolume(0.7f);
        wrongMusic.setVolume(0.7f);


        gameLogo = new Texture(Gdx.files.internal("logos/game_logo.png"));
        splashLogo = new Texture(Gdx.files.internal("logos/splashlogo.png"));

        backgroundMenu = new Texture(Gdx.files.internal("backgrounds/background_menu.jpg"));
        background1 = new Texture(Gdx.files.internal("backgrounds/background1.png"));
        background2 = new Texture(Gdx.files.internal("backgrounds/background2.png"));
        gameover = new Texture(Gdx.files.internal("backgrounds/gameover.png"));

        blueTexture = new Texture(Gdx.files.internal("dots/BlueDot.png"));
        greenTexture = new Texture(Gdx.files.internal("dots/GreenDot.png"));
        purpleTexture = new Texture(Gdx.files.internal("dots/PurpleDot.png"));
        redTexture = new Texture(Gdx.files.internal("dots/RedDot.png"));
        yellowTexture = new Texture(Gdx.files.internal("dots/YellowDot.png"));

        blueRTexture = new Texture(Gdx.files.internal("rings/BlueRing.png"));
        greenRTexture = new Texture(Gdx.files.internal("rings/GreenRing.png"));
        purpleRTexture = new Texture(Gdx.files.internal("rings/PurpleRing.png"));
        redRTexture = new Texture(Gdx.files.internal("rings/RedRing.png"));
        yellowRTexture = new Texture(Gdx.files.internal("rings/YellowRing.png"));

        highscoresButton = new Texture(Gdx.files.internal("buttons/highscores.png"));
        menuButton = new Texture(Gdx.files.internal("buttons/menu.png"));
        retryButton = new Texture(Gdx.files.internal("buttons/retry.png"));
        badgesButton = new Texture(Gdx.files.internal("buttons/badges.png"));
        soundOffButton = new Texture(Gdx.files.internal("buttons/soundoff.png"));
        soundOnButton = new Texture(Gdx.files.internal("buttons/soundon.png"));
        playButton = new Texture(Gdx.files.internal("buttons/play.png"));

        gameLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        splashLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        backgroundMenu.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        background1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        background2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        gameover.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        highscoresButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        menuButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        retryButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        badgesButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        soundOffButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        soundOnButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        playButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);


        blueTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        greenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        purpleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        redTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        yellowTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        blueRTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        greenRTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        purpleRTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        redRTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        yellowRTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        blueDot = new TextureRegion(blueTexture, 0, 0, blueTexture.getWidth(), blueTexture.getHeight());
        greenDot = new TextureRegion(greenTexture, 0, 0, greenTexture.getWidth(), greenTexture.getHeight());
        purpleDot = new TextureRegion(purpleTexture, 0, 0, purpleTexture.getWidth(), purpleTexture.getHeight());
        redDot = new TextureRegion(redTexture, 0, 0, redTexture.getWidth(), redTexture.getHeight());
        yellowDot = new TextureRegion(yellowTexture, 0, 0, yellowTexture.getWidth(), yellowTexture.getHeight());

        blueRing = new TextureRegion(blueRTexture, 0, 0, blueRTexture.getWidth(), blueRTexture.getHeight());
        greenRing = new TextureRegion(greenRTexture, 0, 0, greenRTexture.getWidth(), greenRTexture.getHeight());
        purpleRing = new TextureRegion(purpleRTexture, 0, 0, purpleRTexture.getWidth(), purpleRTexture.getHeight());
        redRing = new TextureRegion(redRTexture, 0, 0, redRTexture.getWidth(), redRTexture.getHeight());
        yellowRing = new TextureRegion(yellowRTexture, 0, 0, yellowRTexture.getWidth(), yellowRTexture.getHeight());

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("DotMatch");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        if (!prefs.contains("gamesPlayed")) {
            prefs.putInteger("gamesPlayed", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void addGamesPlayed() {
        prefs.putInteger("gamesPlayed", getGamesPlayed() + 1);
        prefs.flush();
    }

    public static int getGamesPlayed() {
        return prefs.getInteger("gamesPlayed");
    }

    public static void dispose() {
        font.dispose();

        backgroundMusic.dispose();
        matchMusic.dispose();
        wrongMusic.dispose();

        gameLogo.dispose();
        backgroundMenu.dispose();
        background1.dispose();
        background2.dispose();
        gameover.dispose();

        blueTexture.dispose();
        greenTexture.dispose();
        purpleTexture.dispose();
        redTexture.dispose();
        yellowTexture.dispose();

        blueRTexture.dispose();
        greenRTexture.dispose();
        purpleRTexture.dispose();
        redRTexture.dispose();
        yellowRTexture.dispose();
    }

}
