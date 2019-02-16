package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class Attack {
	protected String name;
	protected String desc;
	protected int damage;
	protected int dmgMin, dmgMax; // Minimum and maximum damage attacks can do (randomly in this range)
	protected int accPercent;
	protected boolean skipMoveStatus;
	protected boolean skipMove;

	// Generic constructor. Creates simple broadside attack.
	protected Attack() {
		this.name = "Broadside";
		this.desc = "Fire a broadside at your enemy.";
		this.dmgMin = 5;
		this.dmgMax = 8;
		this.skipMove = false;
		this.skipMoveStatus = skipMove;
	}

	// Custom constructor. Can be used to create any attack which applies a multiple of the attacker's damage
	// to the defender. Can also take a turn to charge and have custom accuracy.
	protected Attack(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent) {
		this.name = name;
		this.desc = desc + "Base damage from " + dmgMin + " to " + dmgMax + ". Base accuracy of " + accPercent;
		this.dmgMin = dmgMin;
		this.dmgMax = dmgMax;
		this.skipMove = skipMove;
		this.skipMoveStatus = skipMove;
		this.accPercent = accPercent;
	}

	// New function used to check if an attack hits the enemy.
	protected boolean doesHit(int shipAcc, int accPercent) {
		int random = ThreadLocalRandom.current().nextInt(0, 101);
		if (accPercent * (1+(shipAcc-3)*0.02) > random){
			return true;
		} else{
			return false;
		}
	}

	// Function called to actually perform the attack.
	public int doAttack(Ship attacker, Ship defender) {
		if ( doesHit(attacker.getAccuracy(), this.accPercent) ) {
		    int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
			this.damage = Math.round(attacker.getAtkMultiplier() * randDmg);
			defender.damage(this.damage);
			return this.damage;
		}
		return 0;
	}

	public String getName() { return this.name;	}
	public String getDesc() { return this.desc; }
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
	public static Attack attackMain = new Attack("Broadside","Normal cannons. ",5,8,false,60);
	public static Attack attackSwivel = new Attack("Swivel","Lightweight cannons. ",4,7,false,75);
	public static Attack attackBoard = new Attack("Board","Board enemy ship, charging an attack over a turn. ", 15, 17,true,95);
}