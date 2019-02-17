package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class Attack {
	protected String name;
	protected String desc;
	protected int damage;
	protected int dmgMin, dmgMax; // Minimum and maximum damage attacks can do (randomly in this range)
	protected int accPercent;
	protected int cost;
	protected boolean skipMoveStatus;
	protected boolean skipMove;

	// Generic constructor. Creates simple broadside attack.
	protected Attack() {
		this.name = "Broadside";
		this.desc = "Fire a broadside at your enemy.";
		this.dmgMin = 8;
		this.dmgMax = 12;
		this.accPercent = 80;
		this.skipMove = false;
		this.skipMoveStatus = skipMove;
		this.cost = 0;

	}

	// Custom constructor. Can be used to create any attack which applies a multiple of the attacker's damage
	// to the defender. Can also take a turn to charge and have custom accuracy.
	protected Attack(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent, int cost) {
		this.name = name;
		this.desc = desc + "Base damage from " + dmgMin + " to " + dmgMax + ". Base accuracy of " + accPercent;
		this.dmgMin = dmgMin;
		this.dmgMax = dmgMax;
		this.skipMove = skipMove;
		this.skipMoveStatus = skipMove;
		this.accPercent = accPercent;
		this.cost = cost;
	}


	// New function used to check if an attack hits the enemy.
	protected boolean doesHit(float shipMultiplier, int accPercent) {//================================ Modify to account for sail damage?
		int random = ThreadLocalRandom.current().nextInt(0, 101);
		return accPercent * shipMultiplier > random;
	}

	// Function called to actually perform the attack.
	public int doAttack(Ship attacker, Ship defender) {
		if (doesHit(attacker.getAccMultiplier() * Math.max(attacker.getSailsHealth() / 100f, 0.4f), this.accPercent)) {
			// Calculate base damage dealt by the attack with a random integer from the min to max damage potential
		    int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
		    // Multiply real damage by the attackers damage multiplier (Increased through attack upgrades)
			this.damage = Math.round(attacker.getAtkMultiplier() * randDmg);
			defender.damage(name, this.damage);
			return this.damage;
		}
		return 0;
	}

	public String getName() { return this.name;	}
	public String getDesc() { return this.desc; }
	public int getCost() {
		return this.cost;
	}
	public boolean isSkipMove() {
		return this.skipMove;
	}
	public boolean isSkipMoveStatus() {
		return this.skipMoveStatus;
	}
	public void setSkipMoveStatus(boolean skipMoveStatus) {
		this.skipMoveStatus = skipMoveStatus;
	}

	// attacks to be used in the game are defined here.
	public static Attack attackMain = new Attack("Broadside","Normal cannons. ",8,12,false,80, 0);
	public static Attack attackSwivel = new Attack("Swivel","Lightweight cannons. ",5,9,false,90, 0);
	public static Attack attackBoard = new Attack("Board","Board enemy ship, charging an attack over a turn. ", 24, 28,true,95, 0);
	public static Attack attackNone = new Attack("No attack","You haven't got any weapons in this slot, Equip an attack! ",0,0,false,0, 0);
}