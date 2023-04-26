import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

public class Game {
    static final Color[] COLORS = {Color.blue, Color.cyan, Color.green,
            Color.YELLOW, Color.ORANGE, Color.red};
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("game :)", 800, 600);
        this.keyboardSensor = this.gui.getKeyboardSensor();
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        initializePaddle();
        initializeBalls();
        initializeBorders();
        initializeRows();
    }
    private void initializePaddle() {
        Paddle paddle = new Paddle(this.keyboardSensor);
        paddle.addToGame(this);
    }
    private void initializeBalls() {
        Ball[] balls = new Ball[2];
        for (int i = 0; i < 2; i++) {
            balls[i] = new Ball(randomPoint(), 10, Color.BLACK);
            balls[i].setVelocity(10, 10);
            balls[i].setEnvironment(this.environment);
            balls[i].addToGame(this);
        }
    }
    private void initializeBorders() {
        Block[] borders = new Block[4];
        borders[0] = new Block(new Rectangle(new Point(0, 0),
                800, 10), Color.gray);
        borders[1] = new Block(new Rectangle(new Point(0, 10),
                10, 600), Color.gray);
        borders[2] = new Block(new Rectangle(new Point(790, 10),
                10, 590), Color.gray);
        borders[3] = new Block(new Rectangle(new Point(10, 590),
                780, 10), Color.gray);
        for (Block border : borders) {
            border.addToGame(this);
        }
    }
    public void initializeRows() {
        Block[] blocks = new Block[12];
        int width = 50, height = 20, y = 170, x = 790 - width;
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < j + 7; i++) {
                Rectangle rect = new Rectangle(new Point(x - i * width,
                        y - j * height), width, height);
                blocks[i] = new Block(rect, COLORS[j]);
                blocks[i].addToGame(this);
            }
        }
    }
    private static Point randomPoint() {
        Random rand = new Random();
        double x = 160 + rand.nextDouble() * 800;
        double y = 160 + rand.nextDouble() * 600;
        return new Point(x, y);
    }

    // Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}