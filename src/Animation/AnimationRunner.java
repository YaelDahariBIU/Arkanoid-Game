package Animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class AnimationRunner {
    static final int MSPF = 1000;
    static final int POSITIVE = 0;
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    public AnimationRunner() {
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
        this.gui = new GUI("game :)", 800, 600);
    }

    public GUI getGui() {
        return gui;
    }

    public void run(Animation animation) {
        int millisecondsPerFrame = MSPF / this.framesPerSecond;
        DrawSurface d;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            d = gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);
            sleeper.sleepFor(animation.sleepTime());
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > POSITIVE) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (animation.isBonus()) {
            d = gui.getDrawSurface();
            animation.activateBonusEvent(d);
            gui.show(d);
        }
    }
}
