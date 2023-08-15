package characters;

import items.Item;

import java.io.Serializable;

/**
 * Abstract Clas needed for creation of all enemies and the player with basic functionality.
 * "Component part of composite pattern"
 */
public abstract class Character implements Serializable {
    public Item tool;
    public int health;

    public int xpValue;

    public int getXpValue() { return xpValue; }
    public Integer getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(Character foe) {this.tool.attack(foe);}
}

