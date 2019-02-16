package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class Ram extends Attack {

	protected Ram(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent) {
		super(name, desc, dmgMin, dmgMax, skipMove, accPercent);
	}

	// Ram requires a custom doAttack function and as such has its own class.
	@Override
	public int doAttack(Ship attacker, Ship defender) {
		if (doesHit(attacker.getAccuracy(), this.accPercent)) {
			int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
			this.damage = Math.round(attacker.getAtkMultiplier() * randDmg);
			defender.damage(this.damage);
			attacker.damage(this.damage/2);
			return this.damage;
		}
		return 0;
	}

	public static Attack attackRam = new Ram("Ram","Ram your ship into your enemy, causing damage to both of you. ", 15,20, false, 85);
}