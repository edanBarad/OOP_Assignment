package Levels;

import Geometry.*;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.List;

public class Level_1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return List.of(Velocity.fromAngleAndSpeed(0, 3));
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        // We create a sprite that draws the black background and blue target
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // Draw black background
                d.setColor(Colors.BLACK.getColor());
                d.fillRectangle(0, 20, 800, 580);

                // Draw the circles at (400, 150)
                d.setColor(Colors.BLUE.getColor());
                d.drawCircle(400, 150, 60);
                d.drawCircle(400, 150, 90);
                d.drawCircle(400, 150, 120);

                // Draw the lines
                d.drawLine(400, 20, 400, 280); // Vertical line
                d.drawLine(270, 150, 530, 150); // Horizontal line
            }

            @Override
            public void timePassed() {
                // Background does not change
            }
        };
    }

    @Override
    public List<Block> blocks() {
        return List.of(new Block(new Rectangle(new Point(385, 135), 30, 30), Colors.RED.getColor()));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
