package org.verboseStory.engine;

import org.verboseStory.ui.GameWindow;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.verboseStory.engine.StaticHolderGameWindow;

public class TimerEngine {

    public TimerEngine(int someTime, GameWindow.CurrentMode currentMode) {
        GameWindow someWindow = StaticHolderGameWindow.someWindow;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            someWindow.setCurrentMode(GameWindow.CurrentMode.MENU);
            // Optional: shut down the scheduler if you don't need more tasks
            someWindow.midground.setVisible(true);
            scheduler.shutdown();
        }, 5, TimeUnit.SECONDS);  // prints "bacon" after 5 seconds

        try {
            someWindow.setCurrentMode(GameWindow.CurrentMode.TRAVELING);
            someWindow.midground.setVisible(false);
            Thread.sleep(someTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
