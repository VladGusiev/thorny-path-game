package locations.elite;

import characters.Player;
import locations.Location;
import observer.generators.EnemyGenerator;

public class CastleCorridor extends Location {
    public CastleCorridor(Player player) {
        this.id = 8;

        this.player = player;

        //generate enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);


        this.neededAttribute = "agility";
        this.neededLvl = 7;

        this.context = "After you find tower, you are now wandering through corridors in the castle trying to find the Kings Room and Divine Rapier, fighting through armies of his subordinates.\n";
        this.firstChoice = "Left: Take initiative in fight.";
        this.secondChoice = "Right: Heal yourself first.";

        this.firstSuccessfulResolution = "With initiative in your hands fight was much easier.";
        this.firstFailResolution = "It didnt end up how you imagined, so you took some extra beating.";
        this.secondResolution = "After healing yourself you pushed forward into the army of skeletons.";
    }

    @Override
    public void reward(Player player) {
        player.setHealth(player.getHealth() + 20); // healing in tavern
    }

    public String firstResolution() {
        if(player.getAgility() == neededLvl) {
            this.player.setHealth(this.player.getHealth()+20);
            enemies.fight(this.player);
            this.player.addExperience(enemies.getXpValue());
            return(firstSuccessfulResolution);
        }
        else {
            this.player.setHealth(this.player.getHealth()-10);
            this.enemies.fight(this.player);
            this.player.addExperience(this.enemies.getXpValue());
            return(firstFailResolution);
        }
    }

    public String secondResolution() {
        this.player.setHealth(this.player.getHealth()+10);
        this.enemies.fight(this.player);
        this.player.addExperience(this.enemies.getXpValue());
        return(secondResolution);
    }
}
