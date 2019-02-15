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

public class CollegeScreen extends BaseScreen {
    private Player player;
    private Label healthTextLabel, healthValueLabel;
    private Label goldValueLabel, goldTextLabel;
    private Label pointsValueLabel, pointsTextLabel;
    private int healthFromMax;

    public CollegeScreen(PirateGame main, College college){
        super(main);
        this.player = main.getPlayer();
        // Get the amount of health required to heal to maximum
        this.healthFromMax = player.getPlayerShip().getHealthFromMax();

        Table uiTable = new Table();

        /* Creates labels for the health, gold, and points display.
        These displays are separated into two labels each:
        A "TextLabel": These labels are composed of a text element (either the world "Points" or "Gold")
        A "ValueLabel": These labels are the integer value associated to the Text Labels (e.g. 40 for gold)
        */

        healthTextLabel = new Label("Health: ", main.getSkin());
        healthValueLabel = new Label(Integer.toString(main.getPlayer().getPlayerShip().getHealth()), main.getSkin());
        healthValueLabel.setAlignment(Align.left);

        goldTextLabel = new Label("Gold: ", main.getSkin());
        goldValueLabel = new Label(Integer.toString(main.getPlayer().getGold()), main.getSkin());
        goldValueLabel.setAlignment(Align.left);

        pointsTextLabel = new Label("Points: ", main.getSkin());
        pointsValueLabel = new Label(Integer.toString(main.getPlayer().getPoints()), main.getSkin());
        pointsValueLabel.setAlignment(Align.left);

        uiTable.add(healthTextLabel).fill();
        uiTable.add(healthValueLabel).fill();
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
        healTable.setX(viewwidth * -0.2f, Align.center);
        healTable.setFillParent(true);

        final Label healText = new Label("Heal", main.getSkin(), "title");
        final TextButton healFullBtn = new TextButton("Fully heal ship for "+ Integer.toString(getHealCost(healthFromMax)) +" gold", main.getSkin());
        final TextButton healTenBtn = new TextButton("Heal 10 health for 1 gold", main.getSkin());
        final Label healMessage = new Label("status", main.getSkin());

        healTable.add(healText).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healFullBtn).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healTenBtn).padBottom(viewheight/40);
        healTable.row();
        healTable.add(healMessage);

        // Create buttons used to show upgrade options
        Table shipTable = new Table();
        shipTable.setX(viewwidth * 0.2f, Align.center);
        shipTable.setFillParent(true);

        final Label shipText = new Label("Ship management", main.getSkin(), "title");
        shipTable.add(shipText).padBottom(0.05f * Gdx.graphics.getHeight());
        shipTable.row();

        if (healthFromMax == 0) { healMessage.setText("Your ship is already fully repaired!"); }

        healFullBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (healthFromMax == 0){
                    healMessage.setText("Your ship is already fully repaired!");
                }
                else {
                    if (player.payGold(getHealCost(healthFromMax))) {
                        System.out.println("Charged to fully heal");
                        player.getPlayerShip().setHealth(player.getPlayerShip().getHealthMax());
                        healMessage.setText("Ship fully repaired");
                    } else {
                        healMessage.setText("Not enough money to repair ship");
                    }
                }
            }
        });

        healTenBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (healthFromMax == 0){
                    healMessage.setText("Your ship is already fully repaired!");
                }
                else {
                    if (player.payGold(getHealCost(10))) { // Pay cost to heal 10 health
                        System.out.println("Charged to heal 10hp");
                        player.getPlayerShip().heal(10);
                        healMessage.setText("10 health restored");
                    } else {
                        healMessage.setText("Not enough money to repair ship");
                    }
                }
            }
        });

        mainStage.addActor(healTable);
        mainStage.addActor(shipTable);
        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.out.println("ESCAPE");
            pirateGame.setScreen(pirateGame.getSailingScene());
            dispose();
        }

        healthFromMax = player.getPlayerShip().getHealthMax() - player.getPlayerShip().getHealth();
        goldValueLabel.setText(Integer.toString(pirateGame.getPlayer().getGold()));
        pointsValueLabel.setText(Integer.toString(pirateGame.getPlayer().getPoints()));
    }

    public int getHealCost(int value){ // Function to get the cost to heal to full:
        // if statement ensures player pays at least 1 gold to heal
        if (healthFromMax / 10 == 0){
            return 1;
        }
        // Formula for cost: Every 10 health costs 1 gold to heal
        return healthFromMax / 10;
    }
}
