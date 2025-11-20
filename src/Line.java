public class Line {
    private Point start = new Point(0, 0);
    private Point end = new Point(0, 0);;
    private double slope;
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        setSlope();
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        setSlope();
    }

    private void setSlope(){
        if (this.start.equals(this.end)) this.slope = Double.NaN;
        else if (this.start.getX() == this.end.getX()){
            this.slope = Double.POSITIVE_INFINITY;      //Line is vertical
        }else if (this.start.getY() == this.end.getY()){
            this.slope = 0.0;
        }else {
            this.slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        }
    }

    public void setStart(Point start){
        this.start = start;
        setSlope();
    }

    public void setEnd(Point end){
        this.end = end;
        setSlope();
    }

    public double getSlope(){
        return this.slope;
    }

    // Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    // Returns the middle point of the line
    public Point middle() {
        return new Point((this.start.getX()+this.end.getX())/2, (this.start.getY()+this.end.getY())/2);
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.slope == other.getSlope()){
            if (this.isOnLine(other.start) || this.isOnLine(other.end) || other.isOnLine(this.start) || other.isOnLine(this.end)) return true;
            return false;
        }
        else{
            Point intersection = this.intersectionWith(other);
            if (intersection == null) return false;             //Intersection is out of bounds
            else return this.isOnLine(intersection) && other.isOnLine(intersection);
        }
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double x, y;
        //Due to calculations on tablet
        x = (this.slope*this.start.getX() - this.start.getY() - other.getSlope()*other.start().getX() + other.start.getY()) / (this.slope - other.getSlope());
        y = this.slope*(x-this.start.getX()) + this.start.getY();
        Point point = new Point(x, y);
        if (this.isOnLine(point) && other.isOnLine(point)) return point;
        else return null;
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start.equals(other.start())){
            return this.end.equals(other.end());
        } else if (this.start.equals(other.end())) {
            return this.end.equals(other.start());
        }
        return false;
    }

    //This function will return if a given point is on the line using the formula (y-y1)=m(x-x1)
    private boolean isOnLine(Point point){
        if (this.slope == Double.POSITIVE_INFINITY){
            return (point.getX() == this.start.getX() && point.distance(this.middle()) <= this.start.distance(this.middle()));
        }else if (this.slope == 0.0){
            return (point.getY() == this.start.getY() && point.distance(this.middle()) <= this.start.distance(this.middle()));
        }
        final double EPSILON = 0.000000001; //
        if (Math.abs((point.getY()-this.start.getY()) - (this.slope*(point.getX()-this.start.getX()))) < EPSILON){   //If satisfies equation
            return point.distance(this.middle()) <= this.start.distance(this.middle());         //Check if between start and end
        }
        return false;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestIntersect = null;
        double minDistance = Double.POSITIVE_INFINITY;
        Line[] walls = {
                // Left wall (top to bottom)
                new Line(rect.getUpperLeft(),
                        new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight())),
                // Top wall (left to right)
                new Line(rect.getUpperLeft(),
                        new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY())),
                // Right wall (top to bottom)
                new Line(new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY()),
                        new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight())),
                // Bottom wall (left to right)
                new Line(new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight()),
                        new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY() + rect.getHeight()))
        };
        // Check each wall for intersections
        for (Line wall : walls) {
            Point intersect = this.intersectionWith(wall);
            if (intersect != null) {
                double distance = this.start.distance(intersect);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestIntersect = intersect;
                }
            }
        }
        return closestIntersect;
    }

}