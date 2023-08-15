package items.medium;

import characters.Character;
import items.Item;

public class ArmlessMedium extends Item {
    public ArmlessMedium() {
        this.name = "Armless";
        this.damage = 8;
        this.ability = "Nothing";
    }

    @Override
    public void attack(Character foe) {
        foe.setHealth(foe.getHealth() - this.damage);
    }
}
