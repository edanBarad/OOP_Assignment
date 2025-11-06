// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx, dy;
    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);  // calculate from angle and speed
        double dy = -(speed * Math.cos(radians));  // calculate from angle and speed - Minus because GUI
        return new Velocity(dx, dy);  // use existing constructor
    }


    public double getDx() {
        return dx;
    }
    public double getDy() {
        return dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }
    public void setDy(double dy) {
        this.dy = dy;
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point((p.getX()+dx), (p.getY() + dy));
    }
}