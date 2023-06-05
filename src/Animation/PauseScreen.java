// 325166510 Yael Dahari
package Animation;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Pausing the game when pressing the "p" key.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(198, 85, 243, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(66, 27, 89));
        d.drawText(150, d.getHeight() / 2, "paused -- press space to "
                + "continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
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
