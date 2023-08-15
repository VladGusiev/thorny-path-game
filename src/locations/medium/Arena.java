package locations.medium;

import characters.Player;
import locations.Location;
import observer.generators.EnemyGenerator;

public class Arena extends Location {
    public Arena(Player player) {
        this.id = 4;

        this.player = player;

        //generate enemies
        EnemyGenerator.getInstance().generateEnemies(this.enemies);

        this.neededAttribute = "agility";
        this.neededLvl = 6;

        this.context = "A random guy with some kinda bodyguards found you and said that he saw you fighting. Saying you are perfect for arena and can earn some good money.\n";
        this.firstChoice = "Left: Thank for the offer, and leave";
        this.secondChoice = "Right: Go and try yourself at the arena";

        this.firstSuccessfulResolution = "They tried to stop you but you are skilled enough to leave safely.";
        this.firstFailResolution = "They beated you for refusing their offer, and left on the ground.";
        this.secondResolution = "After a long 3 cycles at the arena, you succeeded.";
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
