// 325166510 Yael Dahari
package GameControl;
import Animation.Animation;
import Animation.AnimationRunner;
import CollisionControl.Collidable;
import GameControl.LevelControl.LevelInformation;
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
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.List;

import Animation.PauseScreen;
import Animation.CountdownAnimation;

/**
 * GameControl.Game holds the sprites and the collidables, and is in charge of
 * the animation.
 */
public class GameLevel implements Animation {
    static final int FIRST = 0;
    static final int SECOND = 1;
    static final int THIRD = 2;
    static final int FOURTH = 3;
    static final int SIZE = 4;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int DEPTH = 10;
    static final int RADIUS = 7;
    static final int NUM_BALLS = 3;
    static final int EDGE = 0;
    static final int MAX_BLOCKS = 12;
    static final int MIN_BLOCKS = 7;
    static final int NUM_OF_ROWS = 6;
    static final int WIDTH_BLOCK = 50;
    static final int HEIGHT_BLOCK = 20;
    static final int Y = 170;
    static final int X = 740;
    static final int EXTRA = 30;
    static final int NONE = 0;
    static final int SINGLE = 1;
//    static final Point[] DEFAULT_POINTS = {new Point(340, 480),
//            new Point(350, 480), new Point(360, 480)};
    static final Color[] COLORS = {Color.red, Color.ORANGE, Color.YELLOW,
            Color.green, Color.cyan, Color.BLUE};
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final KeyboardSensor keyboardSensor;
//    private final GUI gui;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final LevelInformation levelInformation;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreListener;

    /**
     * Instantiates a new GameControl.Game.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
//        this.gui = new GUI("game :)", WIDTH, HEIGHT);
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
        this.running = true;
        this.runner = new AnimationRunner();
        this.keyboardSensor = this.runner.getGui().getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.scoreIndicator = new ScoreIndicator(this.score);
        this.scoreListener = new ScoreTrackingListener(this.score);
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
        levelInformation.getBackground().addToGame(this);
        //initializePaddle();
        initializeBallsOnPaddle();
        this.remainingBalls.increase(levelInformation.numberOfBalls());
        initializeBorders();
        initializeRows();
        this.scoreIndicator.addToGame(this);
    }
    /**
     * The method creates the paddle and balls and adds them to this game.
     */
    private void initializeBallsOnPaddle() {
        Paddle paddle = new Paddle(this.keyboardSensor,
                levelInformation.paddleWidth(),
                levelInformation.paddleSpeed(), levelInformation.paddleColor());
        paddle.addToGame(this);
        Point p = paddle.getCollisionRectangle().getUpperLeft();
        Ball[] balls = new Ball[levelInformation.numberOfBalls()];
        for (int i = 0; i < balls.length; i++) {
            Point point = new Point(p.getX() + 30 * i, p.getY() - RADIUS - 1);
            balls[i] = new Ball(point, RADIUS, Color.BLACK);
            balls[i].setVelocity(levelInformation.initialBallVelocities().get(i));
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
        Rectangle r = new Rectangle(new Point(0, 0), 800, 30);
        r.setColor(Color.WHITE);
        r.addToGame(this);
    }

    /**
     * The method creates the rows of blocks and adds them to this game.
     */
    public void initializeRows() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        List<Block> blocks = levelInformation.blocks();
        for (Block block : blocks) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            block.addToGame(this);
            blockRemover.addBlock();
        }
        this.remainingBlocks.increase(levelInformation.numberOfBlocksToRemove());
//        Block[] blocks = new Block[MAX_BLOCKS];
//        for (int j = 0; j < NUM_OF_ROWS; j++) {
//            for (int i = 0; i < j + MIN_BLOCKS; i++) {
//                Rectangle rect = new Rectangle(new Point(X - i * WIDTH_BLOCK,
//                        Y - j * HEIGHT_BLOCK), WIDTH_BLOCK, HEIGHT_BLOCK);
//                blocks[i] = new Block(rect, COLORS[j]);
//                blocks[i].addHitListener(blockRemover);
//                blocks[i].addHitListener(scoreListener);
//                blocks[i].addToGame(this);
//                blockRemover.addBlock();
//                this.remainingBlocks.increase(SINGLE);
//            }
//        }
    }

    /**
     * The method runs this game by starting the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
    private void bonusEvent() {
        DrawSurface d = this.runner.getGui().getDrawSurface();
        scoreListener.bonusEvent();
        this.sprites.drawAllOn(d);
        scoreIndicator.drawOn(d);
        this.runner.getGui().show(d);
//        Sleeper sleeper = new Sleeper();
//        sleeper.sleepFor(10000);
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

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboardSensor));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (remainingBalls.getValue() == NONE) {
            this.running = false;
        }
        if (remainingBlocks.getValue() == NONE) {
            this.running = false;
            //bonusEvent();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public long sleepTime() {
        return 0;
    }
    @Override
    public boolean isBonus() {
        return remainingBlocks.getValue() == NONE;
    }
    @Override
    public void activateBonusEvent(DrawSurface d) {
        scoreListener.bonusEvent();
        this.sprites.drawAllOn(d);
        scoreIndicator.drawOn(d);
    }
}