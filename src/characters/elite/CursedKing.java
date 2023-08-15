package characters.elite;

import characters.Character;
import items.elite.SkeletonKingWeapon;
import items.medium.HeavyBow;

/**
 * Final Boss of the game
 * "leaf" part of composite pattern
 *
 */
public class CursedKing extends Character {
    public CursedKing() {
        this.xpValue = 0;
        this.health = 150;

        this.tool = new SkeletonKingWeapon();
    }
}
