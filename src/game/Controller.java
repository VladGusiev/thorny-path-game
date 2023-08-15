package game;

import animations.SpriteAnimation;
import characters.Player;
import items.Item;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import locations.easy.BrokenWagon;
import locations.easy.HuntingInForest;
import locations.Location;
import locations.easy.OldBridge;
import locations.elite.CastleCorridor;
import locations.elite.KingsRoom;
import locations.medium.Ambush;
import locations.medium.Arena;
import locations.medium.BanditsCamp;
import locations.medium.WaterFall;
import observer.generators.EnemyGenerator;
import observer.generators.WeaponGenerator;


import java.io.*;
import java.util.Objects;

import static java.lang.System.exit;

/**
 * Decides what to show on screen based user input and output of game logic (Controller in MVC)
 */
public class Controller {

    @FXML
    private TextArea PlayerHealth;

    @FXML
    private Button agiBtn;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button exit;

    @FXML
    private Button intBtn;

    @FXML
    private TextArea mainText;

    @FXML
    private Button nextLevel;

    @FXML
    private Button strBtn;

    @FXML
    private ImageView imageFrame;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private Button load;

    @FXML
    private Button save;

    @FXML
    private TextArea enemiesList;


    Player player;
    Location currentLevel;

    //animation
    private Animation animation;
    private final Image IDLE_SPRITE = new Image("./animations./sprites./_Idle.png");
    private final Image RUN_SPRITE = new Image("./animations./sprites./_Run.png");
    private final Image ATTACK_SPRITE = new Image("./animations./sprites./_AttackCombo.png");
    private final Image DEATH_SPRITE = new Image("./animations./sprites./_Death.png");

    public Controller() {
        this.player = new Player();
    }

    /**
     * Needed for loading information from saved file. Second Part of Serialization after save
     * @throws IOException
     * @throws ClassNotFoundException
     */
    // --- SERIALIZATION ---
    private void load() throws IOException, ClassNotFoundException {
        // --- Player Part ---
        ObjectInputStream playerFile = new ObjectInputStream(new FileInputStream(player.getClass().getSimpleName()+".out"));
        Player loadedPlayer = (Player) playerFile.readObject();
        playerFile.close();

        player = loadedPlayer;
        //removing old instances
        player.removeObserver(WeaponGenerator.getInstance());
        player.removeObserver(EnemyGenerator.getInstance());

        // --- Weapon Generator Part ---
        ObjectInputStream weaponGeneratorFile = new ObjectInputStream(new FileInputStream(WeaponGenerator.getInstance().getClass().getSimpleName()+".out"));
        WeaponGenerator loadedWeaponGenerator = (WeaponGenerator) weaponGeneratorFile.readObject();
        weaponGeneratorFile.close();

        WeaponGenerator.setInstance(loadedWeaponGenerator);

        // --- Enemy Generator Part ---
        ObjectInputStream enemyGeneratorFile = new ObjectInputStream(new FileInputStream(EnemyGenerator.getInstance().getClass().getSimpleName()+".out"));
        EnemyGenerator loadedEnemyGenerator = (EnemyGenerator) enemyGeneratorFile.readObject();
        enemyGeneratorFile.close();

        EnemyGenerator.setInstance(loadedEnemyGenerator);

        //adding new generators to Player
        player.addObserver(WeaponGenerator.getInstance());
        player.addObserver(EnemyGenerator.getInstance());
    }

    /**
     * Update screen, so user know that save/load was successful
     * @param buttonName
     */
    private void gameSavedLoaded(String buttonName) {
        mainText.setText("Game has been " + buttonName + "!");
        btn1.setVisible(false);
        btn2.setVisible(false);
        nextLevel.setVisible(true);
        nextLevel.setOnAction(f -> {
            mainMenu();
        });
    }


