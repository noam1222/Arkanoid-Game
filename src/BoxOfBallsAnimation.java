// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * handle animation of multiple balls in rectangle on the screen.
 */
public class BoxOfBallsAnimation {
    private final Ball[] balls;
    private final Box box;

    /**
     * constructor - initialize BoxOfBallsAnimation object with multiple balls.
     * @param box the box where the balls are in.
     * @param balls the balls array to handle in the box.
     */
    public BoxOfBallsAnimation(Box box, Ball[] balls) {
        this.box = box;
        this.balls = balls;
        this.handelBadBallsLocationAndSize();
    }

    /**
     * constructor - initialize BoxOfBallsAnimation object with one ball.
     * @param box the box where the ball are in.
     * @param ball the ball to handle in the box.
     */
    public BoxOfBallsAnimation(Box box, Ball ball) {
        this.box = box;
        this.balls = new Ball[1];
        this.balls[0] = ball;
        this.handelBadBallsLocationAndSize();
    }

    /**
     * handle the velocity of the ball respectively to the box border.
     * @param ball the ball to handle his velocity.
     */
    private void handleVelocity(Ball ball) {
        if (box.isBallWidthOutOfBorder(ball)) {
            ball.setVelocity(-ball.getVelocity().getDx(), ball.getVelocity().getDy());
        }
        if (box.isBallHeightOutOfBorder(ball)) {
            ball.setVelocity(ball.getVelocity().getDx(), -ball.getVelocity().getDy());
        }
    }

    /**
     * if the ball radius is bigger than the box or the box center is out of box solve the problems.
     */
    private void handelBadBallsLocationAndSize() {
        for (int i = 0; i < this.balls.length; i++) {
            if (this.balls[i].getSize() >= this.box.getWidth() || this.balls[i].getSize() >= this.box.getHeight()) {
                int newRadius = this.box.squeezeRadiusToBox(this.balls[i].getSize());
                this.balls[i].setSize(newRadius);
            }
            if (box.isBallWidthOutOfBorder(this.balls[i]) || box.isBallHeightOutOfBorder(this.balls[i])) {
                Point newCenter = this.box.getRandomCenterPointInBox(this.balls[i].getSize());
                this.balls[i].setCenter(newCenter);
            }
        }
    }

    /**
     * draw this.balls on surface.
     * @param surface the surface to draw the balls in.
     */
    public void drawBalls(DrawSurface surface) {
        for (Ball ball : this.balls) {
            this.handleVelocity(ball);
            ball.moveOneStep();
            ball.drawOn(surface);
        }
    }

    /**
     * animate this.balls on the screen.
     * @param gui the gui to draw on his screen.
     * @param surface the surface to draw this.balls on.
     * @param sleeper to make the screen sleep for 15 milliseconds.
     */
    public void animateBalls(GUI gui, DrawSurface surface, Sleeper sleeper) {
        this.drawBalls(surface);
        gui.show(surface);
        sleeper.sleepFor(20);
    }
}
