package locations.elite;

import characters.Player;
import characters.elite.CursedKing;
import locations.Location;
import observer.generators.EnemyGenerator;

import java.util.Objects;

public class KingsRoom extends Location {
    public KingsRoom(Player player) {
        this.id = 9;

        this.player = player;

        //generate enemies
        this.enemies.addEnemy(new CursedKing());


        this.neededAttribute = "Divine Rapier";
        this.neededLvl = 7;

        this.context = "After a loong journey, you finally find Kings room, you feel scared but now is no time to go back, what you tactic be for this fight?\n";
        this.firstChoice = "Left: Use Divine Rapier.";
        this.secondChoice = "Right: Heal yourself first.";

        this.firstSuccessfulResolution = "With might of Divine Rapier, you managed to restore health and increase you fighting capabilities!.";
        this.firstFailResolution = "You dont have Divine Rapier, so you launched into king with whatever weapon you have.";
        this.secondResolution = "After healing yourself you launched into skeleton.";
    }

    @Override
    public void reward(Player player) {
        player.setHealth(player.getHealth() + 20); // healing in tavern
    }

    public String firstResolution() {
        if(Objects.equals(player.getToolName(), neededAttribute)) {
            this.player.setHealth(this.player.getHealth()+50);
            this.player.getTool().damageScale(10);
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
