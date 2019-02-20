package com.rear_admirals.york_pirates.screen.combat.attacks;

import com.rear_admirals.york_pirates.GameTest;
import com.rear_admirals.york_pirates.Ship;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class GrapeShotTest extends GameTest {

    @Test
    public void doAttackTest() {
        GrapeShot grapeShot = new GrapeShot("Grape Shot","Fire four very weak cannonballs. ",3 ,5,false, 85, 60);
        Ship attacker = mock(Ship.class);
        Ship defender = mock(Ship.class);
        when(attacker.getSailsHealth()).thenReturn(150);
        when(attacker.getAtkMultiplier()).thenReturn(1.0f);

        // set to 0 so doesHit returns false to test behaviour when an attack misses
        when(attacker.getAccMultiplier()).thenReturn(0f);

        // if attack does not hit, no damage is caused
        assertEquals(grapeShot.doAttack(attacker, defender), 0);

        // Assume attack never misses from now
        // Sails health is always 150 in this test to ensure doesHit returns true as long as accMultiplier = 1
        when(attacker.getAccMultiplier()).thenReturn(1f);

        assertNotEquals(grapeShot.doAttack(attacker, defender), 0);

        // Ensure damage is only applied to defending ship
        verify(attacker, never()).damage(anyString(), anyInt());
        verify(defender, times(2)).damage(anyString(), anyInt()); //second time damage is called in this test method
    }
}