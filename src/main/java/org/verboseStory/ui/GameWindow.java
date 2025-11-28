package org.verboseStory.ui;

import org.verboseStory.engine.TimerEngine;
import org.verboseStory.model.*;
import org.verboseStory.model.Character;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameWindow extends JFrame {
    String windowTitle;
// current mode to keep state of what our Multa/Avatar is doing
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
    CurrentMode currentMode = CurrentMode.MENU;
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
    JPanel manaBar;
    JPanel staticBarMana;
    JPanel stamBar;
    JPanel staticBarStam;
    JPanel xpBar;
    JPanel staticXpBar;
    JPanel mainInput;

    int currentFoodPoints = 5;
    int restRegenRate = 200;
    int marginLeft = 40;
    int foodXpRate = 20;
    int exploreXpRate = 20;
    int adventureXpRate = 20;
    int forageXpRate = 20;

    ImageIcon someBackground;
    ImageIcon someMidGround;
    ImageIcon someForestBackground;
    ImageIcon someMountainBackground;
    public JLabel background;
    public JLabel midground;
    Character someMulta;
//set window title
    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }
// set current game mode
    public void setCurrentMode(CurrentMode currentMode) {
        this.currentMode = currentMode;
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
        drawActionScreen();
        mainInput.setVisible(false);
    }

    public

    private void drawActionScreen(){
        //      JPanel that holds our main panel that we attach all content to.
        JPanel main = new JPanel(new BorderLayout(10,10));
        JScrollPane scroll = new JScrollPane(textPane(Color.BLACK));
        // define its background color.
        scroll.getViewport().setBackground(Color.BLACK);
        scroll.setVisible(true);
        //add it to our main
        main.add(scroll, BorderLayout.NORTH);
//      load images for avatar and background.
        someForestBackground = new ImageIcon("src/main/resources/image/Forest_Background_Sunny.png");
        someMountainBackground = new ImageIcon("src/main/resources/image/Mountain_Background_Sunny.png");
        someDesertBackground = new ImageIcon("src/main/recources/image/Desert_Background_Sunny.png");
        someMidGround = new ImageIcon("src/main/resources/image/Multa_Cutout.png");
        someBackground = new ImageIcon("src/main/resources/image/Forest_Background_Sunny.png");
        Image someImage = someMidGround.getImage();
        Image scaledAvatarImage = someImage.getScaledInstance(256, 256, someImage.SCALE_SMOOTH);
        ImageIcon processedImage = new ImageIcon(scaledAvatarImage);
        background = new JLabel(someBackground);
        midground = new JLabel(processedImage);
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
//      ManaBar
        staticBarMana = new JPanel(new BorderLayout());
        staticBarMana.setBackground(Color.BLACK);
        staticBarMana.setSize(500, 20);
        staticBarMana.setBounds(marginLeft, 20, 500, 20);
        staticBarMana.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        staticBarMana.setOpaque(true);
        staticBarMana.setVisible(true);
        manaBar = new JPanel(new BorderLayout());
        JLabel someManaText = new JLabel("Mana:");
        someManaText.setForeground(Color.WHITE);
        manaBar.add(someManaText, BorderLayout.CENTER);
        manaBar.setBackground(Color.BLUE);
        manaBar.setSize(500, 20);
        manaBar.setBounds(marginLeft, 20, 500, 20);
        manaBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        manaBar.setOpaque(true);
        manaBar.setVisible(true);
//      Stamina
        staticBarStam = new JPanel(new BorderLayout());
        staticBarStam.setBackground(Color.BLACK);
        staticBarStam.setSize(500, 20);
        staticBarStam.setBounds(marginLeft, 40, 500, 20);
        staticBarStam.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        staticBarStam.setOpaque(true);
        staticBarStam.setVisible(true);
        stamBar = new JPanel(new BorderLayout());
        stamBar.add(new JLabel("Stamina:"), BorderLayout.CENTER);
        stamBar.setBackground(Color.YELLOW);
        stamBar.setForeground(Color.BLACK);
        stamBar.setSize(500, 20);
        stamBar.setBounds(marginLeft, 40, 500, 20);
        stamBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        stamBar.setOpaque(true);
        stamBar.setVisible(true);

        //xp bar
        staticXpBar = new JPanel();
        staticXpBar.setBackground(Color.BLACK);
        JLabel staticXpText = new JLabel();
        staticXpText.setForeground(Color.WHITE);
        staticXpText.setText("XP:");
        staticXpBar.setSize(500, 20);
        staticXpBar.setBounds(marginLeft, 60, 500, 20);
        staticXpBar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        staticXpBar.setOpaque(true);
        staticXpBar.setVisible(true);
        xpBar = new JPanel();
        JLabel xpLabel = new JLabel();
        xpLabel.setText("XP:");
        xpLabel.setForeground(Color.WHITE);
        xpBar.add(xpLabel);
        xpBar.setBackground(Color.DARK_GRAY);
        xpBar.setSize(500, 20);
        xpBar.setBounds(marginLeft, 60, 500, 20);
        xpBar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        xpBar.setOpaque(true);
        xpBar.setVisible(true);


        //display text based statues

        layeredPane.setBounds(0, 0, 600, 300);
        layeredPane.add(staticBarHealth, Integer.valueOf(0));
        layeredPane.add(healthBar, Integer.valueOf(1));
        layeredPane.add(staticBarMana, Integer.valueOf(0));
        layeredPane.add(manaBar, Integer.valueOf(2));
        layeredPane.add(staticBarStam, Integer.valueOf(0));
        layeredPane.add(stamBar, Integer.valueOf(3));
        layeredPane.add(background, Integer.valueOf(4));
        layeredPane.add(midground, Integer.valueOf(5));
        layeredPane.add(staticXpBar, Integer.valueOf(6));
        layeredPane.add(xpBar, Integer.valueOf(7));
        JLabel multaName = new JLabel();
        multaName.setHorizontalAlignment(SwingConstants.CENTER);
        multaName.setFont(new Font( "Ariel", Font.BOLD, 40));
        multaName.setName("Multa Name");
        layeredPane.add(multaName, Integer.valueOf(6));
        layeredPane.setVisible(true);
        layeredPane.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        main.add(layeredPane, BorderLayout.CENTER);

//      JPanel that holds our text input field for the player.
        mainInput = new JPanel();
        mainInput.add(textField(),  BorderLayout.WEST);
        mainInput.add(newButton("SEND", Color.BLACK, Color.CYAN, "Send a message to your multa"), BorderLayout.EAST);

//      Jpanel that holds our additional buttons.
        JPanel controlPanel = new JPanel(new GridLayout(3,3, 2,2));
        controlPanel.add(newButton("FEED", Color.CYAN, Color.BLACK, "Feed your Multa"));
        controlPanel.add(newButton("PLAY", Color.CYAN, Color.BLACK, "Play with your Multa"));
        controlPanel.add(newButton("REST", Color.CYAN, Color.BLACK, "Command your Multa to rest"));
        controlPanel.add(newButton("FORAGE", Color.CYAN, Color.BLACK, "Command your Multa to Forage for food"));
        controlPanel.add(newButton("ADVENTURE", Color.CYAN, Color.BLACK, "Command your Multa to adventure"));
        controlPanel.add(newButton("HEAL", Color.CYAN, Color.BLACK, "Heal your Multa"));
        controlPanel.add(newButton("MEDITATE", Color.CYAN, Color.BLACK, "Meditate to regen Mana"));
        controlPanel.add(newButton("EXPLORE", Color.CYAN, Color.BLACK, "Explore to find locations to adventure in"));
        controlPanel.add(newButton("QUIT", Color.CYAN, Color.BLACK, "This closes the game."));

//      Combine our control panel and text input field into a new panel.
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(mainInput, BorderLayout.NORTH);
        inputPanel.add(controlPanel, BorderLayout.SOUTH);
        main.add(inputPanel, BorderLayout.SOUTH);
//      Attach all our objects to the content pane of or JFrame.
        setContentPane(main);
        startGame();
    }

    private void startGame(){
        sendMessage("Welcome to Verbose Hominid:Multa");
        sendMessage("A idle game, where you send a Multa to explore its world.");
        sendMessage("Take care though, as if it dies game over.");
        sendMessage("Magical Traps set.....");
        sendMessage("Multa Acquired.....");
        while(gameStarted){
            if (healthBar.getWidth() == 0){
                sendMessage("Multa Dead");
                gameStarted = false;
            }else if (healthBar.getWidth() > 600){
                healthBar.setSize(600, 20);
            }

        }
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

    JTextField someInputField;
    private JTextField textField() {
        someInputField = new JTextField();
        someInputField.setBackground(Color.BLACK);
        someInputField.setForeground(Color.CYAN);
        someInputField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        someInputField.setEditable(true);
        someInputField.setVisible(true);
        someInputField.setToolTipText("Enter text here");
        someInputField.setCaretColor(Color.CYAN);
        someInputField.setPreferredSize(new Dimension(400,40));
        someInputField.addActionListener(send);
        return someInputField;
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
            case "SEND":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(send);
                return someButton;
            case "FEED":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(feed);
                return someButton;
            case "PLAY":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(play);
                return someButton;
            case "REST":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(rest);
                return someButton;
            case "FORAGE":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(forage);
                return someButton;
            case "ADVENTURE":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(adventure);
                return someButton;
            case "HEAL":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(heal);
                return someButton;
            case "MEDITATE":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(meditate);
                return someButton;
            case "EXPLORE":
                someButton.setToolTipText(someTooltip);
                someButton.addActionListener(explore);
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

    ActionListener send = e -> {
        String someLine = someInputField.getText().trim();
        if (someLine.equals("q") || someLine.equals("quit") || someLine.equals("exit")){
            exitGame();
        }
        if (!someLine.isEmpty()) {
            sendMessage(someLine);
            someInputField.setText("");
            someInputField.requestFocus();
        }
    };

    ActionListener feed = e -> {
        sendMessage("Multa is looking in its food storage for food");
        if (currentFoodPoints > 1){
            sendMessage("Multa started to eat");
            updateBars("health", "add", 10);
            sendMessage("Gained " + foodXpRate + " experience points");
            updateBars("xp", "add", foodXpRate);
            sendMessage("Multa finished eating");
            currentFoodPoints--;
        }else{
            sendMessage("Multa has no food to eat.");
        }
    };

    ActionListener play = e -> {
        sendMessage("Multa started to play");
        if(stamBar.getWidth() > 50){
            updateBars("stamina", "subtract", 50);
            updateBars("experience points", "add", 10);
            sendMessage("Multa finished playing");
        }else{
            sendMessage("Multa is tired and does not have enough stamina to play.");
        }


    };

    ActionListener rest = e -> {
        sendMessage("Multa started to rest");
        updateBars("stamina", "add", restRegenRate);
        sendMessage("Multa's STAMINA recovered " + restRegenRate + " points");
        sendMessage("Multa finished resting");

    };

    ActionListener meditate = e -> {
        sendMessage("Multa started to meditate");
        updateBars("mana", "add", 20);
        sendMessage("Multa finished to meditate");
    };

    ActionListener explore = e -> {
        sendMessage("Multa started to explore");
        boolean explorationResult = multaExplore();
        if (explorationResult){
            TimerEngine someTimer = new TimerEngine(5, CurrentMode.EXPLORE);
            updateBars("stamina", "subtract", 50);
            int someXP = randomInt(0, 200);
            updateBars("xp", "add", someXP);
            sendMessage("Multa finished exploring and acquired " + someXP + "experience points");
        }else{
            Boolean enemyResult = enemyPresent();
            if (enemyResult){
                int someDmg = randomInt(0, 100);
                sendMessage("Multa took " + someDmg + " points of Damage due to an enemy encounter");
                updateBars("health", "subtract", someDmg);
                int someXP = randomInt(0, 100);
                updateBars("xp", "add", someXP);
            }else{

            }
        }

    };

    ActionListener forage = e -> {
        sendMessage("Multa started to explore");
        updateBars("stamina", "subtract", 25);
        Boolean success = multaForage();
        if (success){
            sendMessage("Multa finished foraging, finding 1 food");
        }else{
            sendMessage("Multa finished foraging, no food found");
        }

    };

    ActionListener adventure = e -> {
        sendMessage("Multa has started adventure");
        updateBars("stamina", "subtract", 200);
        sendMessage("Multa has finished adventuring");
    };

    ActionListener heal = e -> {
        int mana = manaBar.getWidth();
        if (mana >= 400){
            sendMessage("Multa has started casting healing");
            updateBars("health", "add", 200);
            updateBars("mana", "subtract", 400);
        }else{
            sendMessage("Multa tried casting but doesn't have the Mana");
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
    }

    public Boolean multaForage(){
        int someChance = randomInt(0, 100);
        if (someChance < 60){
            currentFoodPoints ++;
            return true;
        }else{
            return false;
        }
    }

    public Boolean multaExplore(){
        int someChance = randomInt(0, 100);
        if (someChance < 50){
            return true;
        }else{
            return false;
        }
    }

    public Boolean enemyPresent(){
        int someChance = randomInt(0, 100);
        if (someChance < 50){
            return true;
        }else{
            return false;
        }
    }

    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public void updateBars(String someType, String operation, int someInt){
        switch (someType.toLowerCase()) {
            case "health":
                if (operation.toLowerCase().equals("add")) {
                    int currentHealth = healthBar.getWidth();
                    currentHealth = currentHealth + someInt;
                    if (currentHealth > 600) {
                        healthBar.setSize(600, 20);
                    } else {
                        healthBar.setSize(currentHealth, 20);
                        int afterSize = healthBar.getWidth();
                        if (afterSize > 600) {
                            healthBar.setSize(600, 20);
                        } else {
                            healthBar.setSize(currentHealth, 20);
                        }
                    }
                    int lastHP = healthBar.getWidth();
                    if (lastHP > 600) {
                        healthBar.setSize(600, 20);
                    }
                }
                if (operation.toLowerCase().equals("subtract")) {
                    int currentHealth = healthBar.getWidth();
                    currentHealth = currentHealth - someInt;
                    if (currentHealth < 0) {
                        healthBar.setSize(0, 20);
                    } else {
                        healthBar.setSize(currentHealth, 20);
                    }
                }
            case "stamina":
                if (operation.toLowerCase().equals("add")) {
                    int currentStamina = stamBar.getWidth();
                    currentStamina = currentStamina + someInt;
                    System.out.println(currentStamina);
                    if (currentStamina > 600) {
                        stamBar.setSize(600, 20);
                        System.out.println("INSIDE StAM IF");
                    } else {
                        stamBar.setSize(currentStamina, 20);
                        int afterSize = stamBar.getWidth();
                        if (afterSize > 600) {
                            stamBar.setSize(600, 20);
                        } else {
                            stamBar.setSize(currentStamina, 20);
                        }
                    }
                    int lastStam = stamBar.getWidth();
                    if (lastStam > 600) {
                        stamBar.setSize(600, 20);
                    }
                }
                if (operation.toLowerCase().equals("subtract")) {
                    int currentStamina = stamBar.getWidth();
                    currentStamina = currentStamina - someInt;
                    if (currentStamina < 0) {
                        stamBar.setSize(0, 20);
                    } else {
                        stamBar.setSize(currentStamina, 20);
                    }
                }
            case "mana":
                if (operation.toLowerCase().equals("add")) {
                    int currentMana = manaBar.getWidth();
                    currentMana = currentMana + someInt;
                    if (currentMana > 600) {
                        manaBar.setSize(600, 20);
                    } else {
                        manaBar.setSize(currentMana, 20);
                        int afterSize = manaBar.getWidth();
                        if (afterSize > 600) {
                            manaBar.setSize(600, 20);
                        } else {
                            manaBar.setSize(currentMana, 20);
                        }
                    }
                    int lastMana = manaBar.getWidth();
                    if (lastMana > 600) {
                        manaBar.setSize(600, 20);
                    }
                }
                if (operation.toLowerCase().equals("subtract")) {
                    int currentHealth = manaBar.getWidth();
                    currentHealth = currentHealth - someInt;
                    if (currentHealth < 0) {
                        manaBar.setSize(0, 20);
                    } else {
                        manaBar.setSize(currentHealth, 20);
                    }
                }
            case "xp":
                if (operation.toLowerCase().equals("add")) {
                    int currentXP = xpBar.getWidth();
                    currentXP = currentXP + someInt;
                    if (currentXP > 600) {
                        xpBar.setSize(600, 20);
                    } else {
                        xpBar.setSize(currentXP, 20);
                        int afterSize = xpBar.getWidth();
                        if (afterSize > 600) {
                            xpBar.setSize(600, 20);
                        } else {
                            xpBar.setSize(currentXP, 20);
                        }
                    }
                    int lastXP = xpBar.getWidth();
                    if (lastXP > 600) {
                        xpBar.setSize(600, 20);
                    }
                }
                if (operation.toLowerCase().equals("subtract")) {
                    int currentXP = xpBar.getWidth();
                    currentXP = currentXP - someInt;
                    if (currentXP < 0) {
                        xpBar.setSize(0, 20);
                    } else {
                        xpBar.setSize(currentXP, 20);
                    }
                }
            default:{
                break;
            }
        }
    }
}
