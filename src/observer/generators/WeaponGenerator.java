package observer.generators;

import items.Item;
import items.easy.ArmlessEasy;
import items.easy.Bow;
import items.easy.Dagger;
import items.elite.DivineRapier;
import items.elite.SkeletonSword;
import items.medium.ArmlessMedium;
import items.medium.Axe;
import items.medium.HeavyBow;
import items.medium.Katana;
import observer.Observer;
import save.Serialization;

import java.io.Serializable;


/**
 * Observer which keeps track of player level, and accordingly generates weapon drops for player
 */
public class WeaponGenerator implements Observer, Serializable, Serialization {
    private static WeaponGenerator instance;
    private int playerLevel;

    private WeaponGenerator() {
        playerLevel = 1;

    }

    //Singleton
    public static WeaponGenerator getInstance() {
        if (instance == null) {
            instance = new WeaponGenerator();
        }
        return instance;
    }
    //Singleton
    public static void setInstance(WeaponGenerator generator) {
        instance = generator;
    }

    /**
     * Decides which enemy generation to pick based on player level
     * @return Item
     */
    public Item generateWeapon() {
        switch (this.playerLevel) {
            case 1 -> {
                return levelOne();
            }
            case 2 -> {
                return levelTwo();
            }
            case 3, 4, 5, 6 -> {
                return levelFinal();
            }
        }
        return new ArmlessEasy();
    }

    //Different enemies generations
    private Item levelFinal() {
        int randWeapon = (int)(Math.random()*(3-1+1)+1);
        if(randWeapon == 1 || randWeapon == 2) return new SkeletonSword();
        else if(randWeapon == 3) return new DivineRapier();
        return new SkeletonSword();
    }

    private Item levelTwo() {
        int randWeapon = (int)(Math.random()*(4-1+1)+1);
        if(randWeapon == 1) return new ArmlessMedium();
        else if(randWeapon == 2) return new Axe();
        else if(randWeapon == 3) return new HeavyBow();
        else if(randWeapon == 4) return new Katana();
        return new ArmlessMedium();
    }

    private Item levelOne() {
        int randWeapon = (int)(Math.random()*(3-1+1)+1);
        if(randWeapon == 1) return new ArmlessEasy();
        else if(randWeapon == 2) return new Dagger();
        else if(randWeapon == 3) return new Bow();
        return new ArmlessEasy();
    }

    /**
     * "update" method from observer pattern
     * @param playerLevel
     */
    @Override
    public void update(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}