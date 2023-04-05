import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The type Abstract art drawing.
 */
public class AbstractArtDrawing {
    /**
     * The Width.
     */
    static final int WIDTH = 400;
    /**
     * The Height.
     */
    static final int HEIGHT = 300;
    /**
     * The Radius.
     */
    static final int RADIUS = 3;
    /**
     * The Size.
     */
    static final int SIZE = 10;
    /**
     * The Including.
     */
    static final int INCLUDING = 1;

    /**
     * Create.
     */
    public void create() {
        GUI gui = new GUI("Random Circles Example", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] arr = new Line[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = generateRandomLine();
            drawLine(arr[i], d);
            drawMiddle(arr[i], d);
            checkIntersection(arr, i, d);
        }
        gui.show(d);
    }

    /**
     * Draw middle.
     *
     * @param l the l
     * @param d the d
     */
    public void drawMiddle(Line l, DrawSurface d) {
        Point mid = l.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) mid.getX(), (int) mid.getY(), RADIUS);
    }

    /**
     * Draw intersection.
     *
     * @param line  the line
     * @param other the other
     * @param d     the d
     */
    public void drawIntersection(Line line, Line other, DrawSurface d) {
        Point inter = line.intersectionWith(other);
        if (inter != null) {
            d.setColor(Color.RED);
            d.fillCircle((int) inter.getX(), (int) inter.getY(), RADIUS);
        }
    }

    /**
     * Check intersection.
     *
     * @param arr     the arr
     * @param current the current
     * @param d       the d
     */
    public void checkIntersection(Line[] arr, int current, DrawSurface d) {
        for (int i = 0; i < current; i++) {
            if (arr[current].isIntersecting(arr[i])) {
                drawIntersection(arr[current], arr[i], d);
            }
        }
    }

    /**
     * Generate random line line.
     *
     * @return the line
     */
    public Line generateRandomLine() {
        Random rand = new Random();
        double start1 = rand.nextDouble(WIDTH) + INCLUDING;
        double end1 = rand.nextDouble(HEIGHT) + INCLUDING;
        double start2 = rand.nextDouble(WIDTH) + INCLUDING;
        double end2 = rand.nextDouble(HEIGHT) + INCLUDING;
        return new Line(start1, end1, start2, end2);
    }

    /**
     * Draw line.
     *
     * @param l the l
     * @param d the d
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.create();
    }
}