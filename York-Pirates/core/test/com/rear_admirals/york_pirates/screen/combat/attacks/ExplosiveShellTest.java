package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.GameTest;
import com.rear_admirals.york_pirates.Ship;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ExplosiveShellTest extends GameTest {

    @Test
    public void doAttackMissedTest() {
        ExplosiveShell explosiveShell = new ExplosiveShell("Explosive Shell","Dangerous cannonball. Has a chance to explode on own ship, causing half damage. ", 16,24, false, 85, 70);
        Ship attacker = mock(Ship.class);
        Ship defender = mock(Ship.class);
        when(attacker.getSailsHealth()).thenReturn(150);
        when(attacker.getAtkMultiplier()).thenReturn(1.0f);

        // set to 0 so doesHit returns false to test that the player is damaged if their attack misses
        when(attacker.getAccMultiplier()).thenReturn(0f);

        // if attack misses the defender, half damage is applied to the attacker
        assertEquals(explosiveShell.doAttack(attacker, defender), 0);
        // verify that damage is only applied to the attacker
        verify(attacker).damage(anyString(), anyInt());
        verify(defender, never()).damage(anyString(), anyInt());


    }

    @Test
    public void doAttackSuccessfulTest () {
        ExplosiveShell explosiveShell = new ExplosiveShell("Explosive Shell","Dangerous cannonball. Has a chance to explode on own ship, causing half damage. ", 16,24, false, 85, 70);
        Ship attacker = mock(Ship.class);
        Ship defender = mock(Ship.class);
        when(attacker.getSailsHealth()).thenReturn(150);
        when(attacker.getAtkMultiplier()).thenReturn(1.0f);

        // Assume attack never misses from now
        // Sails health is always 150 in this test to ensure doesHit returns true as long as accMultiplier = 1
        when(attacker.getAccMultiplier()).thenReturn(1f);

        assertNotEquals(explosiveShell.doAttack(attacker, defender), 0);

        // Ensure damage is only applied to defending ship
        verify(attacker, never()).damage(anyString(), anyInt());
        verify(defender).damage(anyString(), anyInt());
    }
}