package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class Ram extends Attack {

	protected Ram(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent, int cost) {
		super(name, desc, dmgMin, dmgMax, skipMove, accPercent, cost);
	}

	// Ram requires a custom doAttack function and as such has its own class.
	// Ram damage depends on sails health
	@Override
	public int doAttack(Ship attacker, Ship defender) {
		if (doesHit(attacker.getAccMultiplier(), this.accPercent)) {
			// Calculate base damage dealt by the attack with a random integer from the min to max damage potential
			int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
			// Multiply real damage by the attackers damage multiplier (Increased through attack upgrades)
			this.damage = Math.round(attacker.getAtkMultiplier() * randDmg * Math.max(attacker.getSailsHealth() / 100f, 0.25f));
			defender.damage(name, this.damage);
			attacker.damage(name,this.damage/2);
			return this.damage;
		}
		return 0;
	}

	public static Attack attackRam = new Ram("Ram","Ram the enemy ship, causes half damage to your own ship. ", 24,32, false, 75, 0);
}