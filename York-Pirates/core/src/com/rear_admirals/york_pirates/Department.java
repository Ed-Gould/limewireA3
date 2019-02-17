package com.rear_admirals.york_pirates;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class Department {

    private final String name;
    private String product;
    private int baseUpgradeCost;
    private float increase;
    private PirateGame pirateGame;

    public Department(String name, String product, PirateGame pirateGame) {
        this.name = name;
        this.product = product;
        this.baseUpgradeCost = 10;
        this.pirateGame = pirateGame;

        if (product.equals("defence")){
            this.increase = 1;
        } else if (product.equals("attack")){
            this.increase = 0.1f;
        } else { this.increase = 0.02f; }
    }

    public boolean purchase() {
        if (pirateGame.getPlayer().payGold(getUpgradeCost())) {
            if (product.equals("defence")) {
                pirateGame.getPlayer().getPlayerShip().addDefence((int) increase);
                return true;
            } else if (product.equals("attack")) {
                pirateGame.getPlayer().getPlayerShip().addAttack(increase);
                return true;
            } else if (product.equals("accuracy")) {
                pirateGame.getPlayer().getPlayerShip().addAccuracy(increase);
                return true;
            }
        }
        return false;
    }

    public int getUpgradeCost() {
        if (product.equals("defence")) {
            return (int) (baseUpgradeCost + 10 * (pirateGame.getPlayer().getPlayerShip().getDefence() - 5));
        } else if (product.equals("attack")) {
            return (int) (baseUpgradeCost + 100 * (pirateGame.getPlayer().getPlayerShip().getAtkMultiplier() - 1.0f));
        } else if (product.equals("accuracy")) {
            return (int) (baseUpgradeCost + Math.round(500 * (pirateGame.getPlayer().getPlayerShip().getAccMultiplier() - 1.0f)));
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }
}
