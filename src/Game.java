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
        Ball ball = new Ball(new Point(400, 300), 5, Colors.RED.getColor());
        ball.setVelocity(new Velocity(2, 3));
        ball.setGameEnvironment(this.environment);
        ball.addToGame(this);

        //Set array of colors from my enum class
        Colors[] colors = Colors.values();

        int startX = 250; // Starting X position for the first row
        int startY = 100; // Starting Y position for the top row
        int blockWidth = 50;
        int blockHeight = 20;
        int numRows = 6;
        int rowGap = 5;    // Gap between rows
        int columnGap = 5; // Gap between blocks in a row

        for (int i = 0; i < numRows; i++) {
            // Calculate Y position: startY + (row index * (height + gap))
            int currentY = startY + (i * (blockHeight + rowGap));

            // Each row starts slightly later than the one above it
            int currentX = startX + (i * (blockWidth / 2));

            // Number of blocks decreases by one for each subsequent row
            int numBlocksInRow = 10 - i;

            for (int j = 0; j < numBlocksInRow; j++) {
                // Calculate X position: currentX + (block index * (width + gap))
                int blockXPos = currentX + j * (blockWidth + columnGap); // <-- MODIFIED LINE

                Block block = new Block(
                        new Rectangle(new Point(blockXPos, currentY), // <-- Use the new position
                                blockWidth, blockHeight), colors[i].getColor());
                block.addToGame(this);
            }
        }

        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), new Rectangle(new Point(0, 475), blockWidth, blockHeight), Colors.ORANGE, 3);
        paddle.addToGame(this);
    }

    public void run() {
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