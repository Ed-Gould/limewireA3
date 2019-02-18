package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.GameTest;
import com.rear_admirals.york_pirates.Ship;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AttackTest extends GameTest {

    @Test
    public void doAttackTest() {
        Attack attack = new Attack();
        Ship attacker = mock(Ship.class);
        Ship defender = mock(Ship.class);

        // set to 0 so doesHit returns false to test behaviour when an attack misses
        when(attacker.getAccMultiplier()).thenReturn(0f);
        when(attacker.getSailsHealth()).thenReturn(100);

        // if attack does not hit, no damage is caused
        assertEquals(attack.doAttack(attacker, defender), 0);
        System.out.println(attack.doAttack(attacker, defender));
        // Assume attack never misses from now
        when(attacker.getAccMultiplier()).thenReturn(1.0f);
        when(attacker.getSailsHealth()).thenReturn(100);



    }
}