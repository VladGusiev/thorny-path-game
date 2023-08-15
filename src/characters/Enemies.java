package characters;

import java.util.ArrayList;
import java.util.List;


/**
 * Composite class, which enables to treat any combination of enemies as 1 unit
 */
public class Enemies extends Character {
    private List<Character> enemies;
    String allNames;

    public Enemies() {
        this.enemies = new ArrayList<>();
    }

    public void addEnemy(Character enemy) {
        this.enemies.add(enemy);
    }
    public void removeEnemy(Character enemy) {
        this.enemies.remove(enemy);
    }


    public void fight(Character foe) {
        for(Character enemy : enemies) {
            while(foe.getHealth() > 0 && enemy.getHealth() > 0) {
                foe.attack(enemy);
                if(enemy.getHealth() > 0) enemy.attack(foe);
            }
        }
    }

    @Override
    public int getXpValue() {
        int totalXP = 0;
        for(Character enemy : enemies) {
            totalXP += enemy.getXpValue();
        }
        return totalXP;
    }

    /**
     * needed for printing names in GUI
     */
    private void getEnemiesNames() {
        for(Character enemy: enemies) {
            if(enemy == null) continue;
            else {
                allNames += (enemy.getClass().getSimpleName() + "\n");
            }
        }
        allNames = allNames.replace("null", "");
    }
    public String getNames() {
        getEnemiesNames();
        return allNames;
    }
}
