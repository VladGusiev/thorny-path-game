package locations;

import characters.Character;
import characters.Enemies;
import characters.Player;
import items.Item;
import observer.generators.EnemyGenerator;

import java.util.Scanner;

/**
 * This class is a situaltion in which player is put with possible solutions which is on player to take.
 */
public abstract class Location {

//    EnemyGenerator enemyGenerator = EnemyGenerator.getInstance();

    /**
    * to identify which lvl is it
    */
    protected int id;

    /**
     * For interaction with player
     */
    protected Player player;
    /**
     * For generating random set of enenmeis, which enenmies to generate controls @EnemyGenerator
     */
    protected Enemies enemies = new Enemies();

    /**
     * Contains attribute needed for easier completion of the level
     */
    protected String neededAttribute;
    /**
     * needed level of the player attribute
     */
    protected int neededLvl;

    /**
     * basic description of the situation
     */
    protected String context;
    /**
     * 1. choice, mostly needs some special weapon or stat,but better outcome
     */
    protected String firstChoice; //that will need specific stat amount
    /**
     * General choice, with stale outcome
     */
    protected String secondChoice;
    /**
     * if player have needed weapon/attribute
     */
    protected String firstSuccessfulResolution;
    protected String firstFailResolution;
    protected String secondResolution;

    /**
     * general reward for player completing the level
     * @param player
     */
    protected void reward(Player player) {
        this.player.addExperience(enemies.getXpValue());
    }

    //Encapsulation

    public String getContext() {
        return context;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public String getFirstSuccessfulResolution() {
        return firstSuccessfulResolution;
    }

    public String getFirstFailResolution() {
        return firstFailResolution;
    }

    public String getSecondResolution() {
        return secondResolution;
    }

    public abstract String firstResolution();
    public abstract String secondResolution();



    public Enemies getEnemies() {return enemies;}

    public int getID() {return this.id;}

}
