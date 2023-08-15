package characters.medium;

import characters.Character;
import items.medium.HeavyBow;
/**
 * "leaf" part of composite pattern
 */
public class Archer extends Character {
    public Archer() {
        this.xpValue = 16;
        this.health = 15;

        this.tool = new HeavyBow();
    }
}
