package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class GrapeShot extends Attack {

    public GrapeShot(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent, int cost) {
        super(name, desc, dmgMin, dmgMax, skipMove, accPercent, cost);
    }

    // Grapeshot requires a custom doAttack function and as such has its own class.
    @Override
    public int doAttack(Ship attacker, Ship defender) {
        this.damage = 0;
        for (int i = 0; i < 4; i++) { // Fires 4 shots.
            if (doesHit(attacker.getAccMultiplier() * Math.max(attacker.getSailsHealth() / 100f, 0.4f), this.accPercent)) {
                // Calculate base damage dealt by the attack with a random integer from the min to max damage potential
                int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
                // Multiply real damage by the attackers damage multiplier (Increased through attack upgrades)
                this.damage += attacker.getAtkMultiplier() * randDmg;
            }
        }
        defender.damage(name, this.damage);
        return this.damage;
    }

    public static Attack attackGrape = new GrapeShot("Grape Shot","Fire four very weak cannonballs. ",3 ,5,false, 85, 60);
}

