// 325166510 Yael Dahari
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;
/**
 * Creating two frames - one of them is a grey rectangle from (50,50) to
 * (500, 500), and the other is a yellow rectangle from (450,450) to
 * (600, 600). We want the first half of the balls to bounce inside the first
 * frame, and the second half of the balls to bounce inside the second frame.
 */
public class MultipleFramesBouncingBallsAnimation {
    static final int FIRST = 0;
    static final int HALF = 2;
    static final int MAX_SIZE = 50;
    static final int MIN_SPEED = 3;
    static final int MAX_SPEED = 300;
    static final int DEGREES = 360;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int START_1 = 50;
    static final int END_1 = 500;
    static final int START_2 = 450;
    static final int END_2 = 600;
    static final int MAX_SIZE_1 = 225;
    static final int MAX_SIZE_2 = 75;
    static final int NEGATIVE = 0;
    static final int SLEEP = 50;
    static final int SIDE_1 = 450;
    static final int SIDE_2 = 150;
    static final int DEFAULT_SIZE = 30;
    /**
     * The method gets an array of balls, an index and a gui. it creates two
     * frames - one of them is a grey rectangle from (50,50) to (500,500), and
     * the other is a yellow rectangle from (450,450) to (600,600). It makes
     * the first half of the balls to bounce inside the first frame, and the
     * second half of the balls to bounce inside the second frame.
     *
     * @param arr (Ball[]) - an array of balls
     * @param index (int) - the index that separates the array to two halves
     * @param gui (GUI) - the gui
     */
    public static void drawAnimation(Ball[] arr, int index, GUI gui) {
        Sleeper sleeper = new Sleeper();
        DrawSurface d;
        while (true) {
            for (int i = 0; i < index; i++) {
                arr[i].moveInFrame(SIDE_1, SIDE_1, START_1, START_1);
            }
            for (int i = index; i < arr.length; i++) {
                arr[i].moveInFrame(SIDE_2, SIDE_2, START_2, START_2);
            }
            d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(START_1, START_1, SIDE_1, SIDE_1);
            // drawing the first half of the balls array on the grey frame
            for (Ball ball : arr) {
                ball.drawOn(d);
            }
            d.setColor(Color.YELLOW);
            d.fillRectangle(START_2, START_2, SIDE_2, SIDE_2);
            // drawing the second half of the balls array on the yellow frame
            for (int i = index; i < arr.length; i++) {
                arr[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(SLEEP);  // wait for 50 milliseconds.
        }
    }

    /**
     * The method gets an array of sizes, start and end indexes and the
     * coordinates of a frame. It creates an array filled with balls
     * according to the sizes and random values.
     *
     * @param arr (int[]) - an array of sizes
     * @param start (int) - the start index
     * @param end (int) - the end index
     * @param minX (int) - the x value of the left down corner
     * @param minY (int) - the y value of the left down corner
     * @param maxX (int) - the x value of the right up corner
     * @param maxY (int) - the y value of the right up corner
     * @return (Ball[]) - an array of balls
     */
    public static Ball[] createArr(int[] arr, int start, int end, int minX,
                            int minY, int maxX, int maxY) {
        Ball[] newArr = new Ball[end - start];
        Random rand = new Random();
        double angle, speed;
        Point point;
        int size;
        Velocity velocity;
        java.awt.Color color;
        for (int i = 0; i < end - start; i++) {
            size = arr[start + i];
            point = randomPoint(minX, minY, maxX, maxY);
            color = MultipleBouncingBallsAnimation.randomColor(rand);
            newArr[i] = new Ball(point, size, color);
            angle = rand.nextDouble() * DEGREES;
            if (size > MAX_SIZE) {
                velocity = Velocity.fromAngleAndSpeed(angle, MIN_SPEED);
                newArr[i].setVelocity(velocity);
            } else {
                speed = MAX_SPEED / (double) size;
                newArr[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            }
        }
        return newArr;
    }

    /**
     * The method gets the coordinates of a frame and returns a random point
     * within that frame.
     *
     * @param minX (int) - the x value of the left down corner
     * @param minY (int) - the y value of the left down corner
     * @param maxX (int) - the x value of the right up corner
     * @param maxY (int) - the y value of the right up corner
     * @return (Point) - the random point within range
     */
    private static Point randomPoint(int minX, int minY, int maxX, int maxY) {
        Random rand = new Random();
        double x = minX + (rand.nextDouble() * (maxX - minX));
        double y = minY + (rand.nextDouble() * (maxY - minY));
        return new Point(x, y);
    }

    /**
     * The method gets 2 arrays of balls and returns a new combined array.
     *
     * @param balls1 (Ball[]) - the first array of balls
     * @param balls2 (Ball[]) - the second array of balls
     * @return (Ball[]) - the combined array of balls
     */
    public static Ball[] combineArrays(Ball[] balls1, Ball[] balls2) {
        Ball[] balls = new Ball[balls1.length + balls2.length];
        int start = balls1.length;
        System.arraycopy(balls1, FIRST, balls, FIRST, start);
        System.arraycopy(balls2, FIRST, balls, start, balls2.length);
        return balls;
    }

    /**
     * The method gets an array of strings (with sizes within), start and end
     * indexes and a maximum size. It goes over the array and checks if the
     * size is bigger than the maximum or negative and if so, it updates it
     * to a default size. It eventually returns the array with those sizes.
     *
     * @param arr (String[]) - an array of strings (with sizes)
     * @param start (int) - the start index
     * @param end (int) - the end index
     * @param maxSize (int) - the maximum size
     * @return (int[]) - an array of the int values of said sizes
     */
    public static int[] toIntArray(String[] arr, int start, int end,
                                 int maxSize) {
        int size;
        int[] sizes = new int[arr.length];
        for (int i = 0; i < end - start; i++) {
            size = (int) Double.parseDouble(arr[start + i]);
            if (size > maxSize || size < NEGATIVE) {
                sizes[i] = DEFAULT_SIZE;
            } else {
                sizes[i] = size;
            }
        }
        return sizes;
    }

    /**
     * The entry point of application. The method gets several sizes from the
     * command line, checks the input validation, creates an array of balls
     * and runs the drawAnimation method accordingly.
     *
     * @param args (String[]) - the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("two frames bouncing balls", WIDTH, HEIGHT);
        int halfway = args.length / HALF;
        int[] sizes1 = toIntArray(args, FIRST, halfway, MAX_SIZE_1);
        int[] sizes2 = toIntArray(args, halfway, args.length, MAX_SIZE_2);
        Ball[] arr1 = createArr(sizes1, FIRST, halfway, END_1, END_1, START_1,
                START_1);
        Ball[] arr2 = createArr(sizes2, halfway, args.length, START_2, START_2,
                END_2, END_2);
        Ball[] balls = combineArrays(arr1, arr2);
        drawAnimation(balls, halfway, gui);
    }
}