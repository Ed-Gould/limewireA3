package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class ExplosiveShell extends Attack{

    protected ExplosiveShell(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent, int cost){
        super(name, desc, dmgMin, dmgMax, skipMove, accPercent, cost);
    }

    @Override
    public int doAttack(Ship attacker, Ship defender) {
        // Calculate base damage dealt by the attack with a random integer from the min to max damage potential
        int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
        // Multiply real damage by the attackers damage multiplier (Increased through attack upgrades)
        this.damage = Math.round(attacker.getAtkMultiplier() * randDmg);

        if (doesHit(attacker.getAccMultiplier(), this.accPercent)) {
            defender.damage(name, this.damage);
            return this.damage;
        }
        else{ // If the attack misses deal half damage to player
            attacker.damage(name, this.damage / 2);
        }
        return 0;
    }

    public int getCost() {
        return cost;
    }

    public static Attack attackExplosive = new ExplosiveShell("Explosive Shell","Dangerous cannonball. Has a chance to explode on own ship, causing half damage. ", 16,24, false, 85, 70);
}
