package org.verboseStory.engine;

import org.verboseStory.ui.GameWindow;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

// a simple class to use the scheduledexectorservice to create timed event.
public class TimerEngine {
    // get our static holder for our GameWindow.
    GameWindow someWindow = StaticHolderGameWindow.someWindow;
//    when sometime expires send message somemessage and chut down the scheduler.
public TimerEngine(int someTime, String someMessage) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            someWindow.sendMessage(someMessage);
            scheduler.shutdown();
        }, someTime, TimeUnit.SECONDS);
    }
}
