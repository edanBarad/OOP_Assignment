package Game_Setup;

import Ass3_Test_Runs.PrintingHitListener;
import Geometry.*;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Animation;
import Interfaces.LevelInformation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

// Import all your classes from their packages:
import Interfaces.Collidable;
import Interfaces.Sprite;

import java.util.List;

public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter ballCounter = new Counter(), blockCounter = new Counter(), score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = runner;
        this.score = score; //Score passes along all levels
    }

    // Your existing methods:
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    public void initialize() {
        //First parameters for each level
        PrintingHitListener printingHitListener = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        this.addSprite(this.levelInformation.getBackground());
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, this.levelInformation.levelName());
        this.addSprite(scoreIndicator);

        //Add balls
        for (Velocity velocity: this.levelInformation.initialBallVelocities()){
            Ball ball = new Ball(new Point(400, 550), 5, Colors.WHITE.getColor(), this.environment);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }

        // Create border blocks
        Block topBorder = new Block(new Rectangle(new Geometry.Point(0, 20), 800, 20), Colors.GRAY.getColor());
        topBorder.addToGame(this);
        Block leftBorder = new Block(new Rectangle(new Geometry.Point(0, 20), 20, 560), Colors.GRAY.getColor());  // height: 560 instead of 580
        leftBorder.addToGame(this);
        Block rightBorder = new Block(new Rectangle(new Geometry.Point(780, 20), 20, 560), Colors.GRAY.getColor());  // height: 560 instead of 580
        rightBorder.addToGame(this);
        Block bottomBorder = new Block(new Rectangle(new Point(0, 580), 800, 20), Colors.GRAY.getColor());
        bottomBorder.addToGame(this);
        bottomBorder.addHitListener(ballRemover);

        // Create colored blocks
        for (Block block: this.levelInformation.blocks()){
            block.addToGame(this);
            block.addHitListener(printingHitListener);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        this.blockCounter.increase(this.levelInformation.numberOfBlocksToRemove());

        // Create paddle, and place in the middle of the screen
        Paddle paddle = new Paddle(this.keyboard,
                new Rectangle(new Geometry.Point(400-((double) this.levelInformation.paddleWidth() /2), 560), this.levelInformation.paddleWidth(), 20),
                Colors.ORANGE, this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    //Game runs as long as there are blocks and balls left
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this); //Run the game
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            System.out.println("YOU WIN!");
        } else if (this.ballCounter.getValue() == 0) {
            System.out.println("YOU LOSE!");
        }
        System.out.println("Your score is: " + this.score.getValue());
        return;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        this.sprites.notifyAllTimePassed();
        this.running = this.blockCounter.getValue() > 0 && this.ballCounter.getValue() > 0;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

}