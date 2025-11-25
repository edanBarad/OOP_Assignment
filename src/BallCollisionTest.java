import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

public class BallCollisionTest {

    public static void main(String[] args) {
        int screenSize = 400;

        GUI gui = new GUI("Ball Collision Test", screenSize, screenSize);
        Sleeper sleeper = new Sleeper();

        // Create blocks
        ArrayList<Collidable> collidables = new ArrayList<>();
        Block topBlock = new Block(new Rectangle(new Point(50, 50), 300, 20));
        Block leftBlock = new Block(new Rectangle(new Point(50, 50), 20, 300));
        Block rightBlock = new Block(new Rectangle(new Point(330, 50), 20, 300));
        Block bottomBlock = new Block(new Rectangle(new Point(50, 330), 300, 20));

        collidables.add(topBlock);
        collidables.add(leftBlock);
        collidables.add(rightBlock);
        collidables.add(bottomBlock);

        // Create GameEnvironment
        GameEnvironment gameEnvironment = new GameEnvironment(collidables);

        // Create Ball
        Ball ball = new Ball(new Point(200, 200), 10, Color.MAGENTA, gameEnvironment);
        ball.setVelocity(4, 3);

        // Animation loop
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Clear background
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, screenSize, screenSize);

            // Draw blocks manually
            d.setColor(Color.BLUE);
            d.fillRectangle((int)topBlock.getCollisionRectangle().getUpperLeft().getX(),
                    (int)topBlock.getCollisionRectangle().getUpperLeft().getY(),
                    (int)topBlock.getCollisionRectangle().getWidth(),
                    (int)topBlock.getCollisionRectangle().getHeight());

            d.setColor(Color.RED);
            d.fillRectangle((int)leftBlock.getCollisionRectangle().getUpperLeft().getX(),
                    (int)leftBlock.getCollisionRectangle().getUpperLeft().getY(),
                    (int)leftBlock.getCollisionRectangle().getWidth(),
                    (int)leftBlock.getCollisionRectangle().getHeight());

            d.setColor(Color.GREEN);
            d.fillRectangle((int)rightBlock.getCollisionRectangle().getUpperLeft().getX(),
                    (int)rightBlock.getCollisionRectangle().getUpperLeft().getY(),
                    (int)rightBlock.getCollisionRectangle().getWidth(),
                    (int)rightBlock.getCollisionRectangle().getHeight());

            d.setColor(Color.ORANGE);
            d.fillRectangle((int)bottomBlock.getCollisionRectangle().getUpperLeft().getX(),
                    (int)bottomBlock.getCollisionRectangle().getUpperLeft().getY(),
                    (int)bottomBlock.getCollisionRectangle().getWidth(),
                    (int)bottomBlock.getCollisionRectangle().getHeight());

            // Draw ball
            ball.drawOn(d);

            // Move ball
            ball.moveOneStep(screenSize);

            // Show on GUI
            gui.show(d);

            // Sleep to slow animation
            sleeper.sleepFor(20);
        }
    }
}