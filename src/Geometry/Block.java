package Geometry;

import Game_Setup.GameLevel;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.Sprite;
import Interfaces.HitNotifier;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    public Block(Rectangle rectangle, Color color){
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setRectangle(Rectangle rectangle){
        this.rectangle = rectangle;
    }

    public List<HitListener> getHitListeners() {
        return hitListeners;
    }
    public void setHitListeners(List<HitListener> hitListeners) {
        this.hitListeners = hitListeners;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int)this.rectangle.getUpperLeft().getX(),(int)this.rectangle.getUpperLeft().getY(),
                        (int)this.rectangle.getWidth(), (int)this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    public void removeFromGame(GameLevel gameLevel){
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        //Temp list to iterate
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify about the hit
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}