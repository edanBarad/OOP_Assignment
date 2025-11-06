import biuoop.DrawSurface;
import biuoop.GUI;

public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;

    // constructor
    public Ball(Point center, int r, java.awt.Color color){
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(3, 3);
    }

    // accessors
    public int getX() {
        return (int)this.center.getX();
    }
    public int getY(){
        return (int)this.center.getY();
    }
    public int getSize(){
        return this.r;
    }
    public java.awt.Color getColor(){
        return this.color;
    }

    public void setX(int x){
        this.center.setX(x);
    }
    public void setY(int y){
        this.center.setY(y);
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface){
        surface.setColor(this.color);
        surface.fillCircle(this.getX(),this.getY(),this.r);
    }

    public void setVelocity(Velocity v){
        this.velocity = v;
    }
    public void setVelocity(double dx, double dy){
        this.velocity = new Velocity(dx, dy);
    }
    public Velocity getVelocity(){
        return this.velocity;
    }

    public void moveOneStep() {
        double nextX = this.center.getX() + this.velocity.getDx();
        double nextY = this.center.getY() + this.velocity.getDy();
        if (nextX + this.r > 200 || nextX - this.r < 0) {
            this.velocity.setDx(-this.velocity.getDx());
        }
        if (nextY + this.r > 200 || nextY - this.r < 0) {
            this.velocity.setDy(-this.velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }


}