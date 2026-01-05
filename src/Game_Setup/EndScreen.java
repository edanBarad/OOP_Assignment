package Game_Setup;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {

    private boolean win;
    private KeyboardSensor keyboard;
    private Counter score;

    public EndScreen(Counter score, boolean win, KeyboardSensor keyboard){
        this.score = score;
        this.win = win;
        this.keyboard = keyboard;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        String status = (win)?"WIN":"LOSE";
        d.drawText(10, (d.getHeight() / 2)-40, "You " + status + "!", 32);
        d.drawText(10, d.getHeight() / 2, "Score: " + this.score.getValue(), 32);
        d.drawText(10, (d.getHeight() / 2)+40, "Press space to end game", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
