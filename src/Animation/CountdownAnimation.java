// 325166510 Yael Dahari
package Animation;
import GameControl.SpriteControl.SpriteCollection;
import biuoop.DrawSurface;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds
 * seconds, and on top of them it will show a countdown from countFrom back to
 * 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    static final int SINGLE = 1;
    static final int NONE = 0;
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private String num;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds (double) the num of seconds
     * @param countFrom (int) - the count from
     * @param gameScreen (SpriteCollection) - the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.num = String.valueOf(countFrom);
        this.numOfSeconds = numOfSeconds / (double) countFrom;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d, "----");
        d.drawText(d.getWidth() / 2, 350, num, 60);
        reduceCount();
    }
    @Override
    public boolean shouldStop() {
        return this.countFrom == NONE;
    }

    @Override
    public long sleepTime() {
        return (long) (numOfSeconds * 1000);
    }

    @Override
    public boolean isBonus() {
        return false;
    }


    @Override
    public void activateBonusEvent(DrawSurface d) {
    }

    private void reduceCount() {
        this.countFrom = this.countFrom - SINGLE;
        this.num = String.valueOf(countFrom);
    }
}