package com.rear_admirals.york_pirates;

import com.rear_admirals.york_pirates.screen.combat.attacks.Attack;
import com.rear_admirals.york_pirates.screen.combat.attacks.ExplosiveShell;

import static java.lang.Math.max;

public class Department {

    private final String name;
    private String upgrade;
    private Attack weaponToBuy;
    private int baseUpgradeCost;
    private float increase;
    private PirateGame pirateGame;

    public Department(String name, String upgrade, Attack weaponToBuy, PirateGame pirateGame) {
        this.name = name;
        this.upgrade = upgrade;
        this.weaponToBuy = weaponToBuy;
        this.baseUpgradeCost = 10;
        this.pirateGame = pirateGame;

        if (upgrade.equals("defence")){
            this.increase = 1;
        } else if (upgrade.equals("attack")){
            this.increase = 0.1f;
        } else { this.increase = 0.02f; }
    }

    public boolean buyUpgrade() {
        if (pirateGame.getPlayer().payGold(getUpgradeCost())) {
            if (upgrade.equals("defence")) {
                pirateGame.getPlayer().getPlayerShip().addDefence((int) increase);
                return true;
            } else if (upgrade.equals("attack")) {
                pirateGame.getPlayer().getPlayerShip().addAttack(increase);
                return true;
            } else if (upgrade.equals("accuracy")) {
                pirateGame.getPlayer().getPlayerShip().addAccuracy(increase);
                return true;
            }
        }
        return false;
    }

    public boolean buyWeapon(){
        if (pirateGame.getPlayer().payGold(weaponToBuy.getCost())){
            pirateGame.getPlayer().ownedAttacks.add(weaponToBuy);
            return true;
        }
        return false;
    }

    public int getUpgradeCost() {
        if (upgrade.equals("defence")) {
            return (int) (baseUpgradeCost + 10 * (pirateGame.getPlayer().getPlayerShip().getDefence() - 5));
        } else if (upgrade.equals("attack")) {
            return (int) (baseUpgradeCost + 100 * (pirateGame.getPlayer().getPlayerShip().getAtkMultiplier() - 1.0f));
        } else if (upgrade.equals("accuracy")) {
            return (int) (baseUpgradeCost + Math.round(500 * (pirateGame.getPlayer().getPlayerShip().getAccMultiplier() - 1.0f)));
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public Attack getWeaponToBuy() {
        return weaponToBuy;
    }

    public String getUpgrade() {
        return upgrade;
    }
}
