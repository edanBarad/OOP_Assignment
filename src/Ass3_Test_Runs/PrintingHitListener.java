package Ass3_Test_Runs;

import Geometry.Ball;
import Geometry.Block;
import Interfaces.HitListener;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}