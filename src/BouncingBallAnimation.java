// 325166510 Yael Dahari
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * Drawing a ball that moves across the screen.
 */
public class BouncingBallAnimation {
    static final int WIDTH = 200;
    static final int HEIGHT = 200;
    static final int FIRST = 0;
    static final int SECOND = 1;
    static final int THIRD = 2;
    static final int FOURTH = 3;
    static final int EDGE = 0;
    static final int STANDARD_SIZE = 30;
    static final int SLEEP = 50;

    /**
     * The method gets a point and two numbers, creates a ball with those
     * attributes and moves it across the screen.
     *
     * @param start (Point) - the starting location of the ball
     * @param dx (double) - the dx value
     * @param dy (double) - the dy value
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("bouncing ball animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(),
                STANDARD_SIZE, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveInFrame(WIDTH, HEIGHT, EDGE, EDGE);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(SLEEP);  // wait for 50 milliseconds.
        }
    }

    /**
     * The entry point of application. The method gets 4 integers from the
     * command line and runs the drawAnimation method accordingly
     *
     * @param args (String[]) - the input arguments
     */
    public static void main(String[] args) {
        Point p = new Point(Double.parseDouble(args[FIRST]),
                Double.parseDouble(args[SECOND]));
        double dx = Double.parseDouble(args[THIRD]);
        double dy = Double.parseDouble(args[FOURTH]);
        drawAnimation(p, dx, dy);
    }
}
