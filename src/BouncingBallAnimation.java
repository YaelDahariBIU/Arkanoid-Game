import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Bouncing ball animation.
 */
public class BouncingBallAnimation {
    /**
     * The Width.
     */
    static final int WIDTH = 700;
    /**
     * The Height.
     */
    static final int HEIGHT = 700;
    /**
     * The First.
     */
    static final int FIRST = 0;
    /**
     * The Second.
     */
    static final int SECOND = 1;
    /**
     * The Third.
     */
    static final int THIRD = 2;
    /**
     * The Fourth.
     */
    static final int FOURTH = 3;
    /**
     * The Edge.
     */
    static final int EDGE = 0;
    static final int STANDARD_SIZE = 30;
    static final int SLEEP = 50;

    /**
     * Draw animation.
     *
     * @param start the start
     * @param dx    the dx
     * @param dy    the dy
     */
    static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(),
                STANDARD_SIZE, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(WIDTH, HEIGHT, EDGE, EDGE);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(SLEEP);  // wait for 50 milliseconds.
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Point p = new Point(Double.parseDouble(args[FIRST]),
                Double.parseDouble(args[SECOND]));
        double dx = Double.parseDouble(args[THIRD]);
        double dy = Double.parseDouble(args[FOURTH]);
        drawAnimation(p, dx, dy);
    }
}
