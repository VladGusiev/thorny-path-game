package characters;
import items.Item;
import items.easy.ArmlessEasy;
import items.easy.Dagger;
import items.elite.DivineRapier;
import observer.Observer;
import observer.generators.EnemyGenerator;
import observer.generators.WeaponGenerator;
import save.Serialization;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player whose actions user control
 */
public class Player extends Character implements Serialization, Serializable {

    ArrayList<Observer> observerCollection;
    private int level;
    private int xpCurrent;
    private int xpNeededForNextLevel;
    boolean hasLeveledUP;
    int strength;
    int intelligence;
    int agility;

    public Player() {
        //list of observers
        this.observerCollection = new ArrayList<>();
        //adding observers
        addObserver(EnemyGenerator.getInstance());
        addObserver(WeaponGenerator.getInstance());


        this.health = 100;

        this.xpCurrent = 0;
        this.xpNeededForNextLevel = 50;
        this.hasLeveledUP = false;
        this.level = 1;


        this.strength = 5;
        this.intelligence = 5;
        this.agility = 5;

        this.tool = new Dagger();
    }

    /**
     * after fight, player will be granted Experience through this function
     * @param amount
     */
    public void addExperience(int amount) {
        this.xpCurrent += amount;
        if(xpCurrent >= xpNeededForNextLevel){
            levelUP();
        } else this.hasLeveledUP = false;
    }

    /**
     * If player has enough Experience, he will be granted next level
     * After that, all observers are notified to update what kinf od enenmies player can fight, and what type of weapon player can be granted.
     */
    private void levelUP() {
        xpCurrent = xpCurrent - xpNeededForNextLevel;
        xpNeededForNextLevel += 50;
        this.level += 1;
        hasLeveledUP = true;
        notifyObservers();
    }

    // Encapsulations
    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }


    public int getAgility() {
        return agility;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public boolean getHasLeveledUP() {
        return hasLeveledUP;
    }

    public int getXpCurrent() {
        return this.xpCurrent;
    }

    public String getToolName() {return this.tool.getName();}
    public Item getTool() {return this.tool;}
    public void setTool(Item tool) {this.tool = tool;}

    public int getXpNeededForNextLevel() {
        return this.xpNeededForNextLevel;
    }
    public void setHasLeveledUP(boolean hasLeveledUP) {
        this.hasLeveledUP = hasLeveledUP;
    }

    public void setXpCurrent(int xpCurrent) {
        this.xpCurrent = xpCurrent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXpNeededForNextLevel(int xpNeededForNextLevel) {
        this.xpNeededForNextLevel = xpNeededForNextLevel;
    }

    // for observers
    public void addObserver(Observer observer) {
        this.observerCollection.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observerCollection.remove(observer);
    }

    public void notifyObservers() {
        for(Observer observer : observerCollection) {
            observer.update(this.level);
        }
    }
}
