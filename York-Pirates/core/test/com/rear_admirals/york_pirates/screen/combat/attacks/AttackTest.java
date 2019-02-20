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
        when(attacker.getSailsHealth()).thenReturn(150);
        when(attacker.getAtkMultiplier()).thenReturn(1.0f);

        // set to 0 so doesHit returns false to test behaviour when an attack misses
        when(attacker.getAccMultiplier()).thenReturn(0f);

        // if attack does not hit, no damage is caused
        assertEquals(attack.doAttack(attacker, defender), 0);

        // Assume attack never misses from now
        // Sails health is always 150 in this test to ensure doesHit returns true as long as accMultiplier = 1
        when(attacker.getAccMultiplier()).thenReturn(1f);

        assertNotEquals(attack.doAttack(attacker, defender), 0);

        // Ensure damage is only applied to defending ship
        verify(attacker, never()).damage(anyString(), anyInt());
        verify(defender).damage(anyString(), anyInt());
    }
}