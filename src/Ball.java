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

    //Assuming the screen is a square
    public void moveOneStep(int screenSize) {
        //Calculate next position
        double nextX = this.center.getX() + this.velocity.getDx();
        double nextY = this.center.getY() + this.velocity.getDy();

        //Check sides
        if (nextX + this.r > screenSize) {             //Right wall
            this.center.setX(screenSize - this.r);      //Right wall fix
            this.velocity.setDx(-this.velocity.getDx());
        } else if (nextX - this.r < 0) {        //Left wall
            this.center.setX(this.r);            //Left wall fix
            this.velocity.setDx(-this.velocity.getDx());
        } else {
            this.center.setX(nextX);             //No change
        }

        //Check top/bottom
        if (nextY + this.r > screenSize) {             //Bottom wall
            this.center.setY(screenSize - this.r);      //Bottom wall fix
            this.velocity.setDy(-this.velocity.getDy());
        } else if (nextY - this.r < 0) {        //Top wall
            this.center.setY(this.r);            //Top wall fix
            this.velocity.setDy(-this.velocity.getDy());
        } else {
            this.center.setY(nextY);
        }
    }



}