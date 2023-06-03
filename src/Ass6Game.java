import Animation.AnimationRunner;
import GameControl.GameFlow;
import GameControl.LevelControl.*;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

public class Ass6Game {
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner();
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, ks);
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new LevelOne());
        levels.add(new LevelTwo());
        levels.add(new LevelThree());
        levels.add(new LevelFour());
        gameFlow.runLevels(levels);
    }
}
