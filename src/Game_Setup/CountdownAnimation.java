package Game_Setup;

import Geometry.Colors;
import Interfaces.Animation;
import biuoop.DrawSurface;
import java.awt.Color;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long startTime;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
    }

    public void doOneFrame(DrawSurface d) {
        //Draw the game background and sprites first
        this.gameScreen.drawAllOn(d);

        //Calculate timing logic
        long elapsed = System.currentTimeMillis() - this.startTime;
        double milliPerCount = (this.numOfSeconds * 1000) / this.countFrom;

        //Determine the current number to show
        int currentCount = this.countFrom - (int) (elapsed / milliPerCount);

        //Draw the number if it's within the countdown range
        if (currentCount > 0) {
            d.setColor(Colors.BLACK.getColor());
            // Drawing in the center of an 800x600 screen
            d.drawText(380, 300, Integer.toString(currentCount), 80);
        }
    }

    public boolean shouldStop() {
        // Stop when the total time has passed
        return System.currentTimeMillis() - this.startTime > (long) (1000 * this.numOfSeconds);
    }
}