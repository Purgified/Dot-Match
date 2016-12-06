package com.purgified.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.purgified.TweenAccessors.SpriteAccessor;
import com.purgified.gameobjects.ColorDot;
import com.purgified.gameobjects.Dot;
import com.purgified.gameobjects.ScrollHandler;
import com.purgified.helpers.AssetLoader;

import Configuration.Configuration;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class GameRenderer {
    private TweenManager manager;

    private GameWorld world;
    private ScrollHandler scrollHandler;
    private OrthographicCamera orthoCamera;

    private SpriteBatch batcher;
    public Sprite background, ring;
    public Texture firstColor, secondColor, thirdColor, fourthColor, fifthColor, nextColor;
    public String[] colors = Configuration.COLORS;

    public ColorDot colorDot;
    private Dot dot1, dot2, dot3, dot4, dot5;
    private Dot nextDot;

    public GameRenderer() {
        background = new Sprite(AssetLoader.background1);
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        orthoCamera = new OrthographicCamera();
        orthoCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(orthoCamera.combined);



        firstColor = AssetLoader.blueTexture;
        secondColor = AssetLoader.greenTexture;
        thirdColor = AssetLoader.purpleTexture;
        fourthColor = AssetLoader.redTexture;
        fifthColor = AssetLoader.yellowTexture;
        nextColor = AssetLoader.yellowTexture;


        manager = new TweenManager();
    }

//    public GameRenderer(GameWorld world) {
//        this.world = world;
//    }

    public void render() {
        float delta = Gdx.graphics.getRawDeltaTime();
        draw(delta);
    }

    public void draw(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        manager.update(delta);

        drawBackground(delta);
        drawCircleDot(delta);
        drawDot(delta);
        drawScore();

        batcher.end();
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

    public void drawCircleDot(float delta) {
//        shapeRenderer.circle(colorDot.getX(), colorDot.getY(), colorDot.getRadius());

        if (world.getRingColor() == "blue") {
            ring = new Sprite(AssetLoader.blueRing);
        } else if (world.getRingColor() == "green") {
            ring = new Sprite(AssetLoader.greenRing);
        } else if (world.getRingColor() == "purple") {
            ring = new Sprite(AssetLoader.purpleRing);
        } else if (world.getRingColor() == "red") {
            ring = new Sprite(AssetLoader.redRing);
        } else if (world.getRingColor() == "yellow") {
            ring = new Sprite(AssetLoader.yellowRing);
        }

        ring.setSize(colorDot.getRadius(), colorDot.getRadius());
        ring.setCenter(colorDot.getX() + dot1.getRadius() / 2, colorDot.getY() + dot1.getRadius() / 2);
        ring.draw(batcher);
    }

    public void drawScore() {
        String msg = world.getScore() + "";
        float width = (new GlyphLayout(AssetLoader.font, msg)).width;

        AssetLoader.font.draw(batcher, msg , Gdx.graphics.getWidth() / 2 - width / 2, Gdx.graphics.getHeight() * .75f);
    }


    public void resetColors() {
        scrollHandler = world.getScrollHandler();

        firstColor = AssetLoader.blueTexture;
        secondColor = AssetLoader.greenTexture;
        thirdColor = AssetLoader.purpleTexture;
        fourthColor = AssetLoader.redTexture;
        fifthColor = AssetLoader.yellowTexture;
        nextColor = AssetLoader.yellowTexture;

        colors = Configuration.COLORS;

        dot1 = scrollHandler.getDot1();
        dot2 = scrollHandler.getDot2();
        dot3 = scrollHandler.getDot3();
        dot4 = scrollHandler.getDot4();
        dot5 = scrollHandler.getDot5();
        nextDot = scrollHandler.getNextDot();
    }


    public void setWorld(GameWorld world) {
        this.world = world;

        scrollHandler = world.getScrollHandler();

        dot1 = scrollHandler.getDot1();
        dot2 = scrollHandler.getDot2();
        dot3 = scrollHandler.getDot3();
        dot4 = scrollHandler.getDot4();
        dot5 = scrollHandler.getDot5();
        nextDot = scrollHandler.getNextDot();


        colorDot = world.getColorDot();
    }




    public void setupTween() {
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
        Tween.set(background, SpriteAccessor.ALPHA).target(0).start(manager);
    }

    private void toUI() {

        Tween.to(background, SpriteAccessor.ALPHA, 1.2f).target(1)
                .ease(TweenEquations.easeInOutQuad)
                .start(manager);
    }

}

