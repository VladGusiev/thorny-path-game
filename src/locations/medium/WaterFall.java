package locations.medium;

import characters.Player;
import locations.Location;
import observer.generators.EnemyGenerator;

public class WaterFall extends Location {
    public WaterFall(Player player) {
        this.id = 6;

        this.player = player;

        //generate enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);

        this.neededAttribute = "";
        this.neededLvl = 5;

        this.context = "You stumbled upon a beautiful waterfall. With plenty of space to train, What do you want to train?\n";
        this.firstChoice = "Left: Train Agility";
        this.secondChoice = "Right: Train Strength";

        this.firstSuccessfulResolution = "After a training you agility has increased";
        this.firstFailResolution = "After a training you agility has increased";
        this.secondResolution = "After a training, your strength has increased.";
    }

    @Override
    public void reward(Player player) {
        player.addExperience(20);
    }

    public String firstResolution() {
        reward(this.player);
        player.setAgility(player.getAgility() + 1);
        return(firstSuccessfulResolution);
    }

    public String secondResolution() {
        reward(this.player);
        player.setStrength(player.getStrength() + 1);
        return(secondResolution);
    }
}
