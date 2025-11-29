import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Game {

    private static Game instance = null;

    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;

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

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void initialize() {
        Ball redBall = new Ball(new Point(400, 300), 5, Colors.RED.getColor());
        redBall.setVelocity(new Velocity(2, 3));
        redBall.setGameEnvironment(this.environment);
        redBall.addToGame(this);
        Ball blueBall = new Ball(new Point(300, 400), 5, Colors.BLUE.getColor());
        blueBall.setVelocity(new Velocity(2, 3));
        blueBall.setGameEnvironment(this.environment);
        blueBall.addToGame(this);
        //Set array of colors from my enum class
        Colors[] colors = Colors.values();
        // Create border blocks
        Block topBorder = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.GRAY);
        topBorder.addToGame(this);
        Block leftBorder = new Block(new Rectangle(new Point(0, 20), 20, 560), Color.GRAY);  // height: 560 instead of 580
        leftBorder.addToGame(this);
        Block rightBorder = new Block(new Rectangle(new Point(780, 20), 20, 560), Color.GRAY);  // height: 560 instead of 580
        rightBorder.addToGame(this);
        Block bottomBorder = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        bottomBorder.addToGame(this);
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
                        new Rectangle(new Point(blockXPos, currentY),
                                blockWidth, blockHeight), colors[i].getColor());
                block.addToGame(this);
            }
        }
        // Create paddle
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(),
                new Rectangle(new Point(350, 560), 100, 20),
                Colors.ORANGE, 5);
        paddle.addToGame(this);
    }

        public void run () {
            Sleeper sleeper = new Sleeper();
            int framesPerSecond = 60;
            int millisecondsPerFrame = 1000 / framesPerSecond;
            while (true) {
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
        }

    }