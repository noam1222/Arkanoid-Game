package GameHandler;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Stream for stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param keyboardSensor keyboard sensor to listen if the user pressed.
     * @param key key to listen to.
     * @param animation animation to run and stop.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
                this.isAlreadyPressed = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.animation.shouldStop() || this.stop;
    }
}