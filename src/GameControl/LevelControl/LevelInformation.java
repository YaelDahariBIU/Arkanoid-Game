// 325166510 Yael Dahari
package GameControl.LevelControl;
import GameControl.SpriteControl.Sprite;
import GameObjects.Block;
import Movement.Velocity;
import java.awt.Color;
import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully
 * describe a level.
 */
public interface LevelInformation {
    /**
     * The method returns the number of balls in this level.
     *
     * @return (int) - the number of balls
     */
    int numberOfBalls();

    /**
     * The method returns the initial velocity of each ball.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * The method returns the paddle's speed.
     *
     * @return (int) - the paddle's speed
     */
    int paddleSpeed();

    /**
     * The method returns the paddle's width.
     *
     * @return (int) - the paddle's width
     */
    int paddleWidth();

    /**
     * The method returns the level name.
     *
     * @return (String) - the level name
     */
    String levelName();

    /**
     * The method returns a sprite with the background of the level.
     *
     * @return (Sprite) - the background
     */
    Sprite getBackground();

    /**
     * The method returns a list of the Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return (List-Block-) the list
     */
    List<Block> blocks();

    /**
     * The method returns the number of blocks that should be removed.
     *
     * @return (int)
     */
    int numberOfBlocksToRemove();

    /**
     * The method returns the paddle's color.
     *
     * @return (Color) - the paddle's color
     */
    Color paddleColor();
}