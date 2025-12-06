package Game_Setup;

import Geometry.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import Interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {

    private List<Collidable> collidables;

    public GameEnvironment(){
        this.collidables = new ArrayList<>();
    }
    public GameEnvironment(List<Collidable> collidables){
        this.collidables = collidables;
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c){
        this.collidables.add(c);
    }

    public void removeCollidable(Collidable c){
        this.collidables.remove(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory){
        Point collisionPoint = null;
        Collidable collisionObject = null;
        for (Collidable collidable: collidables){
            Point intersect = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (intersect != null){
                if (collisionPoint == null){
                    collisionPoint = intersect;
                    collisionObject = collidable;
                }else{
                    if (trajectory.start().distance(intersect) < trajectory.start().distance(collisionPoint)){
                        collisionPoint = intersect;
                        collisionObject = collidable;
                    }
                }
            }
        }
        return (collisionPoint != null) ? new CollisionInfo(collisionPoint, collisionObject): null;
    }

}