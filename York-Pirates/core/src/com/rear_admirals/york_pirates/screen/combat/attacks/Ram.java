package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

public class Ram extends Attack {

	protected Ram(String name, String desc, int dmgMultiplier, double accMultiplier, boolean skipMove, int accPercent) {
		super(name, desc, dmgMultiplier, accMultiplier, skipMove, accPercent);
	}

	// Ram requires a custom doAttack function and as such has its own class.
	@Override
	public int doAttack(Ship attacker, Ship defender) {
		if ( doesHit(attacker.getAccuracy(), this.accPercent) ) {
			this.damage = attacker.getAttack()*this.dmgMultiplier;
			defender.damage(this.damage);
			attacker.damage(this.damage/2);
			return this.damage;
		}
		return 0;
	}

	public static Attack attackRam = new Ram("Ram","Ram your ship into your enemy, causing damage to both of you.", 4, 1, false, 85);
}