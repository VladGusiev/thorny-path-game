package locations.easy;

import characters.Player;
import locations.Location;
import observer.generators.EnemyGenerator;

public class OldBridge extends Location {
    public OldBridge(Player player) {
        this.id = 2;

        this.player = player;

        //generate enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);


        this.neededAttribute = "agility";
        this.neededLvl = 6;

        this.context = "You walk upon old bridge which looks like its gonna fall apart any second, you are hungry and see on the other way a tavern where you can rest. What would you do?\n";
        this.firstChoice = "Try to go through (agility needed)";
        this.secondChoice = "Go other way(due to hunger you will lose health)";

        this.firstSuccessfulResolution = "You crossed the bridge successfully, even though you hear how its sous like its gonna break any second ";
        this.firstFailResolution = "Bridge collapsed, and you fall to the pit, where you are attacked by stray dogs";
        this.secondResolution = "Even though,you are hungry. You chose, the safe path to the tavern.";
    }

    @Override
    public void reward(Player player) {
        player.setHealth(player.getHealth() + 20); // healing in tavern
    }

    public String firstResolution() {
        if(player.getAgility() == neededLvl || player.getTool().getAbility().equals("Agility Bonus")) {
            player.addExperience(10);
            return(firstSuccessfulResolution);
        }
        else {
            this.enemies.fight(this.player);
            this.player.addExperience(this.enemies.getXpValue());
            return(firstFailResolution);
        }
    }

    public String secondResolution() {
        player.setHealth(player.getHealth() - 10); // hunger
        return(secondResolution);
    }
}
