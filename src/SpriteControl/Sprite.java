// 325166510 Yael Dahari
package SpriteControl;
import GameControl.Game;
import biuoop.DrawSurface;

/**
 * Sprites can be drawn on the screen, and can be notified that time has passed
 * (so that they know to change their position / shape / appearance / etc).
 */
public interface Sprite {
    /**
     * The method draws the sprite to the screen.
     *
     * @param d (DrawSurface) - the surface we're drawing on
     */
    void drawOn(DrawSurface d);

    /**
     * The method notifies the sprite that time has passed.
     */
    void timePassed();

    /**
     * The method adds the sprite to the given game.
     *
     * @param game (GameControl.Game) - the specified game
     */
    void addToGame(Game game);
}