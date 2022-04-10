// 209407162 Noam Maimon

import java.util.ArrayList;
import java.util.List;

/**
 * class that represented rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Line[] border;

    /**
     * rectangle constructor - in addition to received field create line array of the borders of the rectangle.
     *
     * @param upperLeft upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        this.border = new Line[4];
        Point bottomRight = this.upperLeft.getPointInDistance(this.width, this.height);
        // up line
        this.border[0] = new Line(this.upperLeft, this.upperLeft.getPointInDistance(this.width, 0));
        // right line
        this.border[1] = new Line(bottomRight, bottomRight.getPointInDistance(0, -this.height));
        // bottom line
        this.border[2] = new Line(bottomRight.getPointInDistance(-this.width, 0), bottomRight);
        // left line
        this.border[3] = new Line(this.upperLeft.getPointInDistance(0, this.height), this.upperLeft);
    }

    /**
     * find the intersection points of this rectangle with the specified line.
     *
     * @param line the line to check intersection point.
     * @return ArrayList of the intersection points, possibly empty.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionsList = new ArrayList<Point>();

        Line reverseLine = new Line(line.end(), line.start());
        Point p1 = line.closestIntersectionToStartOfLine(this);
        Point p2 = reverseLine.closestIntersectionToStartOfLine(this);
        if (p1 != null) {
            intersectionsList.add(p1);
            if (p2 != null && !p1.equals(p2)) {
                intersectionsList.add(p2);
            }
        }
        return intersectionsList;
    }

    /**
     * get the width of the rectangle.
     *
     * @return Return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get the height of the rectangle.
     *
     * @return Return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * get the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * get the order of the rectangle.
     *
     * @return array of the borders of the rectangle in this order - up, right, bottom, left.
     */
    public Line[] getBorder() {
        return this.border;
    }

    /**
     * check if received point is in this rectangle.
     *
     * @param p the point to check.
     * @return true if the point in this rectangle, false otherwise.
     */
    public boolean isPointIn(Point p) {
        for (Line bord : this.border) {
            if (bord.isPointInLine(p)) {
                return true;
            }
        }
        if (p.getY() <= this.border[0].start().getY() || p.getY() >= this.border[2].start().getY()) {
            return false;
        }
        if (p.getX() <= this.border[3].start().getX() || p.getX() >= this.border[1].start().getX()) {
            return false;
        }
        return true;
    }
}