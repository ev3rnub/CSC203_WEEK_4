package org.verboseStory.model;
import org.verboseStory.ui.GameWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * This is my version of a "person" class.
 *  The Character class is the base of all other "Character" classes, Multa's, Monsters, etc.
 *
 *  Character someCharacter = new Character();
 *  someCharacter.firstName = "Dora"; || *.setFirstName("Dora); || String aName = *.getFirstName()
 *  someCharacter.lastName = "Verbose";
 *  someCharacter.someTitle = "NPC";
 *  someCharacter.someCharacterClass = "NPC";
 */

//Character class.
public class Character {
    //String firstname, lastname, title and location,
    public String firstName;
    private String location;
    private int hitPoints;
    private int manaPoints;
    private int staminaPoints;
    private int experiencePoints = 0;

    private enum Demeanor {
        CALM,
        FRIENDLY,
        RESERVED,
        CONFIDENT,
        EASYGOING,
        CHEERFUL,
        STOIC,
        CURIOUS,
        ENERGETIC,
        INTROVERTED,
        OUTGOING,
        COMPASSIONATE,
        IMPULSIVE,
        AGGRESSIVE,
        PESSIMISTIC,
        MOODY,
        ANXIOUS,
        INDIFFERENT,
        SARCASTIC,
        RUDE,
        RAGE,
    };

    private ArrayList<String> someInventory;

    // Character State
    private enum Posture {PRONE, CRAWL, CROUCH, KNEELING, SITTING, STANDING};
    private enum Locomotion {SNEAK, WALKING, MARCHING, JOGGING, RUNNING, SPRINTING};
    //default character states
    public Demeanor currentDemeanor = Demeanor.CALM;
    public Posture currentPosture = Posture.STANDING;
    public Locomotion currentLocomotion = Locomotion.WALKING;


    /**
     *Getters/Setters to access private var
     * setFirstName("Aleric")
     * String charFirstName = getFirstName();
     * output: "Aleric"
     * */
    public Character(String firstName) {
        this.firstName = firstName;
        this.someInventory = new ArrayList<String>();
        this.appendInventory("FOOD");
        this.appendInventory("FOOD");
        this.appendInventory("FOOD");
        this.appendInventory("FOOD");
        this.appendInventory("FOOD");
    }
    //firstname
    //setter
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    //getter
    public String getFirstName(){
        return firstName;
    }
    //location
    //setter
    public void setLocation(String location){
        this.location = location;
    }

    //getter
    public String getLocation(){
        return location;
    }

    // General Demeanor of the professor
    //setter
    public void setDemeanor(Demeanor demeanor) {
        this.currentDemeanor = demeanor;
    }
    //getter
    public String getDemeanor() {
        return currentDemeanor.toString();
    }

    // character hp
    /**Hit Points will be used by the AI to determine the health of a character.
     * .. int hp = someCharacter.getHitPoints();
     * .. someCharacter.setHitPoints(10)
     * */
    //get hp
    public int getHitPoints(){
        return hitPoints;
    }
    //define hp
    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }
    // character MP
    /**Mana Points will be used by the AI to restrict spell usage.
     * .. int mp = someCharacter.getManaPoints();
     * .. someCharacter.setManaPoints(100)
     * */
    public void setManaPoints(int manaPoints){
        this.manaPoints = manaPoints;
    }

    public int getManaPoints(){
        return manaPoints;
    }

    //StaminaPoints
    /**stamina Points will be used by the AI to restrict Character movements acitons, .
     * .. int mp = someCharacter.getManaPoints();
     * .. someCharacter.setManaPoints(100)
     * */
    public void setStaminaPoints(int exhaustionPoints){
        this.staminaPoints = exhaustionPoints;
    }

    public int getStaminaPoints(){
        return staminaPoints;
    }

    //posture
    /**
     * Posture and Locomotion: The characters posture and type of movement.
     *
     * Posture somePosture = getCurrentPosture()
     * somePosture.setPosture(Posture.SITTING)
     *
     * */
    public void setPosture( Posture newPosture){
        this.currentPosture = newPosture;
    }

    public Posture getCurrentPosture(){
        return currentPosture;
    }

    public void setLocomotion(Locomotion newLocomotion){
        currentLocomotion = newLocomotion;
    }

    public Locomotion getCurrentLocomotion(){
        return currentLocomotion;
    }
    //Inventory
    /**
     * An Inventory for the character. String for now, but will be a record in the future.
     * #FUTUREME: Define an Item Record class.
     *
     * Append an item to the inventory
     * someInventory.append("Necklace of the Vertonal")
     *
     * Get the inventory:
     * List someInventory = getInventory()
     *
     * Get an Item.
     * String someItem = someInventory[0];
     * */

    public void appendInventory(String someItem){
        someInventory.add(someItem);
    }

    public ArrayList<String> getInventory(){
        return someInventory;
    }

    /**
     * Character Actions; These are default character "actions" that a player has access to directly
     * talk, sleep, sit, stand, walk, run, jog, e
     * */

    public void talk(){
    }

    public void rest(int hours){
    }

    public void sit(){
        setPosture(
                Posture.SITTING
        );
    }

    public void stand(){
        setPosture(
                Posture.STANDING
        );
    }

    public void startJogging(){
        if (currentPosture != Posture.STANDING) {
            setPosture(
                    Posture.STANDING
            );
        }
        setLocomotion(
                Locomotion.JOGGING
        );
    }

    public void startRunning(){
        setLocomotion(
                Locomotion.RUNNING
        );
    }

    // eating
    /**
     * All Multa's need to eat :).
     * Placeholder for now, just prints a string that is passed to it.
     * */
    public void eat(String someFood){
        //add food calories to digestion
    }
}
