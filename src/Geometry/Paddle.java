package Geometry;

import Game_Setup.Game;
import Interfaces.Collidable;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Colors color;
    private int speed;

    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, Colors color, int speed){
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
        this.speed = speed;
    }

    public void moveLeft(){
        int nextX = (this.rectangle.getUpperLeft().getX() - speed > 20)? (int) (this.rectangle.getUpperLeft().getX() - speed) : 20;
        this.rectangle.getUpperLeft().setX(nextX);
    }
    public void moveRight(){
        int nextX = (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + speed < 780)? (int) (this.rectangle.getUpperLeft().getX() + speed) : (int) (780 - this.rectangle.getWidth());
        this.rectangle.getUpperLeft().setX(nextX);
    }

    // Interfaces.Sprite
    public void timePassed(){
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    public void drawOn(DrawSurface d){
        d.setColor(this.color.getColor());
        d.fillRectangle((int)this.rectangle.getUpperLeft().getX(), (int)this.rectangle.getUpperLeft().getY(), (int)this.rectangle.getWidth(), (int)this.rectangle.getHeight());
    }

    // Interfaces.Collidable
    public Rectangle getCollisionRectangle(){
        return this.rectangle;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity){
        // Calculate current speed to preserve it
        double speed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx() +
                currentVelocity.getDy() * currentVelocity.getDy());
        // Calculate region width and determine which region was hit
        double regionWidth = this.rectangle.getWidth() / 5;
        double leftX = this.rectangle.getUpperLeft().getX();
        double hitX = collisionPoint.getX();
        int region = (int)((hitX - leftX) / regionWidth) + 1;
        if (region < 1) region = 1;
        if (region > 5) region = 5;
        //Return velocity based on region
        switch(region){
            case 1:
                return Velocity.fromAngleAndSpeed(300, speed);
            case 2:
                return Velocity.fromAngleAndSpeed(330, speed);
            case 3:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 4:
                return Velocity.fromAngleAndSpeed(30, speed);
            case 5:
                return Velocity.fromAngleAndSpeed(60, speed);
            default:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }

    // Add this paddle to the game.
    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}