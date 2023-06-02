package GameControl;

import Animation.AnimationRunner;
import GameControl.LevelControl.LevelInformation;
import ScoreControl.ScoreIndicator;
import ScoreControl.ScoreTrackingListener;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreListener;
    private Counter remainingBalls;
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.score = new Counter();
        this.scoreIndicator = new ScoreIndicator(this.score);
        this.scoreListener = new ScoreTrackingListener(this.score);
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, keyboard, runner,
                    score, scoreIndicator, scoreListener);

            remainingBalls = level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            // no more balls
            if (remainingBalls.getValue() == 0) {
                break;
            }

        }
    }
}
