package items.easy;

import characters.Character;
import items.Item;

public class Dagger extends Item {

    public Dagger() {
        this.name = "Dagger";
        this.damage = 5;
        this.ability = "Agility Bonus";
    }

    @Override
    public void attack(Character foe) {
        foe.setHealth(foe.getHealth() - this.damage);

    }
}
