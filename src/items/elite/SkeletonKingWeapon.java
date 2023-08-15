package items.elite;

import characters.Character;
import items.Item;
import static java.lang.Math.ceil;

/**
 * Weapon of the final boss
 */
public class SkeletonKingWeapon extends Item {
    public SkeletonKingWeapon() {
        this.name = "Cursed Staff";
        this.damage = 30;
        this.ability = "Strength Bonus";
    }

    @Override
    public void attack(Character foe) {
        damageScale((int) (ceil(foe.getHealth())* 0.1));
        foe.setHealth(foe.getHealth() - this.damage);
    }
}
