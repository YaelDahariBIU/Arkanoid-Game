// 325166510 Yael Dahari
package GameControl;
import CollisionControl.Collidable;
import CollisionControl.PrintingHitListener;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Paddle;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import GameControl.SpriteControl.Sprite;
import GameControl.SpriteControl.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * GameControl.Game holds the sprites and the collidables, and is in charge of
 * the animation.
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
    static final int DEFAULT_VALUE = 7;
    static final int NUM_BALLS = 3;
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
    static final Point[] DEFAULT_POINTS = {new Point(340, 480),
            new Point(350, 480), new Point(360, 480)};
    static final Color[] COLORS = {Color.red, Color.ORANGE, Color.YELLOW,
            Color.green, Color.cyan, Color.BLUE};
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;

    /**
     * Instantiates a new GameControl.Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("game :)", WIDTH, HEIGHT);
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
    }

    /**
     * The method adds a collidable object to this game environment's
     * collection of collidables.
     *
     * @param c (CollisionDetection.Collidable) - the object we need to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The method adds a sprite object to this collection of sprites.
     *
     * @param s (GameControl.SpriteControl.Sprite) - the object we need to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The method initializes a new game: creates a GameObjects.Paddle, the
     * Blocks, two Balls and adds them to this game.
     */
    public void initialize() {
        initializePaddle();
        this.remainingBalls.increase(initializeBalls());
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
    private int initializeBalls() {
        Ball[] balls = new Ball[NUM_BALLS];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(DEFAULT_POINTS[i], DEFAULT_VALUE, Color.BLACK);
            balls[i].setVelocity(DEFAULT_VALUE, DEFAULT_VALUE);
            balls[i].setEnvironment(this.environment);
            balls[i].addToGame(this);
            this.remainingBalls.increase(1);
        }
        return balls.length;
    }
    /**
     * The method creates the borders and adds them to this game.
     */
    private void initializeBorders() {
        Block[] borders = new Block[SIZE];
        // the death-region block
        borders[FIRST] = new Block(new Rectangle(new Point(EDGE,
                HEIGHT - DEPTH), WIDTH, DEPTH), Color.gray);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        borders[FIRST].addHitListener(ballRemover);
        borders[SECOND] = new Block(new Rectangle(new Point(EDGE, DEPTH),
                DEPTH, HEIGHT - DEPTH), Color.gray);
        borders[THIRD] = new Block(new Rectangle(new Point(WIDTH - DEPTH,
                DEPTH), DEPTH, HEIGHT - DEPTH - DEPTH), Color.gray);
        borders[FOURTH] = new Block(new Rectangle(new Point(EDGE, EDGE),
                WIDTH, DEPTH), Color.gray);
        for (Block border : borders) {
            border.addToGame(this);
        }
    }

    /**
     * The method creates the rows of blocks and adds them to this game.
     */
    public void initializeRows() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        PrintingHitListener p = new PrintingHitListener();
        Block[] blocks = new Block[MAX_BLOCKS];
        for (int j = 0; j < NUM_OF_ROWS; j++) {
            for (int i = 0; i < j + MIN_BLOCKS; i++) {
                Rectangle rect = new Rectangle(new Point(X - i * WIDTH_BLOCK,
                        Y - j * HEIGHT_BLOCK), WIDTH_BLOCK, HEIGHT_BLOCK);
                blocks[i] = new Block(rect, COLORS[j]);
                blocks[i].addHitListener(blockRemover);
                blocks[i].addToGame(this);
                blockRemover.addBlock();
                this.remainingBlocks.increase(1);
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

            if (remainingBalls.getValue() == 0 || remainingBlocks.getValue() == 0) {
                return;
            }
        }
    }
    public void removeCollidable(Collidable c) {
//        List<Collidable> collidables =
//                new ArrayList<>(this.environment.getObjects());
//        for (Collidable collidable : collidables) {
//            if (c.equals(collidable)) {
//                this.environment.getObjects().remove(c);
//            }
//        }
        this.environment.removeCollidable(c);
    }
    public void removeSprite(Sprite s) {
//        List<Sprite> sprites = new ArrayList<>(this.sprites.getSprites());
//        for (Sprite sprite : sprites) {
//            if (s.equals(sprite)) {
//                this.sprites.getSprites().remove(s);
//                if (sprite.getClass() == Block.class) {
//                    this.remainingBlocks.decrease(1);
//                }
//                if (sprite.getClass() == Ball.class) {
//                    this.remainingBalls.decrease(1);
//                }
//            }
//        }
        this.sprites.removeSprite(s);
        if (s.getClass() == Block.class) {
            this.remainingBlocks.decrease(1);
        }
        if (s.getClass() == Ball.class) {
            this.remainingBalls.decrease(1);
        }
    }
}