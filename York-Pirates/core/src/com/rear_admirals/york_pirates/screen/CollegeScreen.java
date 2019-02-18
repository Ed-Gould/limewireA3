package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rear_admirals.york_pirates.College;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.base.BaseScreen;
import com.rear_admirals.york_pirates.minigame.MiniGameScreen;
import com.rear_admirals.york_pirates.screen.combat.attacks.Attack;

public class CollegeScreen extends BaseScreen {
    private Player player;
    private Label sailsHealthTextLabel, sailsHealthValueLabel;
    private Label hullHealthTextLabel, hullHealthValueLabel;
    private Label goldValueLabel, goldTextLabel;
    private Label pointsValueLabel, pointsTextLabel;
    private int hullHealthFromMax, sailsHealthFromMax;

    public CollegeScreen(PirateGame main, College college){
        super(main);
        this.player = main.getPlayer();
        // Get the amount of health required to heal to maximum
        this.sailsHealthFromMax = player.getPlayerShip().getSailsHealthFromMax();
        this.hullHealthFromMax = player.getPlayerShip().getHullHealthFromMax();

        Table uiTable = new Table();

        player.equippedAttacks.remove(Attack.attackNone);

        /* Creates labels for the health, gold, and points display.
        These displays are separated into two labels each:
        A "TextLabel": These labels are composed of a text element (either the world "Points" or "Gold")
        A "ValueLabel": These labels are the integer value associated to the Text Labels (e.g. 40 for gold)
        */

        sailsHealthTextLabel = new Label("Sails Health: ", main.getSkin());
        sailsHealthValueLabel = new Label(Integer.toString(main.getPlayer().getPlayerShip().getSailsHealth()), main.getSkin());
        sailsHealthValueLabel.setAlignment(Align.left);

        hullHealthTextLabel = new Label("Hull Health: ", main.getSkin());
        hullHealthValueLabel = new Label(Integer.toString(main.getPlayer().getPlayerShip().getHullHealth()), main.getSkin());
        hullHealthValueLabel.setAlignment(Align.left);

        goldTextLabel = new Label("Gold: ", main.getSkin());
        goldValueLabel = new Label(Integer.toString(main.getPlayer().getGold()), main.getSkin());
        goldValueLabel.setAlignment(Align.left);

        pointsTextLabel = new Label("Points: ", main.getSkin());
        pointsValueLabel = new Label(Integer.toString(main.getPlayer().getPoints()), main.getSkin());
        pointsValueLabel.setAlignment(Align.left);

        uiTable.add(sailsHealthTextLabel).fill();
        uiTable.add(sailsHealthValueLabel).fill();
        uiTable.row();
        uiTable.add(hullHealthTextLabel).fill();
        uiTable.add(hullHealthValueLabel).fill();
        uiTable.row();
        uiTable.add(goldTextLabel).fill();
        uiTable.add(goldValueLabel).fill();
        uiTable.row();
        uiTable.add(pointsTextLabel);
        uiTable.add(pointsValueLabel).width(pointsTextLabel.getWidth());

        uiTable.align(Align.topRight);
        uiTable.setFillParent(true);

        uiStage.addActor(uiTable);

        // Create and align department screen title text
        Label titleText = new Label(college.getName() + " College", main.getSkin(), "title");
        titleText.setAlignment(Align.top);
        titleText.setFillParent(true);

        // Create and align text and buttons for healing options
        Table healTable = new Table();
        healTable.setX(viewwidth * -0.35f, Align.center);
        healTable.setFillParent(true);

        // Sails and hull both have option to heal to max, or 10 health.
        final Label healText = new Label("Heal", main.getSkin(), "title");
        final TextButton healSailsFullBtn = new TextButton("Fully heal ship sails for "+ Integer.toString(getHealCost(sailsHealthFromMax)) +" gold", main.getSkin());
        final TextButton healHullFullBtn = new TextButton("Fully heal ship hull for "+ Integer.toString(getHealCost(hullHealthFromMax)) +" gold", main.getSkin());
        final TextButton healSailsTenBtn = new TextButton("Heal 10 sail health for 1 gold", main.getSkin());
        final TextButton healHullTenBtn = new TextButton("Heal 10 hull health for 1 gold", main.getSkin());
        final Label healMessage = new Label("status", main.getSkin());

        healTable.add(healText).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healSailsFullBtn).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healHullFullBtn).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healSailsTenBtn).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healHullTenBtn).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healMessage);

        // Create buttons used to show upgrade options
        Table shipTable = new Table();
        shipTable.align(Align.center);
        shipTable.setFillParent(true);

        final Label shipText = new Label("Ship management", main.getSkin(), "title");
        shipTable.add(shipText).padBottom(0.05f * Gdx.graphics.getHeight());
        shipTable.row();

        // Add buttons to unequip weapons
        for (final Attack attack: player.equippedAttacks){
            TextButton btn = new TextButton("Unequip: " + attack.getName(), main.getSkin());

            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    player.equippedAttacks.remove(attack);
                }
            });
            shipTable.add(btn).padBottom(viewheight/40);
            shipTable.row();
        }

        // Add buttons to equip weapons
        for (final Attack attack: player.ownedAttacks){
            if (!player.equippedAttacks.contains(attack)){
                TextButton btn = new TextButton("Equip: " + attack.getName(), main.getSkin());

                btn.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (!player.equippedAttacks.contains(attack) && player.equippedAttacks.size() < 3){
                            player.equippedAttacks.add(attack);
                        }
                    }
                });
                shipTable.add(btn).padBottom(viewheight/40);
                shipTable.row();
            }
        }

        // Create buttons used to show minigame options
        Table minigameTable = new Table();
        minigameTable.setX(viewwidth * 0.35f, Align.center);
        minigameTable.setFillParent(true);

        final Label minigameText = new Label("Minigame", main.getSkin(), "title");
        final TextButton miniGameBtn = new TextButton("Start Mini Game", main.getSkin());
        minigameTable.add(minigameText).padBottom(0.05f * Gdx.graphics.getHeight());
        minigameTable.row();
        minigameTable.add(miniGameBtn).padBottom(viewwidth/40);
        minigameTable.row();

        healSailsFullBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sailsHealthFromMax == 0){
                    healMessage.setText("Your sails are already fully repaired!");
                }
                else {
                    if (player.payGold(getHealCost(sailsHealthFromMax))) {
                        System.out.println("Charged to fully heal sails");
                        player.getPlayerShip().setSailsHealth(player.getPlayerShip().getHealthMax());
                        healMessage.setText("Ship sails fully repaired");
                    } else {
                        healMessage.setText("Not enough money to repair ship sails");
                    }
                }
            }
        });

        healHullFullBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (hullHealthFromMax == 0){
                    healMessage.setText("Your hull is already fully repaired!");
                }
                else {
                    if (player.payGold(getHealCost(hullHealthFromMax))) {
                        System.out.println("Charged to fully heal hull");
                        player.getPlayerShip().setHullHealth(player.getPlayerShip().getHealthMax());
                        healMessage.setText("Ship hull fully repaired");
                    } else {
                        healMessage.setText("Not enough money to repair ship hull");
                    }
                }
            }
        });

        healSailsTenBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sailsHealthFromMax == 0){
                    healMessage.setText("Your sails are already fully repaired!");
                }
                else {
                    if (player.payGold(getHealCost(10))) { // Pay cost to heal 10 health
                        System.out.println("Charged to heal sails by 10hp");
                        player.getPlayerShip().healSails(10);
                        healMessage.setText("10 health restored to sails");
                    } else {
                        healMessage.setText("Not enough money to repair ship sails");
                    }
                }
            }
        });

        healHullTenBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (hullHealthFromMax == 0){
                    healMessage.setText("Your hull is already fully repaired!");
                }
                else {
                    if (player.payGold(getHealCost(10))) { // Pay cost to heal 10 health
                        System.out.println("Charged to heal hull by 10hp");
                        player.getPlayerShip().healHull(10);
                        healMessage.setText("10 health restored to hull");
                    } else {
                        healMessage.setText("Not enough money to repair ship hull");
                    }
                }
            }
        });

        miniGameBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pirateGame.setScreen(new MiniGameScreen(pirateGame));
            }
        });

        mainStage.addActor(healTable);
        mainStage.addActor(shipTable);
        mainStage.addActor(minigameTable);
        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.out.println("ESCAPE");
            pirateGame.setScreen(pirateGame.getSailingScene());
            dispose();
        }

        goldValueLabel.setText(Integer.toString(pirateGame.getPlayer().getGold()));
        pointsValueLabel.setText(Integer.toString(pirateGame.getPlayer().getPoints()));
        sailsHealthValueLabel.setText(Integer.toString(player.getPlayerShip().getSailsHealth()));
        hullHealthValueLabel.setText(Integer.toString(player.getPlayerShip().getHullHealth()));
        sailsHealthFromMax = player.getPlayerShip().getHealthMax() - player.getPlayerShip().getSailsHealth();
        hullHealthFromMax = player.getPlayerShip().getHealthMax() - player.getPlayerShip().getHullHealth();
    }

    // Function to get the cost to heal sails to full:
    public int getHealCost(int value) {
        // if statement ensures player pays at least 1 gold to heal
        if (value / 10 == 0) {
            return 1;
        }
        // Formula for cost: Every 10 health costs 1 gold to heal
        return value / 10;
    }
}
