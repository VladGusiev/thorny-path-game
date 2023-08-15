package characters.easy;

import characters.Character;
import items.*;
import items.easy.Bow;

/**
 * "leaf" part of composite pattern
 */
public class Goblin extends Character {

    public Goblin() {
        this.xpValue = 8;
        this.health = 10;

        this.tool = new Bow();
    }
    public Goblin(Item weapon) {
        this.xpValue = 8;
        this.health = 10;

        this.tool = weapon;
    }
}
