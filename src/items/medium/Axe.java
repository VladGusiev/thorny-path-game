package items.medium;

import characters.Character;
import items.Item;

public class Axe extends Item {
    public Axe() {
        this.name = "Axe";
        this.damage = 10;
        this.ability = "Strength Bonus";
    }

    @Override
    public void attack(Character foe) {
        damageScale(2);
        foe.setHealth(foe.getHealth() - this.damage);
    }
}
