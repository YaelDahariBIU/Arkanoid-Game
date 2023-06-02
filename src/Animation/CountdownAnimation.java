package Animation;

import GameControl.SpriteControl.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private String num;
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.num = String.valueOf(countFrom);
        this.numOfSeconds = numOfSeconds / (double) countFrom;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.drawText(400, 350, num, 40);
        reduceCount();
    }
    @Override
    public boolean shouldStop() {
        return this.countFrom == 0;
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
        this.countFrom = this.countFrom - 1;
        this.num = String.valueOf(countFrom);
    }
}