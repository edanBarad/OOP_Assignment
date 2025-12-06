package Ass2_Test_Runs;

import Game_Setup.GameEnvironment;
import Geometry.*;
import Interfaces.Collidable;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

public class BallCollisionTest {

    public static void main(String[] args) {
        int screenSize = 400;

        GUI gui = new GUI("Arkanoid Test", screenSize, screenSize);
        Sleeper sleeper = new Sleeper();

        // Create blocks with bigger gaps
        ArrayList<Collidable> collidables = new ArrayList<>();

        int rows = 5;                // Fewer rows
        int cols = 7;                // Fewer columns
        int blockWidth = 40;         // Slightly bigger blocks
        int blockHeight = 15;        // Same height
        int spacing = 15;            // Much larger gaps
        int startX = 20;
        int startY = 40;

        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double x = startX + col * (blockWidth + spacing);
                double y = startY + row * (blockHeight + spacing);
                Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight), Colors.random());
                collidables.add(block);
            }
        }

        // Create Game_Setup.GameEnvironment
        GameEnvironment gameEnvironment = new GameEnvironment(collidables);

        // Create smaller Geometry.Ball
        Ball ball = new Ball(new Point(screenSize / 2.0, screenSize - 50), 6, Color.MAGENTA, gameEnvironment);
        ball.setVelocity(4, -4); // Geometry.Ball starts moving upward

        // Animation loop
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Clear background
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, screenSize, screenSize);

            // Draw blocks manually with colors per row
            int blockIndex = 0;
            for (int row = 0; row < rows; row++) {
                d.setColor(colors[row % colors.length]);
                for (int col = 0; col < cols; col++) {
                    Block block = (Block) collidables.get(blockIndex++);
                    d.fillRectangle((int) block.getCollisionRectangle().getUpperLeft().getX(),
                            (int) block.getCollisionRectangle().getUpperLeft().getY(),
                            (int) block.getCollisionRectangle().getWidth(),
                            (int) block.getCollisionRectangle().getHeight());
                }
            }

            // Draw ball
            ball.drawOn(d);

            // Move ball
            ball.moveOneStep(screenSize, screenSize);

            // Show on GUI
            gui.show(d);

            // Slow animation
            sleeper.sleepFor(20);
        }
    }
}
