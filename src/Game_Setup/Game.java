package Game_Setup;

import Ass3_Test_Runs.PrintingHitListener;
import Geometry.*;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.*;

// Import all your classes from their packages:
import Interfaces.Collidable;
import Interfaces.Sprite;

public class Game {

    private static Game instance = null;

    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter ballCounter = new Counter(), blockCounter = new Counter();


    private Game() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Your existing methods:
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void removeCollidable(Collidable c){
        this.environment.removeCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }

    public void initialize() {
        PrintingHitListener printingHitListener = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);

        Ball redBall = new Ball(new Geometry.Point(400, 300), 5, Colors.RED.getColor());
        redBall.setVelocity(new Velocity(2, 3));
        redBall.setGameEnvironment(this.environment);
        redBall.addToGame(this);
        this.ballCounter.increase(1);
        Ball blueBall = new Ball(new Geometry.Point(300, 400), 5, Colors.BLUE.getColor());
        blueBall.setVelocity(new Velocity(2, 3));
        blueBall.setGameEnvironment(this.environment);
        blueBall.addToGame(this);
        this.ballCounter.increase(1);
        Ball greenBall = new Ball(new Geometry.Point(350, 400), 5, Colors.GREEN.getColor());
        greenBall.setVelocity(new Velocity(2, 3));
        greenBall.setGameEnvironment(this.environment);
        greenBall.addToGame(this);
        this.ballCounter.increase(1);

        //Set array of colors from my enum class
        Colors[] colors = Colors.values();

        // Create border blocks
        Block topBorder = new Block(new Rectangle(new Geometry.Point(0, 20), 800, 20), Color.GRAY);
        topBorder.addToGame(this);
        Block leftBorder = new Block(new Rectangle(new Geometry.Point(0, 20), 20, 560), Color.GRAY);  // height: 560 instead of 580
        leftBorder.addToGame(this);
        Block rightBorder = new Block(new Rectangle(new Geometry.Point(780, 20), 20, 560), Color.GRAY);  // height: 560 instead of 580
        rightBorder.addToGame(this);
        Block bottomBorder = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        bottomBorder.addToGame(this);
        bottomBorder.addHitListener(ballRemover);

        // Create colored blocks
        int startX = 250;
        int startY = 100;
        int blockWidth = 50;
        int blockHeight = 20;
        int numRows = 6;
        for (int i = 0; i < numRows; i++) {
            int currentY = startY + (i * blockHeight);
            int currentX = startX + (i * (blockWidth / 2));
            int numBlocksInRow = 10 - i;

            for (int j = 0; j < numBlocksInRow; j++) {
                int blockXPos = currentX + j * blockWidth;
                Block block = new Block(
                        new Rectangle(new Geometry.Point(blockXPos, currentY),
                                blockWidth, blockHeight), colors[i].getColor());
                block.addToGame(this);
                this.blockCounter.increase(1);
                block.addHitListener(printingHitListener);
                block.addHitListener(blockRemover);
            }
        }
        // Create paddle
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(),
                new Rectangle(new Geometry.Point(350, 560), 100, 20),
                Colors.ORANGE, 5);
        paddle.addToGame(this);

    }

    //Game runs as long as there are blocks and balls left
    public void run () {
            Sleeper sleeper = new Sleeper();
            int framesPerSecond = 60;
            int millisecondsPerFrame = 1000 / framesPerSecond;
            while (this.blockCounter.getValue() > 0 && this.ballCounter.getValue() > 0) {
                long startTime = System.currentTimeMillis();
                DrawSurface d = this.gui.getDrawSurface();
                this.sprites.drawAllOn(d);
                this.gui.show(d);
                this.sprites.notifyAllTimePassed();
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
            this.gui.close();
            return;
        }

    }