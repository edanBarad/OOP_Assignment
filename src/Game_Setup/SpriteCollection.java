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

    public void removeSprite(Sprite s){
        this.sprites.remove(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        //We'll make a copy because the list is being modified in-game
        List<Sprite> spritesTemp = new ArrayList<>(this.sprites);
        for (Sprite sprite: spritesTemp){
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