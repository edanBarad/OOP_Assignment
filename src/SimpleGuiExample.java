import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class SimpleGuiExample {

    public void drawRandomCircles() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[10];
        for (int i = 0; i < lines.length; i++) {          //Draw lines
            int startX = rand.nextInt(400) + 1; // get integer in range 1-400
            int startY = rand.nextInt(300) + 1; // get integer in range 1-300
            int endX = rand.nextInt(400) + 1; // get integer in range 1-400
            int endY = rand.nextInt(300) + 1; // get integer in range 1-300
            lines[i] = new Line(startX, startY, endX, endY);
            d.setColor(Color.BLACK);
            d.drawLine((int)lines[i].start().getX(),(int) lines[i].start().getY(),(int) lines[i].end().getX(),(int) lines[i].end().getY());
        }
        Point p;
        d.setColor(Color.BLUE);
        for (int i = 0; i < lines.length; i++){
            p = lines[i].middle();
            d.fillCircle((int)p.getX(),(int)p.getY(),3);
        }
        d.setColor(Color.RED);
        for (int i = 0; i < lines.length; i++){
            for (int j = i+1; j < lines.length; j++){
                p = lines[i].intersectionWith(lines[j]);
                if (p != null){
                    d.fillCircle((int)p.getX(),(int)p.getY(),3);
                }
            }
        }
        gui.show(d);
    }

    public static void main(String[] args) {
        SimpleGuiExample example = new SimpleGuiExample();
        example.drawRandomCircles();
    }
}