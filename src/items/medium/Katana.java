package items.medium;

import characters.Character;
import items.Item;
import static java.lang.Math.ceil;

public class Katana extends Item {
    public Katana() {
        this.name = "Katana";
        this.damage = 8;
        this.ability = "Bleeding";
    }

    @Override
    public void attack(Character foe) {
        foe.setHealth((int) ceil(foe.getHealth() - (this.damage + (foe.getHealth() * 0.1f))));
    }
}
