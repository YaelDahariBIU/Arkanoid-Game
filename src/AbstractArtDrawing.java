// 325166510 Yael Dahari
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * Creating 10 lines, drawn in black. The middle point in each line is
 * indicated in blue, while the intersection points between the lines are
 * indicated in red (the points are filled circles with a radius of 3).
 */
public class AbstractArtDrawing {
    static final int WIDTH = 400;
    static final int HEIGHT = 300;
    static final int RADIUS = 3;
    static final int NUM_OF_LINES = 10;
    /**
     * The function creates the wanted image.
     */
    public void create() {
        GUI gui = new GUI("Random Lines Drawing", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] arr = new Line[NUM_OF_LINES];
        for (int i = 0; i < NUM_OF_LINES; i++) {
            arr[i] = generateRandomLine();
            drawLine(arr[i], d);
            drawMiddle(arr[i], d);
            checkIntersection(arr, i, d);
        }
        gui.show(d);
    }

    /**
     * The function gets a line and a drawSurface. It calculates the middle
     * point of said line and draws it in blue on said drawSurface.
     *
     * @param l (Line) - the line
     * @param d (DrawSurface) - the surface we're drawing on
     */
    public void drawMiddle(Line l, DrawSurface d) {
        Point mid = l.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) mid.getX(), (int) mid.getY(), RADIUS);
    }

    /**
     * The function gets two line and a drawSurface. It calculates the
     * intersection point of the lines, and if it exists, the function draws
     * it in red on said drawSurface.
     *
     * @param line (Line) - the first line
     * @param other (Line) - the other line
     * @param d (DrawSurface) - the surface we're drawing on
     */
    public void drawIntersection(Line line, Line other, DrawSurface d) {
        Point inter = line.intersectionWith(other);
        if (inter != null) {
            d.setColor(Color.RED);
            d.fillCircle((int) inter.getX(), (int) inter.getY(), RADIUS);
        }
    }

    /**
     * The function gets an array of lines, an index and a drawSurface. It goes
     * over the array until the given index and checks if any of the lines
     * intersect with the current one and if so, calls the method to draw it.
     *
     * @param arr (LIne[]) - an array of lines
     * @param current (int) - the index of the current line
     * @param d (DrawSurface) - the surface we're drawing on
     */
    public void checkIntersection(Line[] arr, int current, DrawSurface d) {
        for (int i = 0; i < current; i++) {
            if (arr[current].isIntersecting(arr[i])) {
                drawIntersection(arr[current], arr[i], d);
            }
        }
    }

    /**
     * The function generates random values for x and y values within the
     * frame, creates a line from said values and returns it.
     *
     * @return (Line) - the generated random line
     */
    public Line generateRandomLine() {
        Random rand = new Random();
        double start1 = rand.nextDouble() * WIDTH;
        double end1 = rand.nextDouble() * HEIGHT;
        double start2 = rand.nextDouble() * WIDTH;
        double end2 = rand.nextDouble() * HEIGHT;
        return new Line(start1, end1, start2, end2);
    }

    /**
     * The function gets a line and a drawSurface, and draws said line in
     * black on said drawSurface.
     *
     * @param l (Line) - the line
     * @param d (DrawSurface) - the surface we're drawing on
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(),
                (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * The main method. Activates the other methods
     *
     * @param args (String[]) - the input arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.create();
    }
}