// 325166510 Yael Dahari
package Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    static final int MSPF = 1000;
    static final int POSITIVE = 0;
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     */
    public AnimationRunner() {
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
        this.gui = new GUI("game :)", 800, 600);
    }

    /**
     * The method returns the keyboard sensor.
     *
     * @return (KeyboardSensor) - the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * The method closes the animation.
     */
    public void close() {
        this.gui.close();
    }

    /**
     * The method runs a given animation.
     *
     * @param animation (Animation) - the animation
     */
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
        // checking for a bonus event
        if (animation.isBonus()) {
            d = gui.getDrawSurface();
            animation.activateBonusEvent(d);
            gui.show(d);
        }
    }
}
