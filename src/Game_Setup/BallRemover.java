package Game_Setup;

import Geometry.Ball;
import Geometry.Block;
import Interfaces.HitListener;

public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}