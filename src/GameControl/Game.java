// 325166510 Yael Dahari
package GameControl;
import CollisionControl.Collidable;
import GameObjects.Ball;
import GameObjects.Block;
import GameObjects.Paddle;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import GameControl.SpriteControl.Sprite;
import GameControl.SpriteControl.SpriteCollection;
import ScoreControl.ScoreIndicator;
import ScoreControl.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;

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
    static final int EXTRA = 30;
    static final int NONE = 0;
    static final int BONUS = 100;
    static final int SINGLE = 1;
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
    private final Counter score;
    private final Counter givenBonuses;

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
        this.score = new Counter();
        this.givenBonuses = new Counter();
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
        initializeBalls();
        this.remainingBalls.increase(NUM_BALLS);
        initializeBorders();
        initializeRows();
        initializeScore();
    }
    private void initializeScore() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
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
            balls[i] = new Ball(DEFAULT_POINTS[i], DEFAULT_VALUE, Color.BLACK);
            balls[i].setVelocity(DEFAULT_VALUE, DEFAULT_VALUE);
            balls[i].setEnvironment(this.environment);
            balls[i].addToGame(this);
            this.remainingBalls.increase(1);
        }
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
        borders[SECOND] = new Block(new Rectangle(new Point(EDGE, EXTRA),
                DEPTH, HEIGHT - DEPTH - EXTRA), Color.gray);
        borders[THIRD] = new Block(new Rectangle(new Point(WIDTH - DEPTH,
                EXTRA), DEPTH, HEIGHT - DEPTH - EXTRA), Color.gray);
        borders[FOURTH] = new Block(new Rectangle(new Point(EDGE, EXTRA),
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
        ScoreTrackingListener scoreListener =
                new ScoreTrackingListener(this.score);
        Block[] blocks = new Block[MAX_BLOCKS];
        for (int j = 0; j < NUM_OF_ROWS; j++) {
            for (int i = 0; i < j + MIN_BLOCKS; i++) {
                Rectangle rect = new Rectangle(new Point(X - i * WIDTH_BLOCK,
                        Y - j * HEIGHT_BLOCK), WIDTH_BLOCK, HEIGHT_BLOCK);
                blocks[i] = new Block(rect, COLORS[j]);
                blocks[i].addHitListener(blockRemover);
                blocks[i].addHitListener(scoreListener);
                blocks[i].addToGame(this);
                blockRemover.addBlock();
                this.remainingBlocks.increase(SINGLE);
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
            checkForBonus();
            if (remainingBalls.getValue() == NONE
                    || remainingBlocks.getValue() == NONE) {
                return;
            }
        }
    }

    /**
     * The method removes a given collidable from this game.
     *
     * @param c (Collidable) - the given collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * The method removes a given sprite from this game.
     *
     * @param s (Sprite) - the given sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
        // making sure which counter to decrease
        if (s.isABall()) {
            remainingBalls.decrease(SINGLE);
        } else {
            remainingBlocks.decrease(SINGLE);
        }
    }
    private void checkForBonus() {
        int[] numColors = new int[NUM_OF_ROWS];
        for (Collidable collidable : this.environment.getObjects()) {
            if (collidable.getCollisionRectangle().getWidth() == WIDTH_BLOCK) {
                numColors[collidable.getColorIndex()] = SINGLE;
            }
        }
        int emptyRows = NUM_OF_ROWS;
        for (int numColor : numColors) {
            emptyRows = emptyRows - numColor;
        }
        int bonuses = givenBonuses.getValue();
        if (bonuses < emptyRows) {
            score.increase(BONUS * (emptyRows - bonuses));
            givenBonuses.increase(emptyRows - bonuses);
        }
    }
}