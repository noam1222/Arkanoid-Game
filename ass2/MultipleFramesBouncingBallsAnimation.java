// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * animate multiple bouncing balls in multiple frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final Box GREY_BOX = new Box(50, 50, 450, 450);
    private static final Box YELLOW_BOX = new Box(450, 450, 150, 150);

    /**
     * draw the grey and yellow rectangles.
     * @param gui GUI object to draw in surface from him.
     * @return the surface who drew on.
     */
    private static DrawSurface drawRectangles(GUI gui) {
        DrawSurface surface = gui.getDrawSurface();
        surface.setColor(Color.gray);
        surface.fillRectangle(50, 50, GREY_BOX.getWidth(), GREY_BOX.getHeight());
        surface.setColor(Color.yellow);
        surface.fillRectangle(450, 450, YELLOW_BOX.getWidth(), YELLOW_BOX.getHeight());
        return surface;
    }

    /**
     * main function - animate multiple bouncing balls in multiple frames.
     * @param args <p>represents the ball radii. the first half is the ball in the first frame
     *             and the second half in the second frame.</p>
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Frames Bouncing Balls", 700, 650);
        Sleeper sleeper = new Sleeper();

        //assuming args.length is even number.
        String[] firstRadius = ArraysOperations.cutArrayOfString(args, 0, args.length / 2);
        String[] secondRadius = ArraysOperations.cutArrayOfString(args, args.length / 2, args.length);

        // convert to int arrays.
        int[] firstRadiusInts = ArraysOperations.stringArrayToInt(firstRadius);
        int[] secondRadiusInts = ArraysOperations.stringArrayToInt(secondRadius);

        //sort the arrays.
        ArraysOperations.sortIntArray(firstRadiusInts);
        ArraysOperations.sortIntArray(secondRadiusInts);

        // insert the balls into the array as requested in the assignment.
        Ball[] balls1 = MultipleBouncingBallsAnimation.generateSlowerBalls(firstRadiusInts, GREY_BOX);
        Ball[] balls2 = MultipleBouncingBallsAnimation.generateSlowerBalls(secondRadiusInts, YELLOW_BOX);

        // create grey and yellow boxes.
        BoxOfBallsAnimation greyBoxOfBalls = new BoxOfBallsAnimation(GREY_BOX, balls1);
        BoxOfBallsAnimation yellowBoxOfBalls = new BoxOfBallsAnimation(YELLOW_BOX, balls2);

        //animate the balls in their boxes.
        while (true) {
            DrawSurface surface = drawRectangles(gui);
            greyBoxOfBalls.drawBalls(surface);
            yellowBoxOfBalls.animateBalls(gui, surface, sleeper);
        }
    }
}