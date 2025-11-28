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
        int nextX = (this.rectangle.getUpperLeft().getX() - speed > 0)? (int) (this.rectangle.getUpperLeft().getX() - speed) : 0;
        this.rectangle.getUpperLeft().setX(nextX);
    }
    public void moveRight(){
        int nextX = (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + speed < 800)? (int) (this.rectangle.getUpperLeft().getX() + speed) : (int) (800 - this.rectangle.getWidth());
        this.rectangle.getUpperLeft().setX(nextX);
    }

    // Sprite
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

    // Collidable
    public Rectangle getCollisionRectangle(){
        return this.rectangle;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity){
        return null;
    }

    // Add this paddle to the game.
    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}