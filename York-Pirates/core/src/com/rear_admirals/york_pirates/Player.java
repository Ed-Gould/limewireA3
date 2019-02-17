package com.rear_admirals.york_pirates;

import com.rear_admirals.york_pirates.screen.combat.attacks.*;

import java.util.ArrayList;
import java.util.List;

import static com.rear_admirals.york_pirates.College.*;
import static com.rear_admirals.york_pirates.ShipType.*;

public class Player {
    private Ship playerShip;
    private int gold;
    private int points;
    public List<Attack> equippedAttacks = new ArrayList<Attack>(); // List of weapons the player is currently using
    public List<Attack> ownedAttacks = new ArrayList<Attack>(); // List of all weapons the player owns
    public Player() {
	    this.playerShip = new Ship(Brig, "Your Ship", Derwent);
        this.gold = 0;
        this.points = 0;

        // Add the attacks the player starts with to the equipped and owned attacks lists
        ownedAttacks.add(Ram.attackRam);
        ownedAttacks.add(Attack.attackSwivel);
        ownedAttacks.add(Attack.attackBoard);

        equippedAttacks.add(Ram.attackRam);
        equippedAttacks.add(Attack.attackSwivel);
        equippedAttacks.add(Attack.attackBoard);
    }

    public Player(Ship ship) {
        this.playerShip = ship;
        this.gold = 0;
        this.points = 0;

        ownedAttacks.add(Ram.attackRam);
        ownedAttacks.add(Attack.attackSwivel);
        ownedAttacks.add(Attack.attackBoard);

        equippedAttacks.add(Ram.attackRam);
        equippedAttacks.add(Attack.attackSwivel);
        equippedAttacks.add(Attack.attackBoard);
    }

    public Ship getPlayerShip() { return this.playerShip; }

    public int getPoints() { return points; }

	public int getGold() { return gold; }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGold(int gold) { this.gold = gold; }

	public boolean payGold(int value){
        if (value > gold){
            return false;
        }
        else{
            addGold(-value);
            return true;
        }
    }

    public void addPoints(int value) { points += value; }

    public void addGold(int value) { gold = gold + value; }
}