package characters.easy;

import characters.Character;
import items.easy.ArmlessEasy;

/**
 * "leaf" part of composite pattern
 */
public class StrayDogs extends Character {

    public StrayDogs() {
        this.xpValue = 5;
        this.health = 7;
        this.tool = new ArmlessEasy();
    }
}
