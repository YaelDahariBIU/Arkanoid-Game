import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
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
        initializeBall();
        initializeBorders();
        initializeBlocks();
    }
    private void initializeBall() {
        Ball ball = new Ball(500, 200, 10, Color.RED);
        ball.setVelocity(10, 10);
        ball.setEnvironment(this.environment);
        ball.addToGame(this);
    }
    private void initializeBorders() {
        Block[] borders = new Block[4];
        borders[0] = new Block(new Rectangle(new Point(0, 0), 800, 10));
        borders[1] = new Block(new Rectangle(new Point(0, 10), 10, 600));
        borders[2] = new Block(new Rectangle(new Point(790, 10), 10, 590));
        borders[3] = new Block(new Rectangle(new Point(10, 590), 780, 10));
        for (Block border : borders) {
            border.addToGame(this);
        }
    }
    private void initializeBlocks() {
        Block[] blocks = new Block[4];
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block(new Rectangle(randomPoint(), randomSize(),
                    randomSize()));
            blocks[i].addToGame(this);
        }
    }
    private static Point randomPoint() {
        Random rand = new Random();
        double x = rand.nextDouble() * 800;
        double y = rand.nextDouble() * 600;
        return new Point(x, y);
    }
    private static double randomSize() {
        Random rand = new Random();
        return rand.nextDouble() * 100;
    }

    // Run the game -- start the animation loop.
    public void run() {
        GUI gui = new GUI("game :)", 800, 600);
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
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