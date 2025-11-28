package org.verboseStory.ui;
import org.verboseStory.engine.TimerEngine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// our game window class extends JFrame
public class GameWindow extends JFrame {
//    window title
    String windowTitle;
// current mode to keep state of what our Multa/Avatar is doing
//    #FUTUREME implement the rest besides menu
    public enum CurrentMode {
        MENU,
        ACTION,
        FIGHT,
        EXPLORE,
        PLAY,
        FIND,
        ADVENTURE,
        TRAVELING
    }
//    set our default "mode"
    public CurrentMode currentMode = CurrentMode.MENU;
    Boolean gameStarted = false;
// define enum for window text color
    enum WindowTextColor {
        CYAN,
        GREEN,
        YELLOW,
        RED
    }
// default text color
    WindowTextColor windowTextColor = WindowTextColor.CYAN;
// define enum for window border color
    enum WindowBorderColor {
        CYAN,
        WHITE,
        RED,
        BLUE,
        YELLOW,
        ORANGE
    }
// set our default border color for our window
    WindowBorderColor windowBorderColor = WindowBorderColor.CYAN;
//  Reference our JTextPane to print messages.
    JTextPane output;
//  Reference bars to we can change them later.
    JPanel healthBar;
    JPanel staticBarHealth;
    JPanel happyLevel;
    JPanel staticBarhapLevel;
    JPanel hungerBar;
    JPanel staticBarHunger;
    //define imagine icons
    ImageIcon someMidGround;
    ImageIcon someForestBackground;
    ImageIcon someMountainBackground;
    ImageIcon someDesertBackground;
    ImageIcon someSnowBackground;
    ImageIcon someCavesBackground;
    public JLabel background;
    public JLabel midground;

    int marginLeft = 40;

//set window title
    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }
// set current game mode
    public void setCurrentMode(CurrentMode currentMode) {
        this.currentMode = currentMode;
    }
//    get current mode.
    public CurrentMode getCurrentMode(){
        return currentMode;
    }
// set window text color
    public void setWindowTextColor(WindowTextColor windowTextColor) {
        this.windowTextColor = windowTextColor;
    }
