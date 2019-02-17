package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.Ship;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleShot extends Attack{

    protected DoubleShot(String name, String desc, int dmgMin, int dmgMax, boolean skipMove, int accPercent){
        super(name, desc, dmgMin, dmgMax, skipMove, accPercent);
    }

    // Double shot requires a custom doAttack function as it is a more complicated attack
    @Override
    public int doAttack(Ship attacker, Ship defender){
        this.damage = 0;
        for (int i = 0; i < 2; i++) { // Fires 2 shots.
            if (doesHit(attacker.getAccMultiplier(), this.accPercent)) {
                int randDmg = ThreadLocalRandom.current().nextInt(this.dmgMin, this.dmgMax + 1);
                this.damage += attacker.getAtkMultiplier() * randDmg;
                System.out.println("DOUBLE SHOT HIT");
            } else {
                System.out.println("DOUBLE SHOT MISSED");
            }
        }
        defender.damage(name, this.damage);
        return this.damage;
    }

    public static Attack attackDouble = new DoubleShot("Double Shot","Fires two weaker cannonballs. ",3 ,4,false, 60);
}
