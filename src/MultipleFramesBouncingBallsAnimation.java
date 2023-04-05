import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The type Multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * The First.
     */
    static final int FIRST = 0;
    /**
     * The Error.
     */
    static final int ERROR = 1;
    /**
     * The Including.
     */
    static final int INCLUDING = 1;
    /**
     * The Half.
     */
    static final int HALF = 2;
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
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;
    /**
     * The Start 1.
     */
    static final int START_1 = 50;
    /**
     * The End 1.
     */
    static final int END_1 = 500;
    /**
     * The Start 2.
     */
    static final int START_2 = 450;
    /**
     * The End 2.
     */
    static final int END_2 = 600;
    /**
     * The Max size 1.
     */
    static final int MAX_SIZE_1 = 200;
    /**
     * The Max size 2.
     */
    static final int MAX_SIZE_2 = 55;
    /**
     * The Negative.
     */
    static final int NEGATIVE = 0;
    static final int SLEEP = 50;
    static final int SIDE_1 = 450;
    static final int SIDE_2 = 150;
    /**
     * Draw animation.
     *
     * @param arr   the arr
     * @param index the index
     * @param gui   the gui
     */
    static void drawAnimation(Ball[] arr, int index, GUI gui) {
        Sleeper sleeper = new Sleeper();
        DrawSurface d;
        while (true) {
            for (int i = 0; i < index; i++) {
                arr[i].moveOneStep(SIDE_1, SIDE_1, START_1, START_1);
            }
            for (int i = index; i < arr.length; i++) {
                arr[i].moveOneStep(SIDE_2, SIDE_2, START_2, START_2);
            }
            d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(START_1, START_1, SIDE_1, SIDE_1);
            for (Ball ball : arr) {
                ball.drawOn(d);
            }
            d.setColor(Color.YELLOW);
            d.fillRectangle(START_2, START_2, SIDE_2, SIDE_2);
            for (int i = index; i < arr.length; i++) {
                arr[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(SLEEP);  // wait for 50 milliseconds.
        }
    }

    /**
     * Create arr ball [ ].
     *
     * @param arr   the arr
     * @param start the start
     * @param end   the end
     * @param minX  the min x
     * @param minY  the min y
     * @param maxX  the max x
     * @param maxY  the max y
     * @return the ball [ ]
     */
    static Ball[] createArr(String[] arr, int start, int end, int minX,
                            int minY, int maxX, int maxY) {
        Ball[] newArr = new Ball[end - start];
        Random rand = new Random();
        double angle, speed;
        Point point;
        int size;
        Velocity velocity;
        java.awt.Color color;
        for (int i = 0; i < end - start; i++) {
            size = (int) Double.parseDouble(arr[start + i]);
            point = randomPoint(minX, minY, maxX, maxY);
            color = MultipleBouncingBallsAnimation.randomColor(rand);
            newArr[i] = new Ball(point, size, color);
            angle = rand.nextDouble(DEGREES) + INCLUDING;
            if (size > MAX_SIZE) {
                velocity = Velocity.fromAngleAndSpeed(angle, MIN_SPEED);
                newArr[i].setVelocity(velocity);
            } else {
                speed = (MAX_SPEED * RATIO) / (double) size;
                newArr[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            }
        }
        return newArr;
    }

    /**
     * Random point point.
     *
     * @param minX the min x
     * @param minY the min y
     * @param maxX the max x
     * @param maxY the max y
     * @return the point
     */
    static Point randomPoint(int minX, int minY, int maxX, int maxY) {
        Random rand = new Random();
        double x = rand.nextDouble(maxX) + minX;
        double y = rand.nextDouble(maxY) + minY;
        return new Point(x, y);
    }

    /**
     * Combine arrays ball [ ].
     *
     * @param balls1 the balls 1
     * @param balls2 the balls 2
     * @return the ball [ ]
     */
    static Ball[] combineArrays(Ball[] balls1, Ball[] balls2) {
        Ball[] balls = new Ball[balls1.length + balls2.length];
        int start = balls1.length;
        System.arraycopy(balls1, FIRST, balls, FIRST, start);
        System.arraycopy(balls2, FIRST, balls, start, balls2.length);
        return balls;
    }

    /**
     * Dont fit boolean.
     *
     * @param arr     the arr
     * @param start   the start
     * @param end     the end
     * @param maxSize the max size
     * @return the boolean
     */
    static boolean dontFit(String[] arr, int start, int end, int maxSize) {
        int size;
        for (int i = 0; i < end - start; i++) {
            size = (int) Double.parseDouble(arr[start + i]);
            if (size > maxSize || size < NEGATIVE) {
                return true;
            }
        }
        return false;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String[] sizes = {"40.6", "100", "50", "5", "20", "40", "35", "25",
                "13"};
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        int halfway = sizes.length / HALF;
        if (dontFit(sizes, FIRST, halfway, MAX_SIZE_1)
                || dontFit(sizes, halfway, sizes.length, MAX_SIZE_2)) {
            System.out.println("Error! size out of range!");
            System.exit(ERROR);
        }
        Ball[] arr1 = createArr(sizes, FIRST, halfway, END_1, END_1, START_1,
                START_1);
        Ball[] arr2 = createArr(sizes, halfway, sizes.length, START_2, START_2,
                END_2, END_2);
        Ball[] balls = combineArrays(arr1, arr2);
        drawAnimation(balls, halfway, gui);
    }
}