//set window border color
    public void setWindowBorderColor(WindowBorderColor windowBorderColor) {
        this.windowBorderColor = windowBorderColor;
    }

    public GameWindow(String windowTitle, int width, int height) {
//        Main Window
        this.windowTitle = windowTitle;
        setTitle(windowTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setResizable(false);
        ImageIcon someBackground = getSomeBackground("FOREST");
        ImageIcon someMidGround = getSomeMidGround("MENU");
        drawActionScreen(someBackground, someMidGround);
    }

    public ImageIcon getSomeBackground(String someType){
        switch(someType){
            case "FOREST":
                someForestBackground = new ImageIcon("src/main/resources/image/Forest_Background_Sunny.png");
                return someForestBackground;
            case "MOUNTAIN":
                someMountainBackground = new ImageIcon("src/main/resources/image/Mountain_Background_Sunny.png");
                return someMountainBackground;
            case "DESERT":
                someDesertBackground = new ImageIcon("src/main/resources/image/Desert_Background_Sunny.png");
                return someDesertBackground;
            case "SNOW":
                someSnowBackground = new ImageIcon("src/main/resources/image/Snow_Background_Sunny.png");
                return someSnowBackground;
            case "CAVE":
                someCavesBackground = new ImageIcon("src/main/resources/image/Cave_Background_Sunny.png");
                return someCavesBackground;
            default:
                return null;
        }



    }

    public ImageIcon getSomeMidGround(String someType){
        switch(someType){
            case "MENU":
                someMidGround = new ImageIcon("src/main/resources/image/Multa_Cutout.png");
                break;
            case "TRAVELING":
                someMidGround = new ImageIcon("src/main/resources/image/Multa_BRB_Traveling.png");
                break;
        }
        //      load images for avatar and background.

        Image someImage = someMidGround.getImage();
        Image scaledAvatarImage = someImage.getScaledInstance(256, 256, someImage.SCALE_SMOOTH);
        ImageIcon processedImage = new ImageIcon(scaledAvatarImage);
        return processedImage;
    }

    public void drawActionScreen(ImageIcon someBackground, ImageIcon someMidGround) {
        //      JPanel that holds our main panel that we attach all content to.
        JPanel main = new JPanel(new BorderLayout(10,10));
        JScrollPane scroll = new JScrollPane(textPane(Color.BLACK));
        // define its background color.
        scroll.getViewport().setBackground(Color.BLACK);
        scroll.setVisible(true);
        //add it to our main
        main.add(scroll, BorderLayout.NORTH);
        midground = new JLabel(someMidGround);
        background = new JLabel(someBackground);
        background.setVisible(true);
        background.setBounds(0, 100, 600, 300);
        midground.setVisible(true);
        midground.setBounds((int) 0, 105, 600, 300);
//      Create a layeredPane to hold our gui items
        JLayeredPane layeredPane = new JLayeredPane();
        //      Now create stat bars using colored JLayeredPanes and Jlabels.
        // default background bar
        staticBarHealth = new JPanel(new BorderLayout());
        staticBarHealth.setBackground(Color.BLACK);
        staticBarHealth.setSize(500, 20);
        staticBarHealth.setBounds(marginLeft, 0, 500, 20);
        staticBarHealth.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        staticBarHealth.setOpaque(true);
        staticBarHealth.setVisible(true);
//      HealthBar
        healthBar = new JPanel(new BorderLayout());
        healthBar.add(new JLabel("Health:"), BorderLayout.CENTER);
        healthBar.setBackground(Color.GREEN);
        healthBar.setBounds(marginLeft,0, 500, 20);
        healthBar.setSize(500, 20);
        healthBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        healthBar.setOpaque(true);
        healthBar.setVisible(true);
//      happyness
        staticBarhapLevel = new JPanel(new BorderLayout());
        staticBarhapLevel.setBackground(Color.BLACK);
        staticBarhapLevel.setSize(500, 20);
        staticBarhapLevel.setBounds(marginLeft, 20, 500, 20);
        staticBarhapLevel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        staticBarhapLevel.setOpaque(true);
        staticBarhapLevel.setVisible(true);
        happyLevel = new JPanel(new BorderLayout());
        JLabel someManaText = new JLabel("Happiness: ");
        someManaText.setForeground(Color.WHITE);
        happyLevel.add(someManaText, BorderLayout.CENTER);
        happyLevel.setBackground(Color.BLUE);
        happyLevel.setSize(100, 20);
        happyLevel.setBounds(marginLeft, 20, 500, 20);
        happyLevel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        happyLevel.setOpaque(true);
        happyLevel.setVisible(true);
//      Hunger
        staticBarHunger = new JPanel(new BorderLayout());
        staticBarHunger.setBackground(Color.BLACK);
        staticBarHunger.setSize(500, 20);
        staticBarHunger.setBounds(marginLeft, 40, 500, 20);
        staticBarHunger.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        staticBarHunger.setOpaque(true);
        staticBarHunger.setVisible(true);
        hungerBar = new JPanel(new BorderLayout());
        hungerBar.add(new JLabel("Hunger: "), BorderLayout.CENTER);
        hungerBar.setBackground(Color.YELLOW);
        hungerBar.setForeground(Color.BLACK);
        hungerBar.setSize(0, 20);
        hungerBar.setBounds(marginLeft, 40, 500, 20);
        hungerBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hungerBar.setOpaque(true);
        hungerBar.setVisible(true);
        //layered pane to hold all of our bars
        layeredPane.setBounds(0, 0, 500, 300);
        layeredPane.add(staticBarHealth, Integer.valueOf(0));
        layeredPane.add(healthBar, Integer.valueOf(1));
        layeredPane.add(staticBarhapLevel, Integer.valueOf(0));
        layeredPane.add(happyLevel, Integer.valueOf(2));
        layeredPane.add(staticBarHunger, Integer.valueOf(0));
        layeredPane.add(hungerBar, Integer.valueOf(3));
        layeredPane.add(background, Integer.valueOf(4));
        layeredPane.add(midground, Integer.valueOf(5));
        layeredPane.setVisible(true);
        layeredPane.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        //finally add the layeredPane to our main panel
        main.add(layeredPane, BorderLayout.CENTER);

//      Jpanel that holds our additional buttons.
        JPanel controlPanel = new JPanel(new GridLayout(1,4, 2,2));
        controlPanel.add(newButton("FEED", Color.CYAN, Color.BLACK, "Feed your Multa"));
        controlPanel.add(newButton("PLAY", Color.CYAN, Color.BLACK, "Play with your Multa"));
        controlPanel.add(newButton("GROOM", Color.CYAN, Color.BLACK, "Groom you Multa"));
        controlPanel.add(newButton("QUIT", Color.CYAN, Color.BLACK, "This closes the game."));

//      Combine our control panel and text input field into a new panel.
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(controlPanel, BorderLayout.SOUTH);
        main.add(inputPanel, BorderLayout.SOUTH);
//      Attach all our objects to the content pane of or JFrame.
        setContentPane(main);
        startGame();
    }
//
    private void startGame(){
        sendMessage("Welcome to Verbose Hominid:Multa");
        sendMessage("A idle game, where you send a Multa to explore its world.");
        sendMessage("Take care though, as if it dies game over.");
        sendMessage("Magical Traps set.....");
        sendMessage("Multa Acquired.....");
    }
    private JTextPane textPane (Color someColor) {
        output = new JTextPane();
        output.setBackground(someColor);
        output.setPreferredSize(new Dimension(600,200));
        output.setFont(new Font("Arial", Font.PLAIN, 20));
        output.setForeground(Color.CYAN);
        output.setVisible(true);
        output.setEditable(false);
        return output;
    }

    private JButton newButton(String text, Color someForeGroundColor, Color someBackgroundColor, String someTooltip) {
        JButton someButton = new JButton(text);
        someButton.setBackground(someBackgroundColor);
        someButton.setForeground(someForeGroundColor);
        someButton.setOpaque(true);
        someButton.setBorderPainted(false);
        someButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        someButton.setVisible(true);
        someButton.setPreferredSize(new Dimension(75,20));
        switch (text){
            case "FEED":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(feed);
                return someButton;
            case "PLAY":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(play);
                return someButton;
            case "GROOM":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(groom);
                return someButton;
            case "QUIT":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(quit);
                return someButton;
            default:
                System.out.println("INVALID Button Name: " + text);
                return null;
        }
    }

    ActionListener feed = e -> {
        sendMessage("Multa is looking in its food storage for food");
        int someInt = randomInt(0,100);
        if (someInt > 50){
            if (hungerBar.getWidth() < 100){
                sendMessage("Multa found food!");
                updateBars("health", "add", 10);
                updateBars("hunger", "subtract", randomInt(1,100));
                updateBars("happiness", "add", randomInt(1,10));
            }else if (healthBar.getWidth() < 500){
                sendMessage("Multa found food!");
                updateBars("health", "add", 10);
                updateBars("hunger", "subtract", randomInt(1,100));
                updateBars("happiness", "add", randomInt(1,10));
            }else{
                sendMessage("Multa isn't hungry");
            }

        }else{
            if (hungerBar.getWidth() > 300){
                new TimerEngine( randomInt(0,2), "Multa isn't hungry");
            }else{
                sendMessage("Multa is kinda hungry but found no food");
                updateBars("hunger", "add", randomInt(1, 15));
                updateBars("happiness", "subtract", randomInt(1,75));
            }

        }
    };

    ActionListener groom = e -> {
        updateBars("happiness", "add", 50);
        new TimerEngine(2, "Finished Grooming Multa");
    };

    ActionListener play = e -> {
        sendMessage("Multa started to play");
        int someInt =  randomInt(0, 100);
        if (someInt > 50){
            sendMessage("Multa finished playing :)");
            updateBars("hunger", "add", randomInt(5,75));
            updateBars("happiness", "add", randomInt(1,20));
        }else{
            sendMessage("Multa finished playing but hurt itself, and is now not as happy");
            updateBars("happiness", "subtract", randomInt(5,75));
            updateBars("health", "subtract", randomInt(5,75));
            updateBars("hunger", "add", randomInt(5,75));
        }

    };

    ActionListener quit = e -> {
        sendMessage("Closing Game");
        exitGame();
    };

    public void exitGame(){
        this.dispose();
    }

    public void sendMessage(String someMessage){
        String prevOutput = output.getText();
        String[] lines = prevOutput.split("\\R");
        ArrayList<String> messages = new ArrayList<>();
        for (String line : lines) {
            messages.add(line + "\n");
        }
        messages.add(someMessage);
        StringBuilder formatedMessage = new StringBuilder();
        for (String message : messages) {
            formatedMessage.append(message);
        }
        output.setText(formatedMessage.toString());
        output.setCaretPosition(output.getText().length());
    }

    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }


    public void updateBars(String someType, String operation, int amount) {
        int currentWidth;
        int newWidth;
        JPanel targetBar = null;

        switch (someType.toLowerCase()) {
            case "health":
                targetBar = healthBar;
                break;
            case "hunger":
                targetBar = hungerBar;
                break;
            case "happiness":
                targetBar = happyLevel;
                break;
            default:
                return; // do nothing
        }

        currentWidth = targetBar.getWidth();

        if (operation.equalsIgnoreCase("add")) {
            newWidth = currentWidth + amount;
        } else if (operation.equalsIgnoreCase("subtract")) {
            newWidth = currentWidth - amount;
        } else {
            return;
        }

        // Clamp between 0 and 500
        newWidth = Math.max(0, Math.min(500, newWidth));

        targetBar.setSize(newWidth, 20);
    }
}
