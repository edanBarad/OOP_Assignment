package Geometry;
import Game_Setup.Game;
import Game_Setup.GameEnvironment;
import Interfaces.Sprite;
import biuoop.DrawSurface;

public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    //Constructor
    public Ball(Point center, int r, java.awt.Color color){
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(3, 3);
    }

    //New constructor with gameEnvironment(Assignment 2)
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment){
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(3, 3);
        this.gameEnvironment = gameEnvironment;
    }

    //Accessors
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
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    public void setX(int x){
        this.center.setX(x);
    }
    public void setY(int y){
        this.center.setY(y);
    }
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
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

    @Override
    //Draw the ball on the given DrawSurface
    public void drawOn(DrawSurface d){
        d.setColor(this.color);
        d.fillCircle(this.getX(),this.getY(),this.r);
    }

    @Override
    public void timePassed() {
        moveOneStep(800, 600); // temporary until GameLevel provides the frame
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }

    public void moveOneStep(int width, int height) {
        //Calculate next position
        double nextX = this.center.getX() + this.velocity.getDx();
        double nextY = this.center.getY() + this.velocity.getDy();
        //Check sides
        if (nextX + this.r > width) {             //Right wall
            this.center.setX(width - this.r);     //Right wall fix
            this.velocity.setDx(-this.velocity.getDx());
        } else if (nextX - this.r < 0) {               //Left wall
            this.center.setX(this.r);                  //Left wall fix
            this.velocity.setDx(-this.velocity.getDx());
        } else {
            this.center.setX(nextX);                   //No change
        }
        //Check top/bottom
        if (nextY + this.r > height) {             //Bottom wall
            this.center.setY(height - this.r);     //Bottom wall fix
            this.velocity.setDy(-this.velocity.getDy());
        } else if (nextY - this.r < 0) {               //Top wall
            this.center.setY(this.r);                  //Top wall fix
            this.velocity.setDy(-this.velocity.getDy());
        } else {
            this.center.setY(nextY);                   //No change
        }
        //Check for collisions (edge-aware)
        //Build a line from previous center to next edge position
        double edgeNextX = this.center.getX() + Math.signum(this.velocity.getDx()) * this.r;
        double edgeNextY = this.center.getY() + Math.signum(this.velocity.getDy()) * this.r;
        Line path = new Line(
                new Point(nextX - this.velocity.getDx(), nextY - this.velocity.getDy()),  // previous center
                new Point(edgeNextX, edgeNextY)                                                 // next edge
        );
        CollisionInfo info = this.gameEnvironment.getClosestCollision(path);
        if (info != null) {  //Collision found
            this.velocity = info.collisionObject().hit(info.collisionPoint(), this.velocity);
        }
    }

    public void startBallInFrame(Line diagonal){
        //Check sides
        if (this.getX() + this.getSize() > diagonal.end().getX()) {             //Right wall
            this.setX((int)(diagonal.end().getX() - this.r));      //Right wall fix
        } else if (this.getX() - this.getSize() < diagonal.start().getX()) {        //Left wall
            this.setX((int)(diagonal.start().getX() + this.r));            //Left wall fix
        }

        //Check top/bottom
        if (this.getY() + this.getSize() > diagonal.end().getY()) {             //Bottom wall
            this.setY(((int)diagonal.end().getY() - this.r));      //Bottom wall fix
        } else if (this.getY() - this.getSize() < diagonal.start().getY()) {        //Top wall
            this.setY(((int)diagonal.start().getY() + this.r));            //Top wall fix
        }
    }




    //Assuming the screen is a square(Assignment 1)
    public void moveOneStepInFrame(Line diagonal) {
        //Calculate next position
        double nextX = this.center.getX() + this.velocity.getDx();
        double nextY = this.center.getY() + this.velocity.getDy();

        //Check sides
        if (nextX + this.r > diagonal.end().getX()) {             //Right wall
            this.center.setX(diagonal.end().getX() - this.r);      //Right wall fix
            this.velocity.setDx(-this.velocity.getDx());
        } else if (nextX - this.r < diagonal.start().getX()) {        //Left wall
            this.center.setX(diagonal.start().getX() + this.r);            //Left wall fix
            this.velocity.setDx(-this.velocity.getDx());
        } else {
            this.center.setX(nextX);             //No change
        }

        //Check top/bottom
        if (nextY + this.r > diagonal.end().getY()) {             //Bottom wall
            this.center.setY(diagonal.end().getY() - this.r);      //Bottom wall fix
            this.velocity.setDy(-this.velocity.getDy());
        } else if (nextY - this.r < diagonal.start().getY()) {        //Top wall
            this.center.setY(diagonal.start().getY() + this.r);            //Top wall fix
            this.velocity.setDy(-this.velocity.getDy());
        } else {
            this.center.setY(nextY);
        }
    }
}