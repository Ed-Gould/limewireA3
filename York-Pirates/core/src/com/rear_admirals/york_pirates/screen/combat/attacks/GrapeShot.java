package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class GrapeShot extends Attack {

    public GrapeShot(String name, String desc, int dmgMin, int dmgMax, double accMultiplier, boolean skipMove, int accPercent) {
        super(name, desc, dmgMin, dmgMax, accMultiplier, skipMove, accPercent);
    }

    // Grapeshot requires a custom doAttack function and as such has its own class.
    @Override
    public int doAttack(Ship attacker, Ship defender) {
        this.damage = 0;
        for (int i = 0; i < 4; i++) { // Fires 4 shots.
            if (doesHit(attacker.getAccuracy(), this.accPercent)) {
                int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
                this.damage += attacker.getAtkMultiplier() * randDmg; // Landed shots do half as much damage as a swivel shot.
                System.out.println("GRAPE HIT");
            } else {
                System.out.println("GRAPE MISSED");
            }
        }
        defender.damage(this.damage);
        return this.damage;
    }

    public static Attack attackGrape = new GrapeShot("Grape Shot","Fire a bundle of smaller cannonballs at the enemy. ",1 ,2,1,false, 25);
}

