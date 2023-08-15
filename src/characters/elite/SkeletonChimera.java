package characters.elite;

import characters.Character;
import items.Item;
import items.elite.SkeletonSword;
import items.medium.HeavyBow;
/**
 * "leaf" part of composite pattern
 */
public class SkeletonChimera extends Character {
    public SkeletonChimera() {
        this.xpValue = 25;
        this.health = 30;

        this.tool = new SkeletonSword();
    }
}
