// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * animate multiple bouncing balls.
 */
public class MultipleBouncingBallsAnimation {
    // magic numbers
    private static final int BIG_BALLS_SIZE = 50;
    private static final Box CURRENT_BOX = new Box(300, 400);

    /**
     * convers String array to int array.
     *
     * @param arr String array to convert.
     * @return arr as int Array.
     */
    public static int[] stringArrayToInt(String[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = Integer.parseInt(arr[i]);
        }
        return newArr;
    }

    /**
     * sort the receiving balls radius String array.
     *
     * @param ballsRadius the receiving balls radius String array.
     */
    public static void sortBallsRadius(int[] ballsRadius) {
        for (int i = 0; i < ballsRadius.length - 1; i++) {
            for (int j = 0; j < ballsRadius.length - i - 1; j++) {
                if (ballsRadius[j] > ballsRadius[j + 1]) {
                    //swap
                    int temp = ballsRadius[j];
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
     * @param box               the box to generate the balls in.
     * @return balls array.
     */
    public static Ball[] generateSlowerBalls(int[] sortedBallsRadius, Box box) {
        Ball[] balls = new Ball[sortedBallsRadius.length];
        double dx = sortedBallsRadius.length + 1;
        double dy = sortedBallsRadius.length + 1;
        for (int i = 0; i < sortedBallsRadius.length; i++) {
            int radius = sortedBallsRadius[i];
            balls[i] = box.getRandomBallInBox(radius);
            balls[i].setVelocity(dx, dy);
            // smaller balls are faster.
            if (balls[i].getSize() < BIG_BALLS_SIZE) {
                dx--;
                dy--;
            }
        }
        return balls;
    }

    /**
     * main function - handle the animation of multiple bouncing balls.
     *
     * @param args array of the balls radius.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Bouncing Balls", CURRENT_BOX.getWidth(), CURRENT_BOX.getHeight());
        Sleeper sleeper = new Sleeper();

        int[] intArgs = stringArrayToInt(args);
        sortBallsRadius(intArgs);

        Ball[] balls = generateSlowerBalls(intArgs, CURRENT_BOX);

        BoxOfBallsAnimation boxOfBalls = new BoxOfBallsAnimation(CURRENT_BOX, balls);

        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            boxOfBalls.animateBalls(gui, surface, sleeper);
        }
    }
}
