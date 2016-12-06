package com.purgified.helpers;

import com.badlogic.gdx.InputProcessor;
import com.purgified.controllers.AdsController;
import com.purgified.screens.MenuScreen;

public class MenuInputHandler implements InputProcessor {

    private MenuScreen menuScreen;

    AdsController adsController;

    private boolean clicked = false;

    public MenuInputHandler(MenuScreen menuScreen, AdsController adsController) {
        this.menuScreen = menuScreen;
        this.adsController = adsController;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        menuScreen.playButton.isTouchDown(screenX, screenY);
        menuScreen.highscoresButton.isTouchDown(screenX, screenY);
        menuScreen.badgesButton.isTouchDown(screenX, screenY);
        menuScreen.soundOnButton.isTouchDown(screenX, screenY);
        menuScreen.soundOffButton.isTouchDown(screenX, screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (menuScreen.playButton.isTouchUp(screenX, screenY) && !clicked) {
            menuScreen.setupTween();
            clicked = true;
        } else if(menuScreen.highscoresButton.isTouchUp(screenX, screenY)) {
            adsController.getLeaderboardGPGS();
        } else if(menuScreen.badgesButton.isTouchUp(screenX, screenY)) {
            adsController.getAchievementsGPGS();
        } else if(menuScreen.soundOnButton.isTouchUp(screenX, screenY)) {
            menuScreen.setSoundOn(!menuScreen.soundOn);
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
