package locations.medium;

import characters.Player;
import locations.Location;
import observer.generators.EnemyGenerator;

public class BanditsCamp extends Location {

    public BanditsCamp(Player player) {
        this.id = 5;

        this.player = player;

        //generate enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);

        this.neededAttribute = "Strength";
        this.neededLvl = 6;

        this.context = "On your way you find a camp with bandits who are preparing for the raid on the village nearby.\n";
        this.firstChoice = "Left: Fight bandits before they attack village";
        this.secondChoice = "Right: Go and tell villagers about bandits who are about to attack";

        this.firstSuccessfulResolution = "The bandits were not as tough as they looked to you. Now all bandits are dead and village is safe.";
        this.firstFailResolution = "You got quite a beating but at least village is safe and now you can continue on your journey";
        this.secondResolution = "Villagers thanked you for the information and thanked with quite and comfortable place to rest.";
    }

    @Override
    public void reward(Player player) {
        player.setHealth(player.getHealth() + 20); // healing in tavern
    }

    public String firstResolution() {
        if(player.getStrength() == neededLvl || player.getTool().getAbility().equals("Strength Bonus")) return(firstSuccessfulResolution);
        else {
            this.player.setHealth(player.getHealth() - 10); // bonus beating
            this.enemies.fight(this.player);
            this.player.addExperience(this.enemies.getXpValue());
            return(firstFailResolution);
        }
    }

    public String secondResolution() {
        player.setHealth(player.getHealth() + 10);
        player.addExperience(20);
        return(secondResolution);
    }
}

