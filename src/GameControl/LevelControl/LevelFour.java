// 325166510 Yael Dahari
package GameControl.LevelControl;
import GameControl.SpriteControl.Sprite;
import GameObjects.Block;
import GeometryPrimitives.Circle;
import GeometryPrimitives.Line;
import GeometryPrimitives.Point;
import Movement.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Level four.
 */
public class LevelFour implements LevelInformation {
    static final int WIDTH_BLOCK = 60;
    static final int HEIGHT_BLOCK = 20;
    static final int X = 730;
    static final int Y = 200;
    static final Color GRAY1 = new Color(161, 157, 157);
    static final Color GRAY2 = new Color(143, 139, 139);
    static final Color GRAY3 = new Color(100, 97, 97);
    static final Color GRAY4 = new Color(80, 80, 80);
    private final Background background;

    /**
     * Instantiates a new Level four.
     */
    public LevelFour() {
        List<Sprite> objects = new ArrayList<>();
        objects.add(new Circle(new Point(150, 150), 80, Color.YELLOW, true));
        Line[] lines = new Line[24];
        for (int i = 0; i < 6; i++) {
            lines[i] = new Line(new Point(70 + (i * 10), 450),
                    new Point(40 + (i * 10), 590));
            lines[i].setColor(Color.WHITE);
            objects.add(lines[i]);
        }
        objects.add(new Circle(new Point(50, 450), 30, GRAY1, true));
        objects.add(new Circle(new Point(80, 470), 45, GRAY2, true));
        objects.add(new Circle(new Point(90, 430), 45, GRAY3, true));
        objects.add(new Circle(new Point(110, 470), 20, GRAY3, true));
        objects.add(new Circle(new Point(130, 450), 50, GRAY4, true));
        for (int i = 0; i < 6; i++) {
            lines[i + 6] = new Line(new Point(270 + (i * 10), 350),
                    new Point(250 + (i * 10), 590));
            lines[i + 6].setColor(Color.WHITE);
            objects.add(lines[i + 6]);
        }
        objects.add(new Circle(new Point(250, 350), 30, GRAY1, true));
        objects.add(new Circle(new Point(280, 370), 45, GRAY2, true));
        objects.add(new Circle(new Point(290, 330), 45, GRAY3, true));
        objects.add(new Circle(new Point(310, 370), 20, GRAY3, true));
        objects.add(new Circle(new Point(330, 350), 50, GRAY4, true));
        for (int i = 0; i < 6; i++) {
            lines[i + 12] = new Line(new Point(450 + (i * 10), 550),
                    new Point(420 + (i * 10), 590));
            lines[i + 12].setColor(Color.WHITE);
            objects.add(lines[i + 12]);
        }
        objects.add(new Circle(new Point(430, 550), 30, GRAY1, true));
        objects.add(new Circle(new Point(460, 570), 45, GRAY2, true));
        objects.add(new Circle(new Point(470, 530), 45, GRAY3, true));
        objects.add(new Circle(new Point(490, 570), 20, GRAY3, true));
        objects.add(new Circle(new Point(510, 550), 50, GRAY4, true));
        for (int i = 0; i < 6; i++) {
            lines[i + 18] = new Line(new Point(650 + (i * 10), 350),
                    new Point(620 + (i * 10), 590));
            lines[i + 18].setColor(Color.WHITE);
            objects.add(lines[i + 18]);
        }
        objects.add(new Circle(new Point(630, 350), 30, GRAY1, true));
        objects.add(new Circle(new Point(660, 370), 45, GRAY2, true));
        objects.add(new Circle(new Point(670, 330), 45, GRAY3, true));
        objects.add(new Circle(new Point(690, 370), 20, GRAY3, true));
        objects.add(new Circle(new Point(710, 350), 50, GRAY4, true));
        this.background = new Background(new Color(135, 206, 250), objects);
    }
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(-30, 5));
        list.add(Velocity.fromAngleAndSpeed(0, 5));
        list.add(Velocity.fromAngleAndSpeed(30, 5));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }
    private Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int j = 0; j < 7; j++) {
            Color color = randomColor();
            for (int i = 0; i < 13; i++) {
                GeometryPrimitives.Rectangle rect = new GeometryPrimitives.Rectangle(
                        new Point(X - i * WIDTH_BLOCK,
                        Y - j * HEIGHT_BLOCK), WIDTH_BLOCK, HEIGHT_BLOCK);
                blocks.add(new Block(rect, color));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 7 * 13;
    }

    @Override
    public Color paddleColor() {
        return new Color(32, 58, 108);
    }
}
