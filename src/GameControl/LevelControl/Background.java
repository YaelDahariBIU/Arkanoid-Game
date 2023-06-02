package GameControl.LevelControl;

import GameControl.GameLevel;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

public class Background implements Sprite {
    private final Color color;
    private final List<Sprite> objects;
    public Background(Color color, List<Sprite> objects) {
        this.color = color;
        this.objects = objects;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 0, 800, 600);
        for (Sprite object : objects) {
            object.drawOn(d);
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
