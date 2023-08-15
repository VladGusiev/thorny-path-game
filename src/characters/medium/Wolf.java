package characters.medium;

import characters.Character;
import items.medium.ArmlessMedium;
/**
 * "leaf" part of composite pattern
 */
public class Wolf extends Character {

    public Wolf() {
        this.xpValue = 15;
        this.health = 17;
        this.tool = new ArmlessMedium();
    }
}
