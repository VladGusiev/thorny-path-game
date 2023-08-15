package items.elite;

import characters.Character;
import characters.elite.SkeletonChimera;
import characters.elite.SkeletonWarrior;
import items.Item;

import java.util.Objects;

import static java.lang.Math.ceil;

public class SkeletonSword extends Item {
    public SkeletonSword() {
        this.name = "Skeleton Sword";
        this.damage = 24;
        this.ability = "Bonus Skeletal Damage";
    }

    @Override
    public void attack(Character foe) {
        if(Objects.equals(foe.getClass(), new SkeletonChimera()) || Objects.equals(foe.getClass(), new SkeletonWarrior())) this.damageScale(10);
        foe.setHealth(foe.getHealth() - this.damage);
    }
}
