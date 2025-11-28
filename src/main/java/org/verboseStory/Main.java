package org.verboseStory;

import org.verboseStory.engine.SoundEngine;
import org.verboseStory.engine.StaticHolderGameWindow;
import org.verboseStory.ui.GameWindow;
import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static GameWindow someGameWindow;
    static void main() {
        //our game window
        someGameWindow = new GameWindow("Verbose Hominid:Multa v0.0.1", 600, 800);
        someGameWindow.setVisible(true);
        StaticHolderGameWindow.someWindow = someGameWindow;
        // Sound Engine
        SoundEngine soundEngine = new SoundEngine();
        Thread musicThread = new Thread(soundEngine::run, "SoundEngine");
        musicThread.setDaemon(true);
        musicThread.start();

    }
}
