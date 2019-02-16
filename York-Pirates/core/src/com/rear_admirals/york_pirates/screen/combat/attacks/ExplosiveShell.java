package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class ExplosiveShell extends Attack{

    protected ExplosiveShell(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent){
        super(name, desc, dmgMin, dmgMax, skipMove, accPercent);
    }

    @Override
    public int doAttack(Ship attacker, Ship defender) {
        int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
        this.damage = Math.round(attacker.getAtkMultiplier() * randDmg);

        if (doesHit(attacker.getAccuracy(), this.accPercent)) {
            defender.damage(this.damage);
            return this.damage;
        }
        else{
            attacker.damage(this.damage / 2);
        }
        return 0;
    }

    public static Attack attackExplosive = new ExplosiveShell("Explosive Shell","Dangerous cannonball. Has a chance to explode on own ship, causing half damage. ", 10,15, false, 65);
}
