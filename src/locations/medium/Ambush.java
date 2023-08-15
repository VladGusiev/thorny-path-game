package locations.medium;

import characters.Player;
import locations.Location;
import observer.generators.EnemyGenerator;

public class Ambush extends Location {
    public Ambush(Player player) {
        this.id = 7;

        this.player = player;

        //generate enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);

        this.neededAttribute = "Intelligence";
        this.neededLvl = 6;

        this.context = "While you were continuing your jorney, you noticed, that there are some guys following you, what will your actions be?\n";
        this.firstChoice = "Left: Using specific rodes, try to git them off the tail.";
        this.secondChoice = "Right: Face them and battle.";

        this.firstSuccessfulResolution = "You easily shook them off and continued on your journey";
        this.firstFailResolution = "You thought you outsmarted them, but in reality, that was them, you got right into their hands, and the batle begun.";
        this.secondResolution = "With no fear and second thought you faced them in not very fair but battle.";
    }

    @Override
    public void reward(Player player) {
        player.setHealth(player.getHealth() + 20); // healing in tavern
    }

    public String firstResolution() {
        if(player.getAgility() == neededLvl || player.getTool().getAbility().equals("Agility Bonus")) return(firstSuccessfulResolution);
        else {
            this.player.setHealth(player.getHealth() - 20); // bonus beating
            return(firstFailResolution);
        }
    }

    public String secondResolution() {
        enemies.fight(this.player);
        player.addExperience(enemies.getXpValue());
        return(secondResolution);
    }


}

