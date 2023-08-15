package locations.easy;

import characters.Player;
import characters.easy.Goblin;
import items.easy.ArmlessEasy;
import items.easy.Bow;
import items.easy.Dagger;
import locations.Location;
import observer.generators.EnemyGenerator;

public class HuntingInForest extends Location {
    public HuntingInForest(Player player) {
        this.id = 3;

        this.player = player;

        //generatae enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);


        this.neededAttribute= "Long Range";
        this.neededLvl = 6;

        this.context = "You come into the forest and find, that there are deer settlement. But if there is the prey, there is the hunter.\n";
        this.firstChoice = "Left: Try to hunt the deer (bow needed)";
        this.secondChoice = "Right: Leave the deer, and try to go for hunters around.";

        this.firstSuccessfulResolution = "You successfully hunted the deer and cooked it." +
                "\nYou feel restoration of your energy";
        this.firstFailResolution = "You didnt have bow or were to clumsy. Deer ran away.";
        this.secondResolution = "After scouting, you found an enemy camp. After a fight, they have fallen, and after looting you continued on your journey";
    }
    @Override
    protected void reward(Player player) {
        player.setHealth(player.getHealth() + 30); // eating Deer
        player.addExperience(15); //hunting deer
    }

    public String firstResolution() {
        if(player.getTool().getAbility().equals(neededAttribute)) {
            reward(player);
            return firstSuccessfulResolution;
        }
        else {
            return firstFailResolution;
        }
    }
    public String secondResolution() {
        this.enemies.fight(this.player);
        this.player.addExperience(enemies.getXpValue());
        return(secondResolution);
    }
}
