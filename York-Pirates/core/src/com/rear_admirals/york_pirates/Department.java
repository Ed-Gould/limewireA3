package com.rear_admirals.york_pirates;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class Department {

    private final String name;
    private String product;
    private int baseUpgradeCost;
    private PirateGame pirateGame;

    public Department(String name, String product, PirateGame pirateGame) {
        this.name = name;
        this.product = product;
        this.baseUpgradeCost = 10;
        this.pirateGame = pirateGame;
    }

    public boolean purchase() {
        if (pirateGame.getPlayer().payGold(getUpgradeCost())) {
            if (product.equals("defence")) {
                pirateGame.getPlayer().getPlayerShip().addDefence(1);
                return true;
            } else if (product.equals("attack")) {
                pirateGame.getPlayer().getPlayerShip().addAttack(1);
                return true;
            } else if (product.equals("accuracy")) {
                pirateGame.getPlayer().getPlayerShip().addAccuracy(1);
                return true;
            }
        }
        return false;
    }

    public int getUpgradeCost() {
        if (product.equals("defence")) {
            return (int) (baseUpgradeCost * pow(2, max(0, pirateGame.getPlayer().getPlayerShip().getDefence() - 3)));
        } else if (product.equals("attack")) {
            return (int) (baseUpgradeCost * pow(2, max(0, pirateGame.getPlayer().getPlayerShip().getAttack() - 3)));
        } else if (product.equals("accuracy")) {
            return (int) (baseUpgradeCost * pow(2, max(0, pirateGame.getPlayer().getPlayerShip().getAccuracy() - 3)));
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
