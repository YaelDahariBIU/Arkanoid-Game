import GameControl.Game;
import GameControl.GameEnvironment;
import GameObjects.Ball;
import GameObjects.Block;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import SpriteControl.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleMain {
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
    public static void createBlocks() {
        GUI gui = new GUI("two frames bouncing balls", 600, 600);
        Ball ball = new Ball(500, 200, 10, java.awt.Color.BLACK);
        GameEnvironment game = new GameEnvironment();
        ball.setEnvironment(game);
        ball.setVelocity(10, 10);
        List<Sprite> sprites = new ArrayList<>();
        sprites.add(ball);
        Block[] blocks = new Block[9];
        for (int i = 0; i < 4; i++) {
            blocks[i] = new Block(new GeometryPrimitives.Rectangle(randomPoint(),
                    randomSize(100), randomSize(100)), Color.BLACK);
            game.addCollidable(blocks[i]);
        }
        blocks[4] = new Block(new Rectangle(new GeometryPrimitives.Point(100, 300), 300, 200), Color.BLUE);
        blocks[5] = new Block(new GeometryPrimitives.Rectangle(new GeometryPrimitives.Point(0, 0), 600, 10), Color.BLUE);
        blocks[6] = new Block(new GeometryPrimitives.Rectangle(new GeometryPrimitives.Point(0, 10), 10, 600), Color.BLUE);
        blocks[7] = new Block(new GeometryPrimitives.Rectangle(new GeometryPrimitives.Point(590, 10), 10, 590), Color.BLUE);
        blocks[8] = new Block(new GeometryPrimitives.Rectangle(new Point(10, 590), 580, 10), Color.BLUE);
        game.addCollidable(blocks[4]);
        game.addCollidable(blocks[5]);
        game.addCollidable(blocks[6]);
        game.addCollidable(blocks[7]);
        game.addCollidable(blocks[8]);
        sprites.addAll(List.of(blocks));
        drawAnimation(sprites, gui);
        //drawAnimation1(blocks, ball, gui);
    }
    private static GeometryPrimitives.Point randomPoint() {
        Random rand = new Random();
        double x = rand.nextDouble() * 600;
        double y = rand.nextDouble() * 600;
        return new GeometryPrimitives.Point(x, y);
    }
    private static double randomSize(int maxSize) {
        Random rand = new Random();
        return rand.nextDouble() * maxSize;
    }
    public static void drawAnimation1(Block[] blocks, Ball ball, GUI gui) {
        Sleeper sleeper = new Sleeper();
        DrawSurface d;
        while (true) {
            ball.moveOneStep();
            d = gui.getDrawSurface();
            ball.drawOn(d);
            for (Block block : blocks) {
                block.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
    public static void drawAnimation(List<Sprite> sprites, GUI gui) {
        Sleeper sleeper = new Sleeper();
        DrawSurface d;
        while (true) {
            d = gui.getDrawSurface();
            for (Sprite sprite : sprites) {
                sprite.drawOn(d);
                sprite.timePassed();
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
