package Notification;

/**
 * interface that indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * add hit listener as a listener to hit events.
     * @param hl hit listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * remove hit listener from the list of listeners to hit events.
     * @param hl hit listener to remove.
     */
    void removeHitListener(HitListener hl);
}