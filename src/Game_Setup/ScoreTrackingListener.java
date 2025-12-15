package Game_Setup;

import Geometry.Ball;
import Geometry.Block;
import Interfaces.HitListener;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}