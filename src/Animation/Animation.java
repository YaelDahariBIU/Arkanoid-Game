package Animation;

import biuoop.DrawSurface;

public interface Animation {
    void doOneFrame(DrawSurface d);
    boolean shouldStop();
    long sleepTime();
    boolean isBonus();
    void activateBonusEvent(DrawSurface d);
}