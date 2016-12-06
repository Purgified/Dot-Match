package com.purgified.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.purgified.TweenAccessors.SpriteAccessor;
import com.purgified.dotmatch.DotMatch;
import com.purgified.gameobjects.Dot;
import com.purgified.gameobjects.ScrollHandler;
import com.purgified.gameworld.GameWorld;
import com.purgified.helpers.AssetLoader;
import com.purgified.ui.SimpleButton;

import java.util.ArrayList;
import java.util.List;

import Configuration.Configuration;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class GameOver implements Screen {
    private TweenManager manager;

    private DotMatch game;

    private GameWorld world;
    private ScrollHandler scrollHandler;
    private OrthographicCamera orthoCamera;

    private Rectangle rectangle;

    private SpriteBatch batcher;
    private Sprite gameover;

    private Sprite background, button1, button2, button3;
    public Texture firstColor, secondColor, thirdColor, fourthColor, fifthColor, nextColor;
    private Dot dot1, dot2, dot3, dot4, dot5;
    private Dot nextDot;



    private List<SimpleButton> menuButtons;
    public SimpleButton highscoresButton;
    public SimpleButton menuButton;
    public SimpleButton retryButton;



    public GameOver(GameWorld world) {
        this.world = world;
        this.rectangle = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        background = world.getRenderer().background;
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        orthoCamera = new OrthographicCamera();
        orthoCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        scrollHandler = world.getScrollHandler();

        dot1 = scrollHandler.getDot1();
        dot2 = scrollHandler.getDot2();
        dot3 = scrollHandler.getDot3();
        dot4 = scrollHandler.getDot4();
        dot5 = scrollHandler.getDot5();
        nextDot = scrollHandler.getNextDot();

        firstColor = AssetLoader.blueTexture;
        secondColor = AssetLoader.greenTexture;
        thirdColor = AssetLoader.purpleTexture;
        fourthColor = AssetLoader.redTexture;
        fifthColor = AssetLoader.yellowTexture;
        nextColor = AssetLoader.yellowTexture;




        // MENU BUTTON CODE
        menuButtons = new ArrayList<SimpleButton>();

        float leftSpace = rectangle.getHeight() * (1 / 10f);
        float rightSpace = rectangle.getHeight() * (1 / 10f);

        float bottomHeight = rectangle.getHeight() * 0.3f;
        float buttonRadius = rectangle.getHeight() * 0.1f;

        highscoresButton = new SimpleButton(rectangle.width / 2 - buttonRadius / 2, rectangle.height / 2 - bottomHeight, buttonRadius, buttonRadius, AssetLoader.highscoresButton, AssetLoader.highscoresButton);
        menuButton = new SimpleButton(rectangle.width / 2 - leftSpace - buttonRadius, rectangle.height / 2 - bottomHeight, buttonRadius, buttonRadius, AssetLoader.menuButton, AssetLoader.menuButton);
        retryButton = new SimpleButton(rightSpace + rectangle.width / 2, rectangle.height / 2 - bottomHeight, buttonRadius, buttonRadius, AssetLoader.retryButton, AssetLoader.retryButton);

        menuButtons.add(highscoresButton);
        menuButtons.add(menuButton);
        menuButtons.add(retryButton);

        button1 = new Sprite(menuButtons.get(0).buttonUp);
        button2 = new Sprite(menuButtons.get(1).buttonUp);


        // TWEEN MANAGER CODE
        manager = new TweenManager();
    }

    @Override
    public void show() {
        orthoCamera = new OrthographicCamera();
        orthoCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(orthoCamera.combined);

        gameover = new Sprite(AssetLoader.gameover);
        gameover.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        setupTween();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batcher.begin();
        manager.update(delta);

        drawBackground(delta);
        drawDot(delta);

        gameover.draw(batcher);

        drawScore();
        drawUI();

        batcher.end();
    }

    private void drawScore() {
        String highscoreMsg = AssetLoader.getHighScore() + "";
        float highscoreWidth = (new GlyphLayout(AssetLoader.font, highscoreMsg)).width;

        AssetLoader.font.getData().setScale(Configuration.FONT_SIZE * .7f);
        AssetLoader.font.draw(batcher, highscoreMsg, Gdx.graphics.getWidth() / 2 - highscoreWidth / 2, Gdx.graphics.getHeight() * .58f);

//        String msg = "0";
        String msg = world.getScore() + "";
        float width = (new GlyphLayout(AssetLoader.font, msg)).width;

        AssetLoader.font.getData().setScale(Configuration.FONT_SIZE * .75f);
        AssetLoader.font.draw(batcher, msg, Gdx.graphics.getWidth() / 2 - width / 2, Gdx.graphics.getHeight() * .47f);
    }

    private void drawUI() {
        for (SimpleButton button : menuButtons) {
            button.draw(batcher);
        }
    }


    public void drawBackground(float delta) {
        background.draw(batcher);
    }

    public void drawDot(float delta) {
        batcher.draw(firstColor, dot1.getX(), dot1.getY(), dot1.getRadius(), dot1.getRadius());
        batcher.draw(secondColor, dot2.getX(), dot2.getY(), dot2.getRadius(), dot2.getRadius());
        batcher.draw(thirdColor, dot3.getX(), dot3.getY(), dot3.getRadius(), dot3.getRadius());
        batcher.draw(fourthColor, dot4.getX(), dot4.getY(), dot4.getRadius(), dot4.getRadius());
        batcher.draw(fifthColor, dot5.getX(), dot5.getY(), dot5.getRadius(), dot5.getRadius());

        batcher.draw(nextColor, nextDot.getX(), nextDot.getY(), nextDot.getRadius(), nextDot.getRadius());
    }




    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

//        TweenCallback cb = new TweenCallback() {
//            @Override
//            public void onEvent(int type, BaseTween<?> source) {
//                drawScore();
//                drawUI();
//            }
//        };

        setUI();
        toUI();

    }

    private void setUI() {
        Tween.set(button1, SpriteAccessor.ALPHA).target(0).start(manager);
        Tween.set(button2, SpriteAccessor.ALPHA).target(0).start(manager);
        Tween.set(button3, SpriteAccessor.ALPHA).target(0).start(manager);

        Tween.set(gameover, SpriteAccessor.ALPHA).target(0).start(manager);
    }

    private void toUI() {
        Tween.to(button1, SpriteAccessor.ALPHA, 1.3f).target(1)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
        Tween.to(button2, SpriteAccessor.ALPHA, 1.3f).target(1)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
        Tween.to(button3, SpriteAccessor.ALPHA, 1.3f).target(1)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);

        Tween.to(gameover, SpriteAccessor.ALPHA, 1.2f).target(.8f)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
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
