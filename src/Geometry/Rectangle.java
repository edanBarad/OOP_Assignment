package Geometry;

public class Rectangle {
    private Point upperLeft;
    private double width, height;

    //Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    //Returns an array of walls(Lines) in the order: left, top, right, bottom
    public Line[] getWalls(){
        return new Line[]{
                //Left wall
                new Line(this.upperLeft,
                        new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height)),
                //Top
                new Line(this.upperLeft,
                        new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY())),
                //Right wall
                new Line(new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()),
                        new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height)),
                //Bottom
                new Line(new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height),
                        new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height))
        };
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new java.util.ArrayList<>();
        // Create the 4 walls of the rectangle
        Line[] walls = this.getWalls();
        // Check each wall for intersections
        for (Line wall : walls) {
            Point intersection = line.intersectionWith(wall);
            if (intersection != null) {
                // Check if this point is already in the list (avoid duplicates for corners)
                if (!isPointInList(intersections, intersection)) {
                    intersections.add(intersection);
                }
            }
        }
        return intersections;
    }

    // Helper method to check if a point is already in the list (for corner cases)
    private boolean isPointInList(java.util.List<Point> list, Point point) {
        for (Point p : list) {
            if (p.equals(point)) {
                return true;
            }
        }
        return false;
    }

    // Return the width and height of the rectangle
    public double getWidth(){
        return this.width;
    }
    public double getHeight(){
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft(){
        return this.upperLeft;
    }
}