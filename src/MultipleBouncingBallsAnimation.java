// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * animate multiple bouncing balls.
 */
public class MultipleBouncingBallsAnimation {
    // magic numbers
    private static int bigBallsSize = 50;
    private static Box currentBox = new Box(300, 400);

    /**
     * sort the receiving balls radius String array.
     *
     * @param ballsRadius the receiving balls radius String array.
     */
    public static void sortBallsRadius(String[] ballsRadius) {
        for (int i = 0; i < ballsRadius.length - 1; i++) {
            for (int j = 0; j < ballsRadius.length - i - 1; j++) {
                if (Integer.parseInt(ballsRadius[j]) > Integer.parseInt(ballsRadius[j + 1])) {
                    //swap
                    String temp = ballsRadius[j];
                    ballsRadius[j] = ballsRadius[j + 1];
                    ballsRadius[j + 1] = temp;
                }
            }
        }
    }

    /**
     * generate the balls array in ascending speed order.
     *
     * @param sortedBallsRadius String array of balls radius who sorted by size.
     * @param box the box to generate the balls in.
     * @return balls array.
     */
    public static Ball[] generateBalls(String[] sortedBallsRadius, Box box) {
        Ball[] balls = new Ball[sortedBallsRadius.length];
        double dx = sortedBallsRadius.length;
        double dy = sortedBallsRadius.length;
        for (int i = 0; i < sortedBallsRadius.length; i++) {
            int radius = Integer.parseInt(sortedBallsRadius[i]);
            balls[i] = box.getBallInBox(radius);
            balls[i].setVelocity(dx, dy);
            // smaller balls are faster.
            if (balls[i].getSize() < bigBallsSize) {
                dx--;
                dy--;
            }
        }
        return balls;
    }

    /**
     * draw balls in receiving box.
     * @param balls Balls array to draw.
     * @param surface the main surface to draw on.
     * @param box the box limit do draw in.
     */
    public static void drawBalls(Ball[] balls, DrawSurface surface, Box box) {
        for (Ball ball : balls) {
            ball.moveOneStep(box);
            ball.drawOn(surface);
        }
    }

    /**
     * main function - handle the animation of multiple bouncing balls.
     *
     * @param args array of the balls radius.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Bouncing Balls", currentBox.getWidth(), currentBox.getHeight());
        Sleeper sleeper = new Sleeper();

        sortBallsRadius(args);

        Ball[] balls = generateBalls(args, currentBox);

        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            drawBalls(balls, surface, currentBox);
            sleeper.sleepFor(15);
            gui.show(surface);
        }
    }
}
