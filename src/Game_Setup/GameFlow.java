package Game_Setup;

import Interfaces.LevelInformation;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;

    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,this.score);
            level.initialize();
            level.run();

            if (level.getNumOfBalls() == 0) {
                win = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, new EndScreen(this.score, win, this.keyboardSensor)));
    }
}