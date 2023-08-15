package items.medium;

import characters.Character;
import items.Item;

public class HeavyBow extends Item {
    public HeavyBow() {
        this.name = "Heavy Bow";
        this.damage = 8;
        this.ability = "Long Range";
    }

    @Override
    public void attack(Character foe) {

        foe.setHealth(foe.getHealth() - this.damage);
    }
}
