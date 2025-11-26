import biuoop.DrawSurface;

import java.util.List;

public class Block implements Collidable, Sprite{
    private Rectangle rectangle;

    public Block(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Line[] walls = this.rectangle.getWalls();
        for (Line line: walls) {
            if (line.isOnLine(collisionPoint)) {
                if (line.getSlope() == 0) {
                    dy = -dy;
                } else if (line.getSlope() == Double.POSITIVE_INFINITY) {
                    dx = -dx;
                }
            }
        }
        return new Velocity(dx, dy);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setRectangle(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.fillRectangle((int)this.rectangle.getUpperLeft().getX(),(int)this.rectangle.getUpperLeft().getY(),
                        (int)this.rectangle.getWidth(), (int)this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }
}