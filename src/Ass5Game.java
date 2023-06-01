// 325166510 Yael Dahari
import GameControl.GameLevel;
import GameControl.LevelControl.LevelOne;

/**
 * Create a game object with a paddle (which is controlled by the left and
 * right arrows), two balls, and blocks.
 */
public class Ass5Game {
    /**
     * The method creates a game, initializes it and runs it.
     *
     * @param args (String[]) - the input arguments
     */
    public static void main(String[] args) {
        LevelOne level = new LevelOne();
        GameLevel game = new GameLevel(level);
        game.initialize();
        game.run();
    }
}
