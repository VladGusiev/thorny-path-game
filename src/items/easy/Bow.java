package items.easy;

import characters.Character;
import items.Item;

public class Bow extends Item {

    public Bow() {
        this.name = "Bow";
        this.damage = 6;
        this.ability = "Long Range";
    }

    @Override
    public void attack(Character foe) {
        this.damageScale(1);
        foe.setHealth(foe.getHealth() - this.damage);
    }
}
