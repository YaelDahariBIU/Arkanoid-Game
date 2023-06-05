// 325166510 Yael Dahari
package GameControl;
import Animation.AnimationRunner;
import Animation.GameOverScreen;
import Animation.KeyPressStoppableAnimation;
import Animation.YouWinScreen;
import GameControl.LevelControl.LevelInformation;
import ScoreControl.ScoreIndicator;
import ScoreControl.ScoreTrackingListener;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The Game flow is in charge of creating the different levels, and moving from
 * one level to the next.
 */
public class GameFlow {
    static final int NONE = 0;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter score;
    private final ScoreIndicator scoreIndicator;
    private final ScoreTrackingListener scoreListener;
    private Counter remainingBalls;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar (AnimationRunner) - the AnimationRunner
     */
    public GameFlow(AnimationRunner ar) {
        this.runner = ar;
        this.keyboard = ar.getKeyboard();
        this.score = new Counter();
        this.scoreIndicator = new ScoreIndicator(this.score);
        this.scoreListener = new ScoreTrackingListener(this.score);
    }

    /**
     * The method runs the given levels.
     *
     * @param levels (List-LevelInformation>) - the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, keyboard, runner,
                    scoreIndicator, scoreListener);

            remainingBalls = level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            // no more balls
            if (remainingBalls.getValue() == NONE) {
                break;
            }

        }
        // the player either lost or won
        if (remainingBalls.getValue() == NONE) {
            runner.run(new KeyPressStoppableAnimation(keyboard, "space",
                            new GameOverScreen(score)));
        } else {
            runner.run(new KeyPressStoppableAnimation(keyboard, "space",
                    new YouWinScreen(score)));
        }
        runner.close();
    }
}
