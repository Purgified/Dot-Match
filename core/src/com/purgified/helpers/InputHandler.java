package com.purgified.helpers;

import com.badlogic.gdx.InputProcessor;
import com.purgified.controllers.AdsController;
import com.purgified.dotmatch.DotMatch;
import com.purgified.gameworld.GameWorld;
import com.purgified.screens.GameOver;
import com.purgified.screens.MenuScreen;

import Configuration.Configuration;

public class InputHandler implements InputProcessor{

    DotMatch game;

    GameWorld world;
    GameOver gameOver;

    AdsController adsController;

    public InputHandler(DotMatch game, GameWorld world, AdsController adsController) {
        this.game = game;
        this.world = world;
        gameOver = new GameOver(world);
        this.adsController = adsController;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (world.isGameOver()) {
            gameOver.highscoresButton.isTouchDown(screenX, screenY);
            gameOver.menuButton.isTouchDown(screenX, screenY);
            gameOver.retryButton.isTouchDown(screenX, screenY);
        } else {
            world.getScrollHandler().setBarsVelocity(Configuration.SCROLL_SPEED);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (world.isGameOver()) {
            if (gameOver.highscoresButton.isTouchUp(screenX, screenY)) {
                adsController.getLeaderboardGPGS();
            } else if (gameOver.menuButton.isTouchUp(screenX, screenY)) {
                game.setScreen(new MenuScreen(game, adsController));
            } else if (gameOver.retryButton.isTouchUp(screenX, screenY)) {
                world.reset();
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

}
