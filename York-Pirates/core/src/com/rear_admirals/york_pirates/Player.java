package com.rear_admirals.york_pirates;

import com.rear_admirals.york_pirates.screen.combat.attacks.*;
import com.rear_admirals.york_pirates.screen.combat.attacks.GrapeShot;

import java.util.ArrayList;
import java.util.List;

import static com.rear_admirals.york_pirates.College.*;
import static com.rear_admirals.york_pirates.ShipType.*;

public class Player {
    private Ship playerShip;
    private int gold;
    private int points;
    public static List<Attack> attacks = new ArrayList<Attack>();

    public Player() {
	    this.playerShip = new Ship(Brig, "Your Ship", Derwent);
        this.gold = 0;
        this.points = 0;

        attacks.add(Ram.attackRam);
        attacks.add(GrapeShot.attackSwivel);
        attacks.add(Attack.attackBoard);
    }

    public Player(Ship ship) {
        this.playerShip = ship;
        this.gold = 0;
        this.points = 0;

        attacks.add(Ram.attackRam);
        attacks.add(Attack.attackSwivel);
        attacks.add(Attack.attackBoard);
    }

    public Ship getPlayerShip() { return this.playerShip; }

    public int getPoints() { return points; }

	public int getGold() { return gold; }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGold(int gold) { this.gold = gold; }

	public boolean payGold(int amount){
        if (amount > gold){
            return false;
        }
        else{
            addGold(-amount);
            return true;
        }
    }

    public void addPoints(int amount) { points += amount; }

    public void addGold(int amount) { gold = gold + amount; }
}