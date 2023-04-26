// 325166510 Yael Dahari
/**
 * Create a game object with a paddle (which is controlled by the left and
 * right arrows), two balls, and blocks.
 */
public class Ass3Game {
    /**
     * The method creates a game, initializes it and runs it.
     *
     * @param args (String[]) - the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
