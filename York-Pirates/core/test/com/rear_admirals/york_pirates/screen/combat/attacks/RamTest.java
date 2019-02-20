package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.GameTest;
import com.rear_admirals.york_pirates.Ship;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RamTest extends GameTest {

    @Test
    public void doAttackTest() {
        Ram ram = new Ram("Ram","Ram the enemy ship, causes half damage to your own ship. ", 24,32, false, 75, 0);
        Ship attacker = mock(Ship.class);
        Ship defender = mock(Ship.class);
        when(attacker.getSailsHealth()).thenReturn(100);
        when(attacker.getAtkMultiplier()).thenReturn(1.0f);

        // set to 0 so doesHit returns false to test behaviour when an attack misses
        when(attacker.getAccMultiplier()).thenReturn(0f);

        // if attack does not hit, no damage is caused
        assertEquals(ram.doAttack(attacker, defender), 0);

        // Assume attack never misses from now
        // Double accMultiplier to ensure doesHit always returns true
        when(attacker.getAccMultiplier()).thenReturn(2f);

        assertNotEquals(ram.doAttack(attacker, defender), 0);

        // Ram damages both the attacker and defender so damage method should be called for both
        verify(defender).damage(anyString(), anyInt());
        verify(attacker).damage(anyString(), anyInt());
    }
}