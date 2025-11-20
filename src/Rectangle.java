public class Rectangle {
    private Point upperLeft;
    private double width, height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    //public java.util.List<Point> intersectionPoints(Line line);

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