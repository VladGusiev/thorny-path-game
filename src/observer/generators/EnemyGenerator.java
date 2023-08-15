package observer.generators;

import characters.Character;
import characters.Enemies;
import characters.easy.Goblin;
import characters.easy.StrayDogs;
import characters.elite.SkeletonChimera;
import characters.elite.SkeletonWarrior;
import characters.medium.Archer;
import characters.medium.Rouge;
import characters.medium.Wolf;
import items.easy.ArmlessEasy;
import items.easy.Bow;
import items.easy.Dagger;
import items.medium.ArmlessMedium;
import items.medium.Axe;
import items.medium.Katana;
import observer.Observer;
import save.Serialization;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Observer which keeps track of player level, and accordingly generates enemies
 */
public class EnemyGenerator implements Observer, Serializable, Serialization {
    private static EnemyGenerator instance;
    private int playerLevel;


    private EnemyGenerator() {
        playerLevel = 1;
    }

    //Singleton
    public static EnemyGenerator getInstance() {
        if (instance == null) {
            instance = new EnemyGenerator();
        }
        return instance;
    }
    //Singleton
    public static void setInstance(EnemyGenerator generator) {
        instance = generator;
    }


    /**
     * Decides which enemy generation to pick based on player level
     * @param enemiesArr
     */
    public void generateEnemies(Enemies enemiesArr) {
        switch (this.playerLevel) {
            case 1 -> levelOne(enemiesArr);
            case 2 -> levelTwo(enemiesArr);
            case 3, 4, 5, 6 -> levelFinal(enemiesArr);
        }
    }

    //Different enemies generations
    private void levelFinal(Enemies enemiesArr) {

        int amountOfEnemies = 2;
        for(int j = 0; j < amountOfEnemies; j++) {
            int randEnemy = (int)(Math.random()*(4-1+1)+1);
            if(randEnemy == 1 || randEnemy == 2 || randEnemy == 3) enemiesArr.addEnemy(new SkeletonWarrior());
            else if(randEnemy == 4) enemiesArr.addEnemy(new SkeletonChimera());
        }
    }
    private void levelTwo(Enemies enemiesArr) {

        int amountOfEnemies = 3;
        for (int j = 0; j < amountOfEnemies; j++) {
            int randEnemy = (int) (Math.random() * (14 - 1 + 1) + 1);
            if (randEnemy == 1 || randEnemy == 2 || randEnemy == 3 || randEnemy == 4)
                enemiesArr.addEnemy(new Rouge(new ArmlessMedium()));
            else if (randEnemy == 5 || randEnemy == 6) enemiesArr.addEnemy(new Archer());
            else if (randEnemy == 7 || randEnemy == 8)
                enemiesArr.addEnemy(new Rouge(new Axe()));
            else if (randEnemy == 9 || randEnemy == 10)
                enemiesArr.addEnemy(new Rouge(new Katana()));
            else if (randEnemy == 11) enemiesArr.addEnemy(new Goblin(new Bow()));
            else if (randEnemy == 12 || randEnemy == 13 || randEnemy == 14) enemiesArr.addEnemy(new Wolf());
        }
    }

    private void levelOne(Enemies enemiesArr) {

        //amount of generated Enemies
        int amountOfEnemies = 3;
        for(int j = 0; j < amountOfEnemies; j++) {
            int randEnemy = (int)(Math.random()*(10-1+1)+1);
            if(randEnemy == 1 || randEnemy == 2 || randEnemy == 3 || randEnemy == 4) enemiesArr.addEnemy(new Goblin(new ArmlessEasy()));
            else if(randEnemy == 5 || randEnemy == 6) enemiesArr.addEnemy(new Goblin(new Dagger()));
            else if(randEnemy == 7) enemiesArr.addEnemy(new Goblin(new Bow()));
            else if(randEnemy == 8 || randEnemy == 9 || randEnemy == 10) enemiesArr.addEnemy(new StrayDogs());
        }
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
