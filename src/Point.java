// 209407162 Noam Maimon

/**
 * class who describe point in two-dimensional space.
 */
public class Point {
    private double x;
    private double y;

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
     * check if 2 points are equal.
     * @param other the other point to check
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return this.x == other.x && this.y == other.y;
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
}