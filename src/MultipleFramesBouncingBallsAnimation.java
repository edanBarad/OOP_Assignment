import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {

    static private void drawBalls(Ball[] balls) {
        GUI gui = new GUI("Multi Frames Bouncing Balls Animation",600,600);
        Sleeper sleeper = new Sleeper();
        for (Ball ball: balls) {
            if (ball.getX() - ball.getSize() < 0) {
                ball.setX(ball.getSize());
            } else if (ball.getX() + ball.getSize() > 600) {
                ball.setX(600 - ball.getSize());
            }
            if (ball.getY() - ball.getSize() < 0) {
                ball.setY(ball.getSize());
            } else if (ball.getY() + ball.getSize() > 600) {
                ball.setY(600 - ball.getSize());
            }
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);

            for (Ball ball : balls) {
                ball.moveOneStep(600);
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }

    }

    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("ERROR!\nMust enter at least one size!");
            return;
        }

        Random rnd = new Random();
        Ball[] balls = new  Ball[args.length];
        for (int i = 0; i < balls.length; i++){
            int size = Integer.parseInt(args[i]);
            Point point = new Point(rnd.nextInt(200 - 2 * size) + size, rnd.nextInt(200 - 2 * size) + size);
            java.awt.Color color = Colors.random();                     //Choose random color
            double speed = (size > 50) ? 1 : ((double) 200 /size);       //Random speed

            balls[i] = new Ball(point, size, color);
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt(361), speed));
        }
        drawBalls(balls);
    }
}