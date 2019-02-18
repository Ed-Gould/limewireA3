package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rear_admirals.york_pirates.*;
import com.rear_admirals.york_pirates.base.BaseScreen;

public class DepartmentScreen extends BaseScreen {
    private Player player;
    private Label sailsHealthTextLabel, sailsHealthValueLabel;
    private Label hullHealthTextLabel, hullHealthValueLabel;
    private Label goldValueLabel, goldTextLabel;
    private Label pointsValueLabel, pointsTextLabel;

    private int sailsHealthFromMax, hullHealthFromMax;

    public DepartmentScreen(final PirateGame main, final Department department){
        super(main);
        player = main.getPlayer();
        // Get the amount of health required to heal to maximum
        this.sailsHealthFromMax = player.getPlayerShip().getSailsHealthFromMax();
        this.hullHealthFromMax = player.getPlayerShip().getHullHealthFromMax();

        Table uiTable = new Table();

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
        Label titleText = new Label(department.getName() + " Department", main.getSkin(), "title");
        titleText.setAlignment(Align.top);
        titleText.setFillParent(true);

        // Create and align text and buttons for healing options
        Table healTable = new Table();
        healTable.setX(viewwidth * -0.35f, Align.center);
        healTable.setFillParent(true);

        final Label healText = new Label("Heal", main.getSkin(), "title");

        // Sails and hull both have option to heal to max, or 10 health.
        final TextButton healHullFullBtn = new TextButton("Fully heal ship hull for "+ Integer.toString(getHealCost(hullHealthFromMax)) +" gold", main.getSkin());
        final TextButton healSailsFullBtn = new TextButton("Fully heal ship sails for "+ Integer.toString(getHealCost(sailsHealthFromMax)) +" gold", main.getSkin());
        final TextButton healHullTenBtn = new TextButton("Heal 10 hull health for 1 gold", main.getSkin());
        final TextButton healSailsTenBtn = new TextButton("Heal 10 sails health for 1 gold", main.getSkin());
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
        Table upgradeTable = new Table();
        upgradeTable.align(Align.center);
        upgradeTable.setFillParent(true);

        final Label upgradeText = new Label("Upgrade", main.getSkin(), "title");
        final TextButton upgradeButton = new TextButton("Upgrade ship "+ department.getUpgrade() + " for " + department.getUpgradeCost() + " gold", main.getSkin());

        upgradeTable.add(upgradeText).padBottom(0.05f * Gdx.graphics.getHeight());
        upgradeTable.row();
        upgradeTable.add(upgradeButton);

        // Create buttons used to show shop options
        Table shopTable = new Table();
        shopTable.setX(viewwidth * 0.35f, Align.center);
        shopTable.setFillParent(true);

        final Label shopText = new Label("Shop", main.getSkin(), "title");
        final TextButton shopButton = new TextButton("Buy " + department.getWeaponToBuy().getName() + " for " + department.getWeaponToBuy().getCost() + " gold", main.getSkin());
        final Label shopMessage = new Label("", main.getSkin());

        shopTable.add(shopText).padBottom(viewheight/40);
        shopTable.row();

        // Display button to buy weapon if the player doesn't own it
        if (!player.ownedAttacks.contains(department.getWeaponToBuy())){
            shopTable.add(shopButton).padBottom(viewheight/40);
            shopTable.row();
        }
        shopTable.add(shopMessage);

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
                System.out.println(player.getPlayerShip().getSailsHealth());
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
                System.out.println(player.getPlayerShip().getHullHealth());
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
                System.out.println(player.getPlayerShip().getSailsHealth());
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
                System.out.println(player.getPlayerShip().getHullHealth());
            }
        });

        upgradeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                department.buyUpgrade();
                upgradeButton.setText("Upgrade Ship "+ department.getUpgrade() + " for " + department.getUpgradeCost() + " gold");
            }
        });

        shopButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (!pirateGame.getPlayer().ownedAttacks.contains(department.getWeaponToBuy())) {
                    if (department.buyWeapon()) {
                        shopMessage.setText(department.getWeaponToBuy().getName() + " purchased!");
                    } else {
                        shopMessage.setText("Not enough money!");
                    }
                }
            }
        });

        mainStage.addActor(titleText);
        mainStage.addActor(healTable);
        mainStage.addActor(upgradeTable);
        mainStage.addActor(shopTable);
        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.out.println("ESCAPE");
            pirateGame.setScreen(pirateGame.getSailingScene());
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



