package Geometry;

/**
 * class who represent line between tow points.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * constructor - initialize the start point of the line and the end point of the line by the passed points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * <p>constructor - initialize the start point of the line and the start point of the line
     * by the passed arguments by convert them to Geometry.Point type.</p>
     *
     * @param x1 x value of the start point of the line
     * @param y1 y value of the start point of the line
     * @param x2 x value of the start point of the line
     * @param y2 y value of the start point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculate the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * get the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * get the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculate the line slope (in mathe marked as 'm').
     *
     * @return the line slope, null if the line is vertical.
     */
    private Double lineSlope() {
        //handle vertical line (no slope)
        if (Point.doubleEquals(this.start.getX(), this.end.getX())) {
            return null;
        }
        // slope of straight line formula
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * calculate the line constant (in math marked as 'c').
     *
     * @param slope the line slope
     * @return the line constant
     */
    private double lineConstant(double slope) {
        if (Point.doubleEquals(slope, 0)) {
            return this.start.getY();
        }
        /* from equation of a line formula:
         y - y1 = m(x - x1) => y = mx -mx1 + y1 => constant = y1 - mx1 */
        return this.start.getY() - slope * this.start.getX();
    }

    /**
     * check if one line is partly contain in the other.
     *
     * @param other the other line to check if he partly contains in this line or the opposite.
     * @return true if one line contain other line, false otherwise.
     */
    private boolean isPartlyContain(Line other) {
        if (!this.isParallel(other)) {
            return false;
        }
        if (this.isPointInLine(other.start) || this.isPointInLine(other.end)) {
            return true;
        }
        return other.isPointInLine(this.start) || other.isPointInLine(this.end);
    }

    /**
     * handle the intersection points of parallel lines.
     *
     * @param other the other line to handle his intersection point with this line.
     * @return true if the lines only "touch" each other in onr point. false otherwise.
     */
    private Point intersectionOfParallelLines(Line other) {
        if (this.start.equals(other.start)) {
            if (this.isPointInLine(other.end) || other.isPointInLine(this.end)) {
                // infinity intersection points.
                return null;
            }
            // the lines "touch" each other in one point.
            return this.start;
        }
        if (this.start.equals(other.end)) {
            if (this.isPointInLine(other.start) || other.isPointInLine(this.end)) {
                // infinity intersection points.
                return null;
            }
            // the lines "touch" each other in one point.
            return this.start;
        }
        if (this.end.equals(other.start)) {
            if (this.isPointInLine(other.end) || other.isPointInLine(this.start)) {
                // infinity intersection points.
                return null;
            }
            // the lines "touch" each other in one point.
            return this.end;
        }
        if (this.end.equals(other.end)) {
            if (this.isPointInLine(other.start) || other.isPointInLine(this.start)) {
                // infinity intersection points.
                return null;
            }
            // the lines "touch" each other in one point.
            return this.end;
        }
        return null;
    }

    /**
     * check if two lines are parallel.
     *
     * @param other the other line to check if this line is parallel to.
     * @return true if parallel, false otherwise.
     */
    private boolean isParallel(Line other) {
        if (this.lineSlope() == null && other.lineSlope() == null) {
            return true;
        }
        if (this.lineSlope() != null && other.lineSlope() != null) {
            return Point.doubleEquals(this.lineSlope(), other.lineSlope());
        }
        return false;
    }

    /**
     * check if passed point who in this infinity line is in this line.
     *
     * @param p the point to check
     * @return true if p in line, false otherwise.
     */
    public boolean isPointInLine(Point p) {
        if (p == null) {
            return false;
        }
        double biggerX = Math.max(this.start.getX(), this.end.getX());
        double smallerX = Math.min(this.start.getX(), this.end.getX());
        double biggerY = Math.max(this.start.getY(), this.end.getY());
        double smallerY = Math.min(this.start.getY(), this.end.getY());

        boolean b1 = biggerX >= p.getX() || Point.doubleEquals(biggerX, p.getX());
        boolean b2 = p.getX() >= smallerX || Point.doubleEquals(smallerX, p.getX());
        boolean b3 = biggerY >= p.getY() || Point.doubleEquals(biggerY, p.getY());
        boolean b4 = p.getY() >= smallerY || Point.doubleEquals(smallerY, p.getY());
        return b1 && b2 && b3 && b4;
    }

    /**
     * check if this and other line are intersecting.
     *
     * @param other line to check if intersecting
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.isPartlyContain(other)) {
            return true;
        }
        return this.intersectionWith(other) != null;
    }

    /**
     * calculate the intersection point between this line and other.
     *
     * @param other line to check intersection point
     * @return the intersection point if the lines intersect once, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersectionPoint;
        if (this.isParallel(other)) {
            intersectionPoint = this.intersectionOfParallelLines(other);
            if (intersectionPoint != null) {
                return intersectionPoint;
            }
        }

        Double m1, m2;
        double c1, c2;
        // check for vertical lines.
        if (this.lineSlope() == null) {
            c1 = this.start.getX();
            m2 = other.lineSlope();
            if (m2 == null) {
                return null;
            }
            c2 = other.lineConstant(m2);
            // assign c1 in line equation to find yIntersection
            double yIntersection = m2 * c1 + c2;
            intersectionPoint = new Point(c1, yIntersection);
        } else if (other.lineSlope() == null) {
            c2 = other.start.getX();
            m1 = this.lineSlope();
            if (m1 == null) {
                return null;
            }
            c1 = this.lineConstant(m1);
            double yIntersection = m1 * c2 + c1;
            // assign c2 in line equation to find yIntersection
            intersectionPoint = new Point(c2, yIntersection);
        } else {
           /* this.line equation: y = m1x + c1
           other line equation: y = m2x + c2
           => m2x + c2 = m1x + c1
           => m2x - m1x = c1 - c2
           => (m2 - m1)x = c1 - c2
           => x = (c1 - c2) / (m2 - m1)
            m1, m2 can't be null because of the 'if' blocks before. */
            m1 = this.lineSlope();
            c1 = this.lineConstant(m1);
            m2 = other.lineSlope();
            c2 = other.lineConstant(m2);

            double xIntersection = (c1 - c2) / (m2 - m1);
            // assign xIntersection in line equation to find yIntersection
            double yIntersection = m1 * xIntersection + c1;
            intersectionPoint = new Point(xIntersection, yIntersection);
        }

        //check if intersection point in line
        if (this.isPointInLine(intersectionPoint) && other.isPointInLine(intersectionPoint)) {
            return intersectionPoint;
        }
        return null;
    }


    /**
     * return the closest intersection point to the start of the line.
     *
     * @param rect rectangle object to check the intersection with.
     * @return false if this line doesn't intersection with the rectangle, otherwise the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestIntersectionPoint = this.intersectionWith(rect.getBorder()[0]);
        for (int i = 1; i < rect.getBorder().length; i++) {
            Point temp = this.intersectionWith(rect.getBorder()[i]);
            if (closestIntersectionPoint == null) {
                closestIntersectionPoint = temp;
            } else if (temp != null) {
                closestIntersectionPoint = this.start.getClosestPoint(closestIntersectionPoint, temp);
            }
        }
        return closestIntersectionPoint;
    }

    /**
     * check if this line and other are identical.
     *
     * @param other line to check if identical
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.length() != other.length()) {
            return false;
        }
        if (!this.start.equals(other.start)) {
            if (!this.start.equals(other.end)) {
                return false;
            }
            if (this.end.equals(other.start)) {
                return true;
            }
        }
        return this.end.equals(other.end);
    }
}