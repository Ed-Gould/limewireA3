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
    private Label pointsLabel;
    private Label goldLabel;
    private int toHeal;

    public DepartmentScreen(final PirateGame main, final Department department){
        super(main);
        player = main.getPlayer();

        Table uiTable = new Table();

        Label pointsTextLabel = new Label("Points: ", main.getSkin());
        pointsLabel = new Label(Integer.toString(main.getPlayer().getPoints()), main.getSkin());
        pointsLabel.setAlignment(Align.left);

        Label goldTextLabel = new Label("Gold:", main.getSkin());
        goldLabel = new Label(Integer.toString(main.getPlayer().getGold()), main.getSkin());
        goldLabel.setAlignment(Align.left);

        uiTable.add(pointsTextLabel);
        uiTable.add(pointsLabel).width(pointsTextLabel.getWidth());
        uiTable.row();
        uiTable.add(goldTextLabel).fill();
        uiTable.add(goldLabel).fill();

        uiTable.align(Align.topRight);
        uiTable.setFillParent(true);

        uiStage.addActor(uiTable);

        Table optionsTable = new Table();
        optionsTable.setFillParent(true);
        Label title = new Label(department.getName(), main.getSkin());

        final TextButton upgrade = new TextButton("Upgrade Ship "+ department.getProduct() + " for " + department.getPrice() + " gold", main.getSkin());
        final Label message = new Label("", main.getSkin());
        this.toHeal = player.getPlayerShip().getHealthMax() - player.getPlayerShip().getHealth();

        final TextButton heal = new TextButton("Repair Ship for "+ Integer.toString(toHeal/10) +" gold", main.getSkin());
        upgrade.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                department.purchase();
                upgrade.setText("Upgrade Ship "+ department.getProduct() + " for " + department.getPrice() + " gold");
            }
        });

        if (toHeal == 0) { heal.setText("Your ship is already fully repaired!"); }

        heal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (toHeal==0){
                    heal.setText("Your ship is already fully repaired!");
                }
                else {
                    if (player.payGold(toHeal / 10)) {
                        System.out.println("charged");
                        player.getPlayerShip().setHealth(player.getPlayerShip().getHealthMax());
                        message.setText("Successful repair");
                    } else {
                        message.setText("You don't have the funds to repair your ship");
                    }
                }
            }
        });

        optionsTable.add(title);
        optionsTable.row();
        optionsTable.add(upgrade);
        optionsTable.row();
        optionsTable.add(heal);

        mainStage.addActor(optionsTable);
        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.out.println("ESCAPE");
            pirateGame.setScreen(pirateGame.getSailingScene());
        }

        goldLabel.setText(Integer.toString(pirateGame.getPlayer().getGold()));
        pointsLabel.setText(Integer.toString(pirateGame.getPlayer().getPoints()));
        toHeal = player.getPlayerShip().getHealthMax() - player.getPlayerShip().getHealth();

    }
}



