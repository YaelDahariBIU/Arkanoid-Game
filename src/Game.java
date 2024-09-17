// 325166510 Yael Dahari
import Animation.AnimationRunner;
import GameControl.GameFlow;
import GameControl.LevelControl.LevelOne;
import GameControl.LevelControl.LevelInformation;
import GameControl.LevelControl.LevelTwo;
import GameControl.LevelControl.LevelThree;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates a list of the desired levels and runs the game accordingly.
 */
public class Game {
    static final int NONE = 0;
    private static void addToList(List<LevelInformation> levels, String[] arr) {
        int num = NONE;
        for (String s : arr) {
            try {
               num = Integer.parseInt(s);
            } catch (NumberFormatException ignored) {
            }
            switch (num) {
                case 1 -> levels.add(new LevelOne());
                case 2 -> levels.add(new LevelTwo());
                case 3 -> levels.add(new LevelThree());
                default -> {
                }
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args (String[]) - the input arguments
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        // When run without arguments, you should start a game with three
        // levels that run one after the other.
        if (args.length == NONE) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
        } else {
            addToList(levels, args);
        }
        if (levels.size() != NONE) {
            AnimationRunner runner = new AnimationRunner();
            GameFlow gameFlow = new GameFlow(runner);
            gameFlow.runLevels(levels);
        }
    }
}
