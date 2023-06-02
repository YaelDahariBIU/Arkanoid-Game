package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(198, 85, 243, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(66, 27, 89));
        d.drawText(150, d.getHeight() / 2, "paused -- press space to "
                + "continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public long sleepTime() {
        return 0;
    }

    @Override
    public boolean isBonus() {
        return false;
    }


    @Override
    public void activateBonusEvent(DrawSurface d) {
    }

}
