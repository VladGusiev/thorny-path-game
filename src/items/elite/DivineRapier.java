package items.elite;

import characters.Character;
import items.Item;
import static java.lang.Math.ceil;


/**
 * Weaon needed for defeating final boss
 */
public class DivineRapier extends Item {
    public DivineRapier() {
        this.name = "Divine Rapier";
        this.damage = 25;
        this.ability = "Divine";
    }

    @Override
    public void attack(Character foe) {
        damageScale((int) ceil(this.damage * 1.5));
        foe.setHealth(foe.getHealth() - this.damage);
    }
}
