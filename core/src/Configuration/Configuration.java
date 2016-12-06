package Configuration;

import com.badlogic.gdx.Gdx;

public class Configuration {

    // ADS configuration
    public static final String BANNER_AD_UNIT_ID = "ca-app-pub-7817940621390472/9905897542";
    public static final String INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-7817940621390472/2382630747";
    public static final int AD_FREQUENCY = 2; // Higher value = Less Ads

    // BOOLEAN CONFIGS
    public static final boolean SHOW_SPLASH_SCREEN = true;
    public static final boolean SHOW_BANNER_AD = false;

    // Achievements IDs - POINTS
    public static final String ACHIEVEMENT_5_POINTS = "CgkI047ZnekeEAIQAg";
    public static final String ACHIEVEMENT_10_POINTS = "CgkI047ZnekeEAIQAw";
    public static final String ACHIEVEMENT_25_POINTS = "CgkI047ZnekeEAIQBA";
    public static final String ACHIEVEMENT_50_POINTS = "CgkI047ZnekeEAIQBQ";
    public static final String ACHIEVEMENT_100_POINTS = "CgkI047ZnekeEAIQBg";
    public static final String ACHIEVEMENT_200_POINTS = "CgkI047ZnekeEAIQBw";
    // Achievements IDs - GAMES PLAYED
    public static final String ACHIEVEMENT_10_GAMES = "CgkI047ZnekeEAIQCA";
    public static final String ACHIEVEMENT_25_GAMES = "CgkI047ZnekeEAIQCQ";
    public static final String ACHIEVEMENT_50_GAMES = "CgkI047ZnekeEAIQCg";
    public static final String ACHIEVEMENT_100_GAMES = "CgkI047ZnekeEAIQCw";
    public static final String ACHIEVEMENT_200_GAMES = "CgkI047ZnekeEAIQDA";

    // Leaderboards ID
    public static final String LEADERBOARD_ID = "CgkI047ZnekeEAIQAA";



    public static final float FONT_SIZE = 2.5f;

    // DOTS CONFIGS
    public static final int SCROLL_SPEED = (int) (Gdx.graphics.getHeight() * 0.781f); // Increase float value to increase scroll speed
    public static final int DOT_RADIUS = (int) (Gdx.graphics.getWidth() * 0.125f); // Increase float value to increase radius
    public static final int DOT_RADIUS_MAX = (int) (Gdx.graphics.getWidth() * (1 / 36f)); // Increase float value to increase max animation radius
    public static final float DOT_POSITION_Y = Gdx.graphics.getHeight() * .55f; // Increase float value to increase starting y-position

    // RING CONFIGS
    public static final int RING_RADIUS = (int) (Gdx.graphics.getHeight() * 2f); // Increase float value to increase ring radius
    public static final int RING_SPEED_1 = (int) (Gdx.graphics.getWidth() * (7 / 720f)); // 7
    public static final int RING_SPEED_2 = (int) (Gdx.graphics.getWidth() * (10 / 720f)); // 10
    public static final int RING_SPEED_3 = (int) (Gdx.graphics.getWidth() * (13 / 720f)); // 13
    public static final int RING_SPEED_4 = (int) (Gdx.graphics.getWidth() * (17 / 720f)); // 16
    public static final int RING_SPEED_5 = (int) (Gdx.graphics.getWidth() * (21 / 720f)); // 21



    // DON'T CHANGE THESE
    public static final String[] COLORS = {"blue", "green", "purple", "red", "yellow"};
}
