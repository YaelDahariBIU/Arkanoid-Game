// 325166510 Yael Dahari
package GameControl.LevelControl;
import GameControl.SpriteControl.Sprite;
import GameObjects.Block;
import GeometryPrimitives.Circle;
import GeometryPrimitives.Line;
import GeometryPrimitives.Point;
import Movement.Velocity;
import GeometryPrimitives.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The type Level three.
 */
public class LevelThree implements LevelInformation {
    static final Color[] COLORS = {Color.yellow, Color.orange, Color.white};
    static final int[] RADIUS = {40, 20, 10};
    static final int WIDTH_BLOCK = 50;
    static final int HEIGHT_BLOCK = 20;
    static final int Y = 200;
    static final int X = 740;
    static final Color GRAY1 = new Color(161, 157, 157);
    static final Color GRAY2 = new Color(143, 139, 139);
    static final Color GRAY3 = new Color(122, 119, 119);
    static final Color GRAY4 = new Color(108, 105, 105);
    private final Background background;

    /**
     * Instantiates a new Level three.
     */
    public LevelThree() {
        List<Sprite> objects = new ArrayList<>();
        objects.add(new Circle(new Point(150, 150), 80, Color.YELLOW, true));
        objects.add(new Circle(new Point(250, 150), 30, GRAY1, true));
        objects.add(new Circle(new Point(280, 170), 45, GRAY2, true));
        objects.add(new Circle(new Point(290, 130), 45, GRAY3, true));
        objects.add(new Circle(new Point(310, 170), 20, GRAY3, true));
        objects.add(new Circle(new Point(330, 150), 50, GRAY4, true));
        objects.add(new Circle(new Point(95, 450), 75, randomColor(), true));
        Rectangle[] recs = new Rectangle[18];
        recs[0] = new Rectangle(new Point(20, 450), 150, 140);
        recs[0].setColor(randomColor());
        recs[1] = new Rectangle(new Point(50, 470), 30, 30);
        recs[1].setColor(Color.WHITE);
        recs[2] = new Rectangle(new Point(110, 470), 30, 30);
        recs[2].setColor(Color.WHITE);
        recs[3] = new Rectangle(new Point(75, 530), 40, 60);
        recs[3].setColor(randomColor());
        recs[4] = new Rectangle(new Point(330, 290), 150, 300);
        recs[4].setColor(randomColor());
        recs[5] = new Rectangle(new Point(380, 520), 50, 70);
        recs[5].setColor(randomColor());
        int k = 6;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                recs[k] = new Rectangle(new Point(365 + (j * 32),
                        310 + (i * 50)), 15, 30);
                recs[k].setColor(Color.WHITE);
                k = k + 1;
            }
        }
        objects.addAll(Arrays.asList(recs));
        Line l1 = new Line(new Point(220, 590), new Point(220, 485));
        l1.setColor(Color.BLACK);
        objects.add(l1);
        Line l2 = new Line(new Point(280, 590), new Point(280, 440));
        l2.setColor(Color.BLACK);
        objects.add(l2);
        for (int i = 0; i < 3; i++) {
            objects.add(new Circle(new Point(220, 445), RADIUS[i],
                    COLORS[i], true));
            objects.add(new Circle(new Point(280, 400), RADIUS[i],
                    COLORS[i], true));
        }
        objects.add(new Circle(new Point(430, 150), 30, GRAY1, true));
        objects.add(new Circle(new Point(460, 170), 45, GRAY2, true));
        objects.add(new Circle(new Point(470, 130), 45, GRAY3, true));
        objects.add(new Circle(new Point(490, 170), 20, GRAY3, true));
        objects.add(new Circle(new Point(510, 150), 50, GRAY4, true));
        Line l3 = new Line(new Point(530, 590), new Point(530, 485));
        l3.setColor(Color.BLACK);
        objects.add(l3);
        Line l4 = new Line(new Point(590, 590), new Point(590, 440));
        l4.setColor(Color.BLACK);
        objects.add(l4);
        for (int i = 0; i < 3; i++) {
            objects.add(new Circle(new Point(530, 445), RADIUS[i],
                    COLORS[i], true));
            objects.add(new Circle(new Point(590, 400), RADIUS[i],
                    COLORS[i], true));
        }
        this.background = new Background(new Color(44, 220, 110), objects);
    }
    private Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(-30, 7));
        list.add(Velocity.fromAngleAndSpeed(30, 7));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Color color = randomColor();
            for (int i = 0; i < j + 6; i++) {
                Rectangle rect = new Rectangle(new Point(X - i * WIDTH_BLOCK,
                        Y - j * HEIGHT_BLOCK), WIDTH_BLOCK, HEIGHT_BLOCK);
                blocks.add(new Block(rect, color));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public Color paddleColor() {
        return new Color(14, 96, 5);
    }
}
