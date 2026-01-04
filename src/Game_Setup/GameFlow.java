package Game_Setup;

import Interfaces.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private final Counter score;

    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,this.score);
            level.initialize();
            level.run();

            if (level.getNumOfBalls() == 0) {
                this.animationRunner.run(new EndScreen(this.score, false, this.keyboardSensor));
                return;
            }
        }
        this.animationRunner.run(new EndScreen(this.score, true, this.keyboardSensor));
    }
}