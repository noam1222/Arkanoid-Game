// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * class that make bouncing ball animation.
 */
public class BouncingBallAnimation {
    static final Box CURRENT_BOX = new Box(200, 200);
    static final int BALL_RADIUS = 300;

    /**
     * draw animation of bouncing ball.
     *
     * @param start the start point of the ball.
     * @param dx    the ball speed in the x-axis.
     * @param dy    the ball speed in the y-axis.
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", CURRENT_BOX.getWidth(), CURRENT_BOX.getHeight());
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), BALL_RADIUS, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        BoxOfBallsAnimation boxOfBall = new BoxOfBallsAnimation(CURRENT_BOX, ball);
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            boxOfBall.animateBalls(gui, surface, sleeper);
        }
    }

    /**
     * main function of the bouncing ball animation.
     *
     * @param args array of String. first and second elements are the coordinate of the starting point of the ball./
     *             second ant third elements are the velocity of the ball.
     */
    public static void main(String[] args) {
        Point startPoint = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        drawAnimation(startPoint, dx, dy);
    }
}
