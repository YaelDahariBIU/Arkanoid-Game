import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

public class Main {
    static final double DEGS_TO_RADS = Math.PI / 180;
    public void test() {
        Point p1 = new Point(11,11);
        Point p2 = new Point(87,87);
        Point p3 = new Point(1,1);
        Point p4 = new Point(11,11);
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p3,p4);
        Point inter = line1.intersectionWith(line2);
        if (inter == null) {
            System.out.println("null");
        } else {
            System.out.println("coordinates: " + inter.getX() + "and" + inter.getY());
        }
    }
        static private void drawAnimation(Point start, double dx, double dy) {
            GUI gui = new GUI("title", 500, 500);
            Sleeper sleeper = new Sleeper();
            Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, java.awt.Color.BLACK);
            Velocity v = Velocity.fromAngleAndSpeed(Math.toRadians(dx), dy);
            ball.setVelocity(v);
            while (true) {
                ball.moveOneStep(700, 700, 0, 0);
                DrawSurface d = gui.getDrawSurface();
                ball.drawOn(d);
                gui.show(d);
                sleeper.sleepFor(50);  // wait for 50 milliseconds.
            }
        }

    public static void main(String[] args) {
        Point start = new Point(150, 30);
        drawAnimation(start, 0, 10);
    }
}