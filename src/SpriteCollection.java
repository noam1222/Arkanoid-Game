// 209407162 Noam Maimon

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * class of collection of sprites objects.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;

    /**
     * constructor - initialize this object.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * constructor - set this sprite to received sprites list.
     * @param sprites sprites list to set to these sprites.
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * add sprite object to these sprites.
     * @param s sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() function on all these sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : this.sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn() function on all sprites.
     * @param d the surface to draw all these sprites objects in.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}