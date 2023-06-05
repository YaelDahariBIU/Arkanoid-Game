// 325166510 Yael Dahari
package Animation;
import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * The method draws one frame on a given DrawSurface.
     *
     * @param d (DrawSurface) - the DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * The method determines whether the animation should stop.
     *
     * @return (boolean) - true if so, otherwise false
     */
    boolean shouldStop();

    /**
     * The method returns the waiting time.
     *
     * @return (long) - the time
     */
    long sleepTime();

    /**
     * The method returns if a bonus event should occur.
     *
     * @return (boolean) - true if so, otherwise false
     */
    boolean isBonus();

    /**
     * The method activates bonus event.
     *
     * @param d (DrawSurface)
     */
    void activateBonusEvent(DrawSurface d);
}