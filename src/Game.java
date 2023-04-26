// 325166510 Yael Dahari
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * Game holds the sprites and the collidables, and is in charge of the
 * animation.
 */
public class Game {
    static final int FIRST = 0;
    static final int SECOND = 1;
    static final int THIRD = 2;
    static final int FOURTH = 3;
    static final int SIZE = 4;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int DEPTH = 10;
    static final int DEFAULT_VALUE = 10;
    static final int NUM_BALLS = 2;
    static final int EDGE = 0;
    static final int MAX_BLOCKS = 12;
    static final int MIN_BLOCKS = 7;
    static final int NUM_OF_ROWS = 6;
    static final int WIDTH_BLOCK = 50;
    static final int HEIGHT_BLOCK = 20;
    static final int Y = 170;
    static final int X = 740;
    static final int FPS = 60;
    static final int MSPF = 1000;
    static final int POSITIVE = 0;
    static final Point DEFAULT_POINT = new Point(400, 400);
    static final Color[] COLORS = {Color.blue, Color.cyan, Color.green,
            Color.YELLOW, Color.ORANGE, Color.red};
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("game :)", WIDTH, HEIGHT);
        this.keyboardSensor = this.gui.getKeyboardSensor();
    }

    /**
     * The method adds a collidable object to this game environment's
     * collection of collidables.
     *
     * @param c (Collidable) - the object we need to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The method adds a sprite object to this collection of sprites.
     *
     * @param s (Sprite) - the object we need to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The method initializes a new game: create the Blocks, Balls and
     * Paddle, and add them to this game.
     */
    public void initialize() {
        initializePaddle();
        initializeBalls();
        initializeBorders();
        initializeRows();
    }

    /**
     * The method creates the paddle and adds it to this game.
     */
    private void initializePaddle() {
        Paddle paddle = new Paddle(this.keyboardSensor);
        paddle.addToGame(this);
    }
    /**
     * The method creates the balls and adds them to this game.
     */
    private void initializeBalls() {
        Ball[] balls = new Ball[NUM_BALLS];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(DEFAULT_POINT, DEFAULT_VALUE, Color.BLACK);
            balls[i].setVelocity(DEFAULT_VALUE, DEFAULT_VALUE);
            balls[i].setEnvironment(this.environment);
            balls[i].addToGame(this);
        }
    }
    /**
     * The method creates the borders and adds them to this game.
     */
    private void initializeBorders() {
        Block[] borders = new Block[SIZE];
        borders[FIRST] = new Block(new Rectangle(new Point(EDGE, EDGE),
                WIDTH, DEPTH), Color.gray);
        borders[SECOND] = new Block(new Rectangle(new Point(EDGE, DEPTH),
                DEPTH, HEIGHT), Color.gray);
        borders[THIRD] = new Block(new Rectangle(new Point(WIDTH - DEPTH,
                DEPTH), DEPTH, HEIGHT - DEPTH), Color.gray);
        borders[FOURTH] = new Block(new Rectangle(new Point(DEPTH,
                HEIGHT - DEPTH), WIDTH - DEPTH - DEPTH, DEPTH),
                Color.gray);
        for (Block border : borders) {
            border.addToGame(this);
        }
    }

    /**
     * The method creates the rows of blocks and adds them to this game.
     */
    public void initializeRows() {
        Block[] blocks = new Block[MAX_BLOCKS];
        for (int j = 0; j < NUM_OF_ROWS; j++) {
            for (int i = 0; i < j + MIN_BLOCKS; i++) {
                Rectangle rect = new Rectangle(new Point(X - i * WIDTH_BLOCK,
                        Y - j * HEIGHT_BLOCK), WIDTH_BLOCK, HEIGHT_BLOCK);
                blocks[i] = new Block(rect, COLORS[j]);
                blocks[i].addToGame(this);
            }
        }
    }

    /**
     * The method runs this game by starting the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = MSPF / FPS;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > POSITIVE) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}