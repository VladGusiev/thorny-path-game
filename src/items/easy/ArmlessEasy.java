package items.easy;

import characters.Character;
import items.Item;

public class ArmlessEasy extends Item {
    public ArmlessEasy() {
        this.name = "Armless";
        this.damage = 3;
        this.ability = "Nothing";
    }

    @Override
    public void attack(Character foe) {
        foe.setHealth(foe.getHealth() - this.damage);
    }
}
