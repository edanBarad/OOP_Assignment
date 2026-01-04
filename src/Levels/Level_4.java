package Levels;

import Geometry.*;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class Level_4 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3; // Three balls for the final challenge
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        // Three balls fanning out: one left, one straight, one right
        velocities.add(Velocity.fromAngleAndSpeed(-30, 4));
        velocities.add(Velocity.fromAngleAndSpeed(0, 4));
        velocities.add(Velocity.fromAngleAndSpeed(30, 4));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // Light blue sky background
                d.setColor(Colors.BLUE.getColor());
                d.fillRectangle(0, 20, 800, 600);

                // You can add cloud or rain visuals here
            }

            @Override
            public void timePassed() {}
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // Seven rows of blocks, each a different color
        Colors[] colors = {
                Colors.GRAY, Colors.RED, Colors.YELLOW,
                Colors.GREEN, Colors.WHITE, Colors.PINK, Colors.CYAN
        };

        // 7 rows of 15 blocks each to fill the screen width
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                // Blocks are 50x20. 25px margin on the left
                Point topLeft = new Point(25 + (j * 50), 100 + (i * 20));
                blocks.add(new Block(new Rectangle(topLeft, 50, 20), colors[i].getColor()));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        // 7 rows * 15 blocks = 105 blocks total
        return 105;
    }
}