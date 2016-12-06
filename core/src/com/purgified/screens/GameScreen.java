package com.purgified.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.purgified.controllers.AdsController;
import com.purgified.dotmatch.DotMatch;
import com.purgified.gameworld.GameRenderer;
import com.purgified.gameworld.GameWorld;
import com.purgified.helpers.InputHandler;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;


    public GameScreen(final DotMatch game, GameRenderer renderer, AdsController adsController, boolean playSounds) {
        world = new GameWorld(game, this, playSounds);
        this.renderer = renderer;
        renderer.setWorld(world);
        world.setRenderer(renderer);
        Gdx.input.setInputProcessor(new InputHandler(game, world, adsController));
    }

    // Constantly calls render method i.e. is the game loop
    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }



    @Override
    public void show() {
        renderer.setupTween();
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {

    }
}
