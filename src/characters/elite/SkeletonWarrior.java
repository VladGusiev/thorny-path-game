package characters.elite;

import characters.Character;
import items.Item;
import items.elite.SkeletonSword;
import items.medium.HeavyBow;
/**
 * "leaf" part of composite pattern
 */
public class SkeletonWarrior extends Character {
    public SkeletonWarrior() {
        this.xpValue = 20;
        this.health = 25;

        this.tool = new SkeletonSword();
    }
}