    // --- ANIMATIONS PRESETS ---
    private void playAnimation() {
        animation.stop();
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    private void playIdleAnimation() {
        imageFrame.setImage(IDLE_SPRITE);
        playAnimation();
    }

    private void playRunAnimation() {
        imageFrame.setImage(RUN_SPRITE);
        playAnimation();
    }

    private void playAttackAnimation() {
        imageFrame.setImage(ATTACK_SPRITE);
        playAnimation();

    }
    private void playDeathAnimation() {
        imageFrame.setImage(DEATH_SPRITE);
        animation.stop();
        animation.setCycleCount(1);
        animation.play();
    }

    /**
     * update player health bar which is located right under player sprite
     */
    private void updatePlayerHealth() {
        healthBar.setProgress(player.getHealth() / 100.0f);
    }

    /**
     * updating player stats on the screen after event that might change them.
     */
    private void updatePlayerStats(Player player) {
        PlayerHealth.setText("Health: " + player.getHealth().toString() +
                "\nXP: " + player.getXpCurrent() + " / " + player.getXpNeededForNextLevel() +
                "\nStrength: " + player.getStrength() +
                "\nIntelligence: " + player.getIntelligence() +
                "\nAgility: " + player.getAgility() +
                "\nWeapon: " + player.getToolName());
                updatePlayerHealth();
    }

    private void startingText() {
        mainText.setText("Where do you want to go?");
    }

    /**
     * According to player level, generates level where player will be put
     */
    void levelChoosing() {
        if(player.getLevel() == 1) {
            int randLevelID = (int)(Math.random()*(3-1+1)+1);
            switch (randLevelID) {
                case 1 -> currentLevel = new BrokenWagon(this.player);
                case 2 -> currentLevel = new OldBridge(this.player);
                case 3 -> currentLevel = new HuntingInForest(this.player);
            }
        } else if (player.getLevel() == 2) {
            int randLevelID = (int)(Math.random()*(4-1+1)+1);
            switch (randLevelID) {
                case 1 -> currentLevel = new Ambush(this.player);
                case 2 -> currentLevel = new Arena(this.player);
                case 3 -> currentLevel = new BanditsCamp(this.player);
                case 4 -> currentLevel = new WaterFall(this.player);
            }
        } else {
            int randLevelID = (int)(Math.random()*(3-1+1)+1);
            switch (randLevelID) {
                case 1, 2 -> currentLevel = new CastleCorridor(this.player);
                case 3 -> currentLevel = new KingsRoom(this.player);
            }
        }
        playRunAnimation();
    }

    /**
     * Here player chooses what to do in the level
     */
    private void playLevel() {
        updatePlayerStats(this.player);
            updatePlayerStats(this.player);
            mainText.setText(currentLevel.getContext() + "\n" +
                    currentLevel.getFirstChoice() + "\n" +
                    currentLevel.getSecondChoice()
            );

            btn1.setOnAction(e -> {
                mainText.setText(currentLevel.firstResolution());
                resolution();
            });


            btn2.setOnAction(e -> {
                mainText.setText(currentLevel.secondResolution());
                resolution();
            });

        enemiesList.setText(currentLevel.getEnemies().getNames());
    }

    /**
     * Outcome of player decisions
     */
    private void resolution() {
        updatePlayerStats(this.player);
        if(player.getHealth() <= 0) { //player died
            btn1.setVisible(false);
            btn2.setVisible(false);
            nextLevel.setVisible(false);
            mainText.setText("Your Journey ends here");
            playDeathAnimation();
        } else{                      // player is alive
            if(currentLevel.getID() == 9) { // final level
                btn1.setVisible(false);
                btn2.setVisible(false);
                nextLevel.setVisible(false);
                mainText.setText("Congratulations on finishing the game! Thanks for playing!");
            } else {
                btn1.setVisible(false);
                btn2.setVisible(false);
                nextLevel.setVisible(true);
                nextLevel.setOnAction(f -> {
                    enemiesList.clear();
                    playerLevelingUp();
                });
            }
            playAttackAnimation();
        }
    }

    /**
     * Checks if player achived new level after completing previous location
     */
    void playerLevelingUp() {
        if(this.player.getHasLeveledUP()) {
            btn1.setVisible(false);
            btn2.setVisible(false);
            nextLevel.setVisible(false);
            strBtn.setVisible(true);
            intBtn.setVisible(true);
            agiBtn.setVisible(true);

            mainText.setText("You have reached new level!" +
                    "\n What do you want to upgrade?"
            );

            strBtn.setOnAction(e -> {
                this.player.setStrength(this.player.getStrength() + 1);
                this.player.setHasLeveledUP(false);
                weaponReward();
            });


            intBtn.setOnAction(e -> {
                this.player.setIntelligence(this.player.getIntelligence() + 1);
                this.player.setHasLeveledUP(false);
                weaponReward();
            });

            agiBtn.setOnAction(e -> {
                this.player.setAgility(this.player.getAgility() + 1);
                this.player.setHasLeveledUP(false);
                weaponReward();
            });
            playIdleAnimation();
        } else {
            weaponReward();
        }
    }

    /**
     * After location is completed player is awarded with new weapon which is randomly generated by
     * @WeaponGenerator class
     */
    private void weaponReward() {
        Item newWeapon =  WeaponGenerator.getInstance().generateWeapon();
        strBtn.setVisible(false);
        intBtn.setVisible(false);
        agiBtn.setVisible(false);
        nextLevel.setVisible(false);

        mainText.setText("After the events you found a weapon dropped on the ground: " + newWeapon.getName() + ". Do you want to pick it up?\nLeft: Yes\nRight: No");

        btn1.setVisible(true);
        btn2.setVisible(true);

        btn1.setOnAction(e -> {
            player.setTool(newWeapon);
            mainMenu();
        });

        btn2.setOnAction(e -> {
            mainMenu();
        });
    }


    /**
     * First screen to welcome player, there player chooses where to go
     */
    private void mainMenu() {
        strBtn.setVisible(false);
        intBtn.setVisible(false);
        agiBtn.setVisible(false);


        updatePlayerStats(this.player);
        startingText();

        btn1.setVisible(true);
        btn2.setVisible(true);

        btn2.setOnAction(e -> {
            levelChoosing();
            playLevel();
        });

        btn1.setOnAction(e -> {
            levelChoosing();
            playLevel();
        });

        nextLevel.setVisible(false);

        exit.setOnAction(e -> {
            exit(1);
        });

        save.setOnAction( e -> {
            player.save();
            WeaponGenerator.getInstance().save();
            EnemyGenerator.getInstance().save();
            gameSavedLoaded("saved");
        });
        load.setOnAction( e -> {
            try {
                this.load();
                gameSavedLoaded("loaded");

            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        playIdleAnimation();
    }

    void start() {
        mainMenu();
    }

    /**
     * starting of game pool + instantiating animation variable to be updated in the future
     */
    public void initialize() {
        animation = new SpriteAnimation(
                imageFrame,
                Duration.millis(1000),
                10, 10,
                0, 0,
                120, 80
        );
        start();
    }
}

