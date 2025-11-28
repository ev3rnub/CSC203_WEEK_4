package org.verboseStory.engine;

import org.verboseStory.ui.GameWindow;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.verboseStory.engine.StaticHolderGameWindow;

import javax.swing.*;

import static org.verboseStory.ui.GameWindow.*;

public class TimerEngine {

    public TimerEngine(int someTime, CurrentMode currentMode) {
        GameWindow someWindow = StaticHolderGameWindow.someWindow;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            someWindow.setCurrentMode(CurrentMode.MENU);
            someWindow.midground.setVisible(false);
            someWindow.background.setVisible(false);
            ImageIcon someImage = someWindow.getSomeMidGround("TRAVELING");
            ImageIcon someBackground = someWindow.getSomeBackground("SNOW");
            someWindow.drawActionScreen(someBackground, someImage);
            // Optional: shut down the scheduler if you don't need more tasks
            someWindow.midground.setVisible(true);
            someWindow.background.setVisible(true);
            scheduler.shutdown();
        }, 5, TimeUnit.SECONDS);  // prints "bacon" after 5 seconds

        try {
            someWindow.setCurrentMode(CurrentMode.TRAVELING);
            someWindow.midground.setVisible(false);
            someWindow.background.setVisible(false);
            ImageIcon someImage = someWindow.getSomeMidGround("TRAVELING");
            ImageIcon someBackground = someWindow.getSomeBackground("SNOW");
            someWindow.drawActionScreen(someBackground, someImage);
            someWindow.midground.setVisible(true);
            Thread.sleep(someTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
