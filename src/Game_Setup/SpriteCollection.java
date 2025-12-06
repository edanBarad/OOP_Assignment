package Game_Setup;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> sprites;

    public SpriteCollection(){
        this.sprites = new ArrayList<>();
    }
    public SpriteCollection(List<Sprite> sprites){
        this.sprites = sprites;
    }

    public void addSprite(Sprite s){
        this.sprites.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        for (Sprite sprite: sprites){
            sprite.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (Sprite sprite: sprites){
            sprite.drawOn(d);
        }
    }

    //Accessors
    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }
    public List<Sprite> getSprites() {
        return sprites;
    }
}