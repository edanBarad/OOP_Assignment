import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;


public class BallsTest1 {

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",200,200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(new Point(start.getX(), start.getY()), 30, java.awt.Color.BLACK);
        if (ball.getX() - ball.getSize() < 0){
            ball.setX(ball.getSize());
        }else if (ball.getX() + ball.getSize() > 200){
            ball.setX(200 - ball.getSize());
        }
        if (ball.getY() - ball.getSize() < 0){
            ball.setY(ball.getSize());
        }else if (ball.getY() + ball.getSize() > 200){
            ball.setY(200 - ball.getSize());
        }
        ball.setVelocity(Velocity.fromAngleAndSpeed(13, 9));
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        //GUI gui = new GUI("Balls Test 1", 400, 400);
        //DrawSurface d = gui.getDrawSurface();

        Ball b1 = new Ball(new Point(100,100),30,java.awt.Color.RED);
        Ball b2 = new Ball(new Point(100,150),10,java.awt.Color.BLUE);
        Ball b3 = new Ball(new Point(80,249),50,java.awt.Color.GREEN);

        //b1.drawOn(d);
        //b2.drawOn(d);
        //b3.drawOn(d);
        drawAnimation(new Point(15, 15), 3, 3);


        //gui.show(d);
    }
}