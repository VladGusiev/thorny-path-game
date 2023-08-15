package locations.easy;

import characters.*;
import characters.Character;
import characters.easy.Goblin;
import locations.Location;
import observer.generators.EnemyGenerator;

public class BrokenWagon extends Location {
    public BrokenWagon(Player player) {
        this.id = 1;

        this.player = player;

        //generate enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);

        this.neededAttribute= "agility";
        this.neededLvl = 6;

        this.context = "You come into the forest and find broken wagon with dead bodies around and enemies looting, what would you do?\n";
        this.firstChoice = "Left: Try to silently pass through (agility needed)";
        this.secondChoice = "Right: Give enemies foes what they deserve";

        this.firstSuccessfulResolution = "You successfully sneaked around the wagon and went further.";
        this.firstFailResolution = "The enemies heard you trying to sneak and you were forced to fight.";
        this.secondResolution = "After brutal fight, all foes are dead and you have increased you fighting abilities";
    }
    public String firstResolution() {
        if(this.player.getAgility() == neededLvl || player.getTool().getAbility().equals("Agility Bonus")) {
            reward(player);
            return firstSuccessfulResolution;
        }
        else {
            enemies.fight(this.player);
            return firstFailResolution;
        }
    }
    public String secondResolution() {
        this.enemies.fight(this.player);
        this.reward(player);
        return(secondResolution);
    }
}
