import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

public class MultipleBouncingBallsAnimation {

    static private void drawBalls(Ball[] balls) {
        GUI gui = new GUI("Bouncing Balls Animation",200,200);
        Sleeper sleeper = new Sleeper();
        for (Ball ball: balls) {
            if (ball.getX() - ball.getSize() < 0) {
                ball.setX(ball.getSize());
            } else if (ball.getX() + ball.getSize() > 200) {
                ball.setX(200 - ball.getSize());
            }
            if (ball.getY() - ball.getSize() < 0) {
                ball.setY(ball.getSize());
            } else if (ball.getY() + ball.getSize() > 200) {
                ball.setY(200 - ball.getSize());
            }
        }
        int i = 0;
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.moveOneStep(200, 200);
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