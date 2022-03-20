// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * animate multiple bouncing balls in multiple frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static Box greyBox = new Box(50, 50, 450, 450);
    private static Box yellowBox = new Box(450, 450, 150, 150);

    /**
     * cut array of string to new array in given size.
     * @param args array to cut.
     * @param start start the cut from here (included).
     * @param stop stop the cut here (excluded).
     * @return the cut array.
     */
    private static String[] cutArrayOfString(String[] args, int start, int stop) {
        int newSize = stop - start;
        String[] arr = new String[newSize];

        int index = 0;
        for (int i = start; i < stop; i++) {
            arr[index] = args[i];
            index++;
        }
        return arr;
    }

    /**
     * draw the grey and yellow rectangles.
     * @param gui GUI object to draw in surface from him.
     * @return the surface who drew on.
     */
    private static DrawSurface drawRectangles(GUI gui) {
        DrawSurface surface = gui.getDrawSurface();
        surface.setColor(Color.gray);
        surface.fillRectangle(50, 50, greyBox.getWidth(), greyBox.getHeight());
        surface.setColor(Color.yellow);
        surface.fillRectangle(450, 450, yellowBox.getWidth(), yellowBox.getHeight());
        return surface;
    }

    /**
     * main function - animate multiple bouncing balls in multiple frames.
     * @param args <p>represents the ball radii. the first half is the ball in the first frame
     *             and the second half in the second frame.</p>
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Frames Bouncing Balls", 700, 600);
        Sleeper sleeper = new Sleeper();

        //assuming args.length is even number.
        String[] firstRadius = cutArrayOfString(args, 0, args.length / 2);
        String[] secondRadius = cutArrayOfString(args, args.length / 2, args.length);

        MultipleBouncingBallsAnimation.sortBallsRadius(firstRadius);
        MultipleBouncingBallsAnimation.sortBallsRadius(secondRadius);

        Ball[] balls1 = MultipleBouncingBallsAnimation.generateBalls(firstRadius, greyBox);
        Ball[] balls2 = MultipleBouncingBallsAnimation.generateBalls(secondRadius, yellowBox);

        while (true) {
            DrawSurface surface = drawRectangles(gui);
            MultipleBouncingBallsAnimation.drawBalls(balls1, surface, greyBox);
            MultipleBouncingBallsAnimation.drawBalls(balls2, surface, yellowBox);
            sleeper.sleepFor(15);
            gui.show(surface);
        }
    }
}
