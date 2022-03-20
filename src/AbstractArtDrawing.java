// 209407162 Noam Maimon

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * <p>draw 10 randomly lines so that their middle line marked as blue circle\
 * and their intersection points marked as red circles.</p>
 */
public class AbstractArtDrawing {
    // magic numbers
    private static final int POINT_RADIUS = 3;
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;

    // magic colors
    private static final Color LINE_COLOR = Color.black;
    private static final Color MIDDLE_COLOR = Color.blue;
    private static final Color INTERSECTING_COLOR = Color.RED;

    /**
     * generate random line in the window width and height.
     *
     * @return the new line.
     */
    public static Line generateRandomLine() {
        Random rand = new Random();
        double x1 = rand.nextDouble() * WINDOW_WIDTH;
        double y1 = rand.nextDouble() * WINDOW_HEIGHT;
        double x2 = rand.nextDouble() * WINDOW_WIDTH;
        double y2 = rand.nextDouble() * WINDOW_HEIGHT;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * draw line.
     *
     * @param l line to draw.
     * @param d draw surface to draw in.
     */
    public static void drawLine(Line l, DrawSurface d) {
        d.setColor(LINE_COLOR);
        int x1 = (int) l.start().getX();
        int y1 = (int) l.start().getY();
        int x2 = (int) l.end().getX();
        int y2 = (int) l.end().getY();
        d.drawLine(x1, y1, x2, y2);
    }

    /**
     * draw middle point in line as blue circle.
     *
     * @param l line to draw his middle point.
     * @param d draw surface to draw in.
     */
    private static void drawMiddlePoint(Line l, DrawSurface d) {
        d.setColor(MIDDLE_COLOR);
        Point middle = l.middle();
        int xMiddle = (int) middle.getX();
        int yMiddle = (int) middle.getY();
        d.fillCircle(xMiddle, yMiddle, POINT_RADIUS);
    }

    /**
     * draw two lines intersecting point as red circle.
     *
     * @param l1 first line to draw his intersecting point.
     * @param l2 second line to draw his intersecting point.
     * @param d  draw surface to draw in.
     */
    public static void drawIntersectingPoints(Line l1, Line l2, DrawSurface d) {
        d.setColor(INTERSECTING_COLOR);
        if (l1.isIntersecting(l2)) {
            Point intersectingPoint = l1.intersectionWith(l2);
            if (intersectingPoint != null) {
                d.fillCircle((int) intersectingPoint.getX(), (int) intersectingPoint.getY(), POINT_RADIUS);
            }
        }
    }

    /**
     * main function - draw 1- randomly lines with their middle and intersection points.
     *
     * @param args array of string that gets from the terminal - unused in this class.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Random Lines", WINDOW_WIDTH, WINDOW_HEIGHT);
        DrawSurface drawSurface = gui.getDrawSurface();
        Line[] lines = new Line[10];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = generateRandomLine();
            drawLine(lines[i], drawSurface);
            drawMiddlePoint(lines[i], drawSurface);
        }
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                drawIntersectingPoints(lines[i], lines[j], drawSurface);
            }
        }
        gui.show(drawSurface);
    }
}
