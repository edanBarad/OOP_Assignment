package Levels;

import Geometry.*;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class Level_2 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        // Create 10 balls with angles fanning out from -45 to 45 degrees
        for (int i = 0; i < 10; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-45 + (i * 10), 4));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        // Very wide paddle for "Wide Easy"
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // Simple white background
                d.setColor(Colors.WHITE.getColor());
                d.fillRectangle(0, 20, 800, 600);
            }

            @Override
            public void timePassed() {}
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // One row of blocks across the screen
        // Each block is 50px wide (15 blocks total to fill 750px + margins)
        Colors[] colors = {Colors.RED, Colors.RED, Colors.ORANGE,
                Colors.ORANGE, Colors.YELLOW, Colors.YELLOW,
                Colors.GREEN, Colors.GREEN, Colors.GREEN,
                Colors.BLUE, Colors.BLUE, Colors.PINK,
                Colors.PINK, Colors.CYAN, Colors.CYAN};

        for (int i = 0; i < 15; i++) {
            blocks.add(new Block(new Rectangle(new Point(25 + (i * 50), 250), 50, 25), colors[i].getColor()));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}