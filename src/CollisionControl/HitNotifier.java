// 325166510 Yael Dahari
package CollisionControl;

/**
 * The HitNotifier interface indicate that objects that implement it send
 * notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * The method adds hl as a listener to hit events.
     *
     * @param hl (HitListener) - the hl
     */
    void addHitListener(HitListener hl);

    /**
     * The method removes hl from the list of listeners to hit events.
     *
     * @param hl (HitListener) - the hl
     */
    void removeHitListener(HitListener hl);
}
