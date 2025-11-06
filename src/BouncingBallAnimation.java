import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class BouncingBallAnimation {

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation",200,200);
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
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(200);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        if (args.length != 4){
            System.out.println("ERROR!\nMust insert 4 numbers!");
            return;
        }
        else{
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            double dx = Double.parseDouble(args[2]);
            double dy = Double.parseDouble(args[3]);
            drawAnimation(new Point(x, y), dx, dy);
        }
    }
}
