import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The type Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The Width.
     */
    static final int WIDTH = 200;
    /**
     * The Height.
     */
    static final int HEIGHT = 200;
    /**
     * The Including.
     */
    static final int INCLUDING = 1;
    /**
     * The Max size.
     */
    static final int MAX_SIZE = 50;
    /**
     * The Min speed.
     */
    static final int MIN_SPEED = 3;
    /**
     * The Max speed.
     */
    static final int MAX_SPEED = 30;
    /**
     * The Degrees.
     */
    static final int DEGREES = 360;
    /**
     * The Ratio.
     */
    static final int RATIO = 10;
    /**
     * The Edge.
     */
    static final int EDGE = 0;
    static final int SLEEP = 50;

    /**
     * Create arr ball [ ].
     *
     * @param arr the arr
     * @return the ball [ ]
     */
    static Ball[] createArr(String[] arr) {
        Ball[] newArr = new Ball[arr.length];
        Random rand = new Random();
        double x, y, angle, speed;
        int size;
        Velocity v;
        java.awt.Color color;
        for (int i = 0; i < arr.length; i++) {
            x = rand.nextDouble(WIDTH) + INCLUDING;
            y = rand.nextDouble(HEIGHT) + INCLUDING;
            size = Integer.parseInt(arr[i]);
            color = randomColor(rand);
            newArr[i] = new Ball(new Point(x, y), size, color);
            angle = rand.nextDouble(DEGREES) + INCLUDING;
            if (size > MAX_SIZE) {
                v = Velocity.fromAngleAndSpeed(angle, MIN_SPEED);
                newArr[i].setVelocity(v);
            } else {
                speed = (MAX_SPEED * RATIO) / (double) size;
                v = Velocity.fromAngleAndSpeed(angle, speed);
                newArr[i].setVelocity(v);
            }
        }
        return newArr;
    }

    /**
     * Draw animation.
     *
     * @param arr the arr
     * @param gui the gui
     */
    static void drawAnimation(Ball[] arr, GUI gui) {
        Sleeper sleeper = new Sleeper();
        DrawSurface d = gui.getDrawSurface();
        gui.show(d);
        while (true) {
            for (Ball ball : arr) {
                ball.moveOneStep(WIDTH, HEIGHT, EDGE, EDGE);
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
     * Random color java . awt . color.
     *
     * @param rand the rand
     * @return the java . awt . color
     */
    static java.awt.Color randomColor(Random rand) {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        String[] sizes = {"25", "40", "13", "5", "55"};
        Ball[] arr = createArr(sizes);
        Ball[] arr2 = new Ball[1];
        arr2[0] = new Ball(-100, -50, 70, Color.BLUE);
        arr2[0].setVelocity(3, 3);
        Ball[] balls = MultipleFramesBouncingBallsAnimation.combineArrays(arr, arr2);
        drawAnimation(balls, gui);
    }
}
