package characters.medium;

import characters.Character;
import items.Item;
import items.easy.ArmlessEasy;
/**
 * "leaf" part of composite pattern
 */
public class Rouge extends Character {
    public Rouge() {
        this.xpValue = 17;
        this.health = 13;
        this.tool = new ArmlessEasy();
    }
    public Rouge(Item weapon) {
        this.xpValue = 20;
        this.health = 10;

        this.tool = weapon;
    }
}
