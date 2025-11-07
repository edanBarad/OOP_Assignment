import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {

    static private void drawBalls(Ball[] balls) {
        GUI gui = new GUI("Multi Frames Bouncing Balls Animation",600,600);
        Sleeper sleeper = new Sleeper();
        Line r1 = new Line(50 , 50 , 500, 500);
        Line r2 = new Line(450 , 450 , 600, 600);
        int r1Size = (int)(r1.end().getX() - r1.start().getX());
        int r2Size = (int)(r2.end().getX() - r2.start().getX());
        //First we'll place all balls in thier frames

        for (int i = 0; i < balls.length; i++){
            if (i < balls.length/2){
                balls[i].startBallInFrame(r1);
            }else {
                balls[i].startBallInFrame(r2);
            }
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            d.setColor(Color.GRAY);
            d.fillRectangle((int)r1.start().getX(), (int)r1.start().getY(), r1Size, r1Size);
            d.setColor(Color.YELLOW);
            d.fillRectangle((int)r2.start().getX(), (int)r2.start().getY(), r2Size, r2Size);

            for (int i = 0; i < balls.length; i++){
                if (i < balls.length/2){
                    balls[i].moveOneStepInFrame(r1);
                }else {
                    balls[i].moveOneStepInFrame(r2);
                }
                balls[i].drawOn(d);
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