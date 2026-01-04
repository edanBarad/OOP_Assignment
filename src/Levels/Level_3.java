package Levels;

import Geometry.*;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

public class Level_3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2; // Level 3 has two balls
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        // Two balls fanning out in opposite directions
        velocities.add(Velocity.fromAngleAndSpeed(40, 4));
        velocities.add(Velocity.fromAngleAndSpeed(-40, 4));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // Set the background to green
                d.setColor(new java.awt.Color(42, 130, 21));
                d.fillRectangle(0, 20, 800, 600);

                // Drawing the building/tower in the background
                d.setColor(java.awt.Color.BLACK);
                d.fillRectangle(50, 450, 100, 150); // Building base

                d.setColor(java.awt.Color.WHITE);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(60 + (j * 18), 460 + (i * 28), 10, 20); // Windows
                    }
                }
            }

            @Override
            public void timePassed() {}
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // Colors for each row from top to bottom
        Colors[] colors = {Colors.GRAY, Colors.RED, Colors.YELLOW, Colors.BLUE, Colors.WHITE};

        // Creating the block structure: 5 rows starting with 10 blocks, decreasing
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                // Blocks are 50x20. They are aligned to the right edge of the screen
                int blockWidth = 50;
                int blockHeight = 20;
                // 775 is the right margin before the border
                Point topLeft = new Point(775 - (blockWidth * (j + 1)), 150 + (i * blockHeight));
                blocks.add(new Block(new Rectangle(topLeft, blockWidth, blockHeight), colors[i].getColor()));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        // Total blocks: 10 + 9 + 8 + 7 + 6 = 40
        return 40;
    }
}