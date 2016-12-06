package com.purgified.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.purgified.TweenAccessors.SpriteAccessor;
import com.purgified.controllers.AdsController;
import com.purgified.dotmatch.DotMatch;
import com.purgified.gameworld.GameRenderer;
import com.purgified.helpers.AssetLoader;
import com.purgified.helpers.MenuInputHandler;
import com.purgified.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class MenuScreen implements Screen {
    private TweenManager manager;
    private AdsController adsController;

    private DotMatch game;
    private GameScreen gameScreen;

    private GameRenderer renderer;


    private OrthographicCamera orthoCamera;

    private Rectangle rectangle;

    private SpriteBatch batcher;

    private Sprite background, button1, button2, button3, button4, button5;

    private List<SimpleButton> menuButtons;
    public SimpleButton highscoresButton;
    public SimpleButton badgesButton;
    public SimpleButton soundOnButton, soundOffButton;
    public SimpleButton playButton;

    public boolean soundOn = true;

    public MenuScreen(final DotMatch game, AdsController adsController) {
        Gdx.input.setInputProcessor(new MenuInputHandler(this, adsController));

        this.adsController = adsController;

        this.game = game;
        renderer = new GameRenderer();

        this.rectangle = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        background = new Sprite(AssetLoader.backgroundMenu);
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        orthoCamera = new OrthographicCamera();
        orthoCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());




        // MENU BUTTON CODE
        menuButtons = new ArrayList<SimpleButton>();

        float bottomHeight = rectangle.getHeight() * 0.3f;
        float topHeight = rectangle.getHeight() * 0.108f;

        float leftSpace = rectangle.getHeight() * (1 / 10f);
        float rightSpace = rectangle.getHeight() * (1 / 10f);

        float buttonRadius = rectangle.getHeight() * 0.1f;

        highscoresButton = new SimpleButton(rectangle.width / 2 - leftSpace - buttonRadius, rectangle.height / 2 - bottomHeight, buttonRadius, buttonRadius, AssetLoader.highscoresButton, AssetLoader.highscoresButton);
        badgesButton = new SimpleButton(rectangle.width / 2 - buttonRadius / 2, rectangle.height / 2 - bottomHeight, buttonRadius, buttonRadius, AssetLoader.badgesButton, AssetLoader.badgesButton);
        playButton = new SimpleButton(rectangle.width / 2 - buttonRadius / 2 , (rectangle.height / 2 - bottomHeight) + topHeight  + buttonRadius / 2, buttonRadius, buttonRadius, AssetLoader.playButton, AssetLoader.playButton);
        soundOffButton = new SimpleButton(rightSpace + rectangle.width / 2, rectangle.height / 2 - bottomHeight, buttonRadius, buttonRadius, AssetLoader.soundOffButton, AssetLoader.soundOffButton);
        soundOnButton = new SimpleButton(rightSpace + rectangle.width / 2, rectangle.height / 2 - bottomHeight, buttonRadius, buttonRadius, AssetLoader.soundOnButton, AssetLoader.soundOnButton);


        menuButtons.add(highscoresButton);
        menuButtons.add(badgesButton);
        menuButtons.add(playButton);
        menuButtons.add(soundOnButton);
        menuButtons.add(soundOffButton);

        button1 = new Sprite(menuButtons.get(0).buttonUp);
        button2 = new Sprite(menuButtons.get(1).buttonUp);
        button3 = new Sprite(menuButtons.get(2).buttonUp);
        button4 = new Sprite(menuButtons.get(3).buttonUp);
        button5 = new Sprite(menuButtons.get(4).buttonUp);


        // TWEEN MANAGER CODE
        manager = new TweenManager();
    }

    @Override
    public void show() {
        orthoCamera = new OrthographicCamera();
        orthoCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(orthoCamera.combined);

//        setupTween();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batcher.begin();
        manager.update(delta);

        drawBackground(delta);
        drawUI();

        batcher.end();
    }


    private void drawUI() {
        menuButtons.get(0).draw(batcher);
        menuButtons.get(1).draw(batcher);
        menuButtons.get(2).draw(batcher);
        menuButtons.get(3).draw(batcher);
        if (!soundOn) {
            menuButtons.get(4).draw(batcher);
        }
    }


    public void drawBackground(float delta) {
        background.draw(batcher);
    }


    public void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                startPlaying();
            }
        };

        setUI();
        toUI(cb);

    }

    private void setUI() {
        Tween.set(button1, SpriteAccessor.ALPHA).target(1).start(manager);
        Tween.set(button2, SpriteAccessor.ALPHA).target(1).start(manager);
        Tween.set(button3, SpriteAccessor.ALPHA).target(1).start(manager);
        Tween.set(button4, SpriteAccessor.ALPHA).target(1).start(manager);
        Tween.set(button5, SpriteAccessor.ALPHA).target(1).start(manager);

        Tween.set(background, SpriteAccessor.ALPHA).target(1).start(manager);
    }

    private void toUI(TweenCallback cb) {
        Tween.to(button1, SpriteAccessor.ALPHA, 1.3f).target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
        Tween.to(button2, SpriteAccessor.ALPHA, 1.3f).target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
        Tween.to(button3, SpriteAccessor.ALPHA, 1.3f).target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
        Tween.to(button4, SpriteAccessor.ALPHA, 1.3f).target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
        Tween.to(button5, SpriteAccessor.ALPHA, 1.3f).target(0)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);

        Tween.to(background, SpriteAccessor.ALPHA, 1.0f).target(0)
                .ease(TweenEquations.easeInOutQuad)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
    }



    public void startPlaying() {
        game.setScreen(new GameScreen(game, renderer, adsController, soundOn));
    }


    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;

        if (soundOn) {
            AssetLoader.backgroundMusic.setLooping(true);
            AssetLoader.backgroundMusic.play();
        } else {

            AssetLoader.backgroundMusic.setLooping(false);
            AssetLoader.backgroundMusic.stop();
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
