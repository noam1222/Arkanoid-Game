// 209407162 Noam Maimon

/**
 * class who describe point in two-dimensional space.
 */
public class Point {
    private final double x;
    private final double y;
    // epsilon to define double equal operation according to the assignment demands.
    private static final double EPSILON = 1E-10;

    /**
     * constructor - assign the x, y who passed to this.x, this.y.
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance of this point to the other point.
     * @param other the other point to check
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double xDistance = this.x - other.x;
        double yDistance = this.y - other.y;
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    /**
     * <p>new double equals operation (according to the assignment demands) -
     * check if the difference between tow double numbers are less or equal to EPSILON.</p>
     * @param num1 first number to check
     * @param num2 second number to check
     * @return true if the difference between the numbers are less or equal to epsilon, false otherwise.
     */
    public static boolean doubleEquals(double num1, double num2) {
        return Math.abs(num1 - num2) <= EPSILON;
    }

    /**
     * check if 2 points are equal.
     * @param other the other point to check
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return doubleEquals(this.x, other.x) && doubleEquals(this.y, other.y);
    }

    /**
     * get the x value of this point.
     * @return the x and value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * get the y value of this point.
     * @return the y and value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * get point with received distance from this point.
     * @param width the x-axis distance.
     * @param height the y-axis distance.
     * @return new Point of received distance from this point.
     */
    public Point getPointInDistance(double width, double height) {
        return new Point(this.getX() + width, this.getY() + height);
    }

    /**
     * get the closest point from tow points to this point.
     * @param p1 first point to check.
     * @param p2 second point to check.
     * @return the closest point to this.
     */
    public Point getClosestPoint(Point p1, Point p2) {
        if (this.distance(p1) <= this.distance(p2)) {
            return p1;
        }
        return p2;
    }
}