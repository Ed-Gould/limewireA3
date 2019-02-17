package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class ExplosiveShell extends Attack{

    protected ExplosiveShell(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent, int cost){
        super(name, desc, dmgMin, dmgMax, skipMove, accPercent, cost);
    }

    @Override
    public int doAttack(Ship attacker, Ship defender) {
        int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
        this.damage = Math.round(attacker.getAtkMultiplier() * randDmg);

        if (doesHit(attacker.getAccMultiplier(), this.accPercent)) {
            defender.damage(name, this.damage);
            return this.damage;
        }
        else{
            attacker.damage(name, this.damage / 2);
        }
        return 0;
    }

    public int getCost() {
        return cost;
    }

    public static Attack attackExplosive = new ExplosiveShell("Explosive Shell","Dangerous cannonball. Has a chance to explode on own ship, causing half damage. ", 10,15, false, 65, 1);
}
