package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

public class GrapeShot extends Attack {

    public GrapeShot(String name, String desc, int dmgMultiplier, double accMultiplier, boolean skipMove, int accPercent) {
        super(name, desc, dmgMultiplier, accMultiplier, skipMove, accPercent);
    }

    // Grapeshot requires a custom doAttack function and as such has its own class.
    @Override
    public int doAttack(Ship attacker, Ship defender) {
        this.damage = 0;
        for (int i = 0; i < 4; i++) { // Fires 4 shots.
            if (doesHit(attacker.getAccuracy(), this.accPercent)) {
                this.damage += attacker.getAttack() * this.dmgMultiplier; // Landed shots do half as much damage as a swivel shot.
                System.out.println("GRAPE HIT");
            } else {
                System.out.println("GRAPE MISSED");
            }
        }
        defender.damage(this.damage);
        return this.damage;
    }

    public static Attack attackGrape = new GrapeShot("Grape Shot","Fire a bundle of smaller cannonballs at the enemy.",1,1,false, 25);
}

