package items;

import characters.Character;

import java.io.Serializable;
import static java.lang.Math.ceil;

/**
 * Abstract class, with basic structure which all weapons share
 */
public abstract class Item implements Serializable {
    protected String name;
    protected int damage;
    protected String ability;
    public abstract void attack(Character foe);

    public void damageScale(int stat) {
        this.damage += stat;
    }
    public int getDamage() {return damage;}
    public String getAbility() {return this.ability;}
    public String getName() {return this.name;}
}
