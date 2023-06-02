package GameControl.LevelControl;

import GameControl.SpriteControl.Sprite;
import GameObjects.Block;
import GeometryPrimitives.Circle;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import Movement.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelTwo implements LevelInformation {
    static final int WIDTH = 60;
    static final int HEIGHT = 20;
    static final int Y = 200;
    static final int ANGLE = -100;
    static final int CHANGE = 20;
    private final Background background;
    public LevelTwo() {
        List<Sprite> objects = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            objects.add(new Circle(randomPoint(), randomRadius(),
                    randomColor(), randomBool()));
        }
        this.background = new Background(new Color(246, 150, 227), objects);
    }
    private boolean randomBool() {
        Random rand = new Random();
        return rand.nextBoolean();
    }
    private Point randomPoint() {
        Random rand = new Random();
        double x = rand.nextDouble(10, 790);
        double y = rand.nextDouble(40, 590);
        return new Point(x, y);
    }
    private int randomRadius() {
        Random rand = new Random();
        return rand.nextInt(1, 80);
    }
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Velocity.fromAngleAndSpeed(ANGLE + (i * CHANGE), 10));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 700;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            list.add(new Block(new Rectangle(new Point(10 + (WIDTH * i), Y),
                    WIDTH, HEIGHT), randomColor()));
        }
        return list;
    }
    private Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 13;
    }

    @Override
    public Color paddleColor() {
        return new Color(199, 23, 143);
    }
}
