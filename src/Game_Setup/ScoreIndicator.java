package Game_Setup;

import Geometry.Colors;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Sprite;
import biuoop.DrawSurface;

public class ScoreIndicator implements Sprite {
    private final Rectangle rectangle;
    private String text;
    private Counter score;

    public ScoreIndicator(Counter score){
        this.score = score;
        this.rectangle = new Rectangle(new Point(0, 0), 800, 20);
        this.text = "Score: 0";
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Colors.LIGHT_GRAY.getColor());
        d.fillRectangle((int)this.rectangle.getUpperLeft().getX(), (int)this.rectangle.getUpperLeft().getY(),
                        (int)this.rectangle.getWidth(), (int)this.rectangle.getHeight());
        d.setColor(Colors.BLACK.getColor());
        d.drawText(350, 16, this.text, 18);
    }

    @Override
    public void timePassed() {
        this.text = "Score: " + this.score.getValue();
    }
}
