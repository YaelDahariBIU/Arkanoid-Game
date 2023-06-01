package GeometryPrimitives;

import GameControl.GameLevel;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

public class Circle implements Sprite {
    private final Point center;
    private final int radius;
    private final Color color;
    private boolean toFill;
    public Circle(Point center, int radius, Color color, boolean toFill) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.toFill = toFill;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        if (toFill) {
            d.fillCircle((int) center.getX(), (int) center.getY(), radius);
        } else {
            d.drawCircle((int) center.getX(), (int) center.getY(), radius);
        }
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public boolean isABall() {
        return false;
    }
}
