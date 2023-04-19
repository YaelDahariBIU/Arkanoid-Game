// 325166510 Yael Dahari
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;
/**
 * Drawing multiple balls that move across the screen.
 */
public class MultipleBouncingBallsAnimation {
    static final int WIDTH = 200;
    static final int HEIGHT = 200;
    static final int MAX_SIZE = 50;
    static final int MIN_SPEED = 3;
    static final int MAX_SPEED = 300;
    static final int DEGREES = 360;
    static final int EDGE = 0;
    static final int SLEEP = 50;
    static final int FIRST = 0;
    static final int MAXIMUM_SIZE = 90;
    /**
     * The method gets an array of sizes and creates an array of balls
     * according to the sizes and random values.
     *
     * @param arr (int[]) - an array with sizes
     * @return (Ball[]) - an array of balls
     */
    public static Ball[] createArr(int[] arr) {
        Ball[] newArr = new Ball[arr.length];
        Random rand = new Random();
        double x, y, angle, speed;
        int size;
        Velocity v;
        java.awt.Color color;
        for (int i = 0; i < arr.length; i++) {
            x = rand.nextDouble() * WIDTH;
            y = rand.nextDouble() * HEIGHT;
            size = arr[i];
            color = randomColor(rand);
            newArr[i] = new Ball(new Point(x, y), size, color);
            angle = rand.nextDouble() * DEGREES;
            if (size > MAX_SIZE) {
                v = Velocity.fromAngleAndSpeed(angle, MIN_SPEED);
                newArr[i].setVelocity(v);
            } else {
                speed = MAX_SPEED / (double) size;
                v = Velocity.fromAngleAndSpeed(angle, speed);
                newArr[i].setVelocity(v);
            }
        }
        return newArr;
    }

    /**
     * The method gets an array of balls and a gui. It draws them and moves
     * them across the screen.
     *
     * @param arr (Ball[]) - an array of balls
     * @param gui (GUI) - the gui
     */
    public static void drawAnimation(Ball[] arr, GUI gui) {
        Sleeper sleeper = new Sleeper();
        DrawSurface d = gui.getDrawSurface();
        gui.show(d);
        while (true) {
            for (Ball ball : arr) {
                ball.moveInFrame(WIDTH, HEIGHT, EDGE, EDGE);
            }
            d = gui.getDrawSurface();
            for (Ball ball : arr) {
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(SLEEP);  // wait for 50 milliseconds.
        }
    }

    /**
     * The method gets a variable of Random, generates 3 random floats using
     * it and returns a color defined by those numbers.
     *
     * @param rand (Random) - a variable of Random
     * @return (java.awt.color) - a random color
     */
    public static java.awt.Color randomColor(Random rand) {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    /**
     * The entry point of application. The method gets several sizes from the
     * command line and runs the drawAnimation method accordingly.
     *
     * @param args (String[]) - the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("bouncing balls", WIDTH, HEIGHT);
        int[] sizes = MultipleFramesBouncingBallsAnimation.toIntArray(args,
                FIRST, args.length, MAXIMUM_SIZE);
        Ball[] balls = createArr(sizes);
        drawAnimation(balls, gui);
    }
}
