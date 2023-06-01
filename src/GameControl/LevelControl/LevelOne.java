package GameControl.LevelControl;

import GameControl.SpriteControl.Sprite;
import GameObjects.Block;
import GeometryPrimitives.Circle;
import GeometryPrimitives.Line;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import Movement.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class LevelOne implements LevelInformation {
    static final Point CENTER = new Point(400, 160);
    private final Background background;
    public LevelOne() {
        Color burgundy = new Color(86, 6, 9);
        List<Sprite> objects = new ArrayList<>();
        Line l1 = new Line(new Point(400, 50), new Point(400, 130));
        l1.setColor(burgundy);
        objects.add(l1);
        Line l2 = new Line(new Point(400, 190), new Point(400, 270));
        l2.setColor(burgundy);
        objects.add(l2);
        Line l3 = new Line(new Point(290, 160), new Point(370, 160));
        l3.setColor(burgundy);
        objects.add(l3);
        Line l4 = new Line(new Point(430, 160), new Point(510, 160));
        l4.setColor(burgundy);
        objects.add(l4);
        objects.add(new Circle(CENTER, 60, burgundy, false));
        objects.add(new Circle(CENTER, 75, burgundy, false));
        objects.add(new Circle(CENTER, 90, burgundy, false));
        this.background = new Background(new Color(210, 185, 82), objects);
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(0, 10));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(380, 140),
                40, 40), Color.red));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
