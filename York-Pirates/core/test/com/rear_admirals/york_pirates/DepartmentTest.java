package com.rear_admirals.york_pirates;

import com.rear_admirals.york_pirates.screen.combat.attacks.Attack;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DepartmentTest extends GameTest {

    @Mock
    PirateGame pirateGame;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void buyUpgrade() {
        // Remove Player and Ship classes dependencies by mocking them
        when(pirateGame.getPlayer()).thenReturn(mock(Player.class));
        when(pirateGame.getPlayer().getPlayerShip()).thenReturn(mock(Ship.class));
        when(pirateGame.getPlayer().getPlayerShip().getDefence()).thenReturn(5); // default defence value for Ship

        Department defenceDepartment = new Department("Derwent", "defence", mock(Attack.class), pirateGame);

        // Assume player doesn't have enough gold
        when(pirateGame.getPlayer().payGold(anyInt())).thenReturn(false);

        // Should then return false
        assertFalse(defenceDepartment.buyUpgrade());

        // From now, assume player has enough gold
        when(pirateGame.getPlayer().payGold(anyInt())).thenReturn(true);

        assertTrue(defenceDepartment.buyUpgrade());

        // Only addDefence should called as the department's upgrade is defence
        verify(pirateGame.getPlayer().getPlayerShip()).addDefence(anyInt());
        verify(pirateGame.getPlayer().getPlayerShip(), never()).addAttack(anyFloat());
        verify(pirateGame.getPlayer().getPlayerShip(), never()).addAccuracy(anyFloat());

        Department attackDepartment = new Department("Vanbrugh", "attack", mock(Attack.class), pirateGame);
        assertTrue(attackDepartment.buyUpgrade());

        // addDefence should only have been called once (for the previous department), now addAttack should have been called
        verify(pirateGame.getPlayer().getPlayerShip()).addDefence(anyInt());
        verify(pirateGame.getPlayer().getPlayerShip()).addAttack(anyFloat());
        verify(pirateGame.getPlayer().getPlayerShip(), never()).addAccuracy(anyFloat());

        Department accuracyDepartment = new Department("James", "accuracy", mock(Attack.class), pirateGame);
        assertTrue(accuracyDepartment.buyUpgrade());

        // Now all three methods should have been called exactly once
        verify(pirateGame.getPlayer().getPlayerShip()).addDefence(anyInt());
        verify(pirateGame.getPlayer().getPlayerShip()).addAttack(anyFloat());
        verify(pirateGame.getPlayer().getPlayerShip()).addAccuracy(anyFloat());
    }

    @Test
    public void buyWeapon() {
        when(pirateGame.getPlayer()).thenReturn(mock(Player.class));
        pirateGame.getPlayer().ownedAttacks = mock(ArrayList.class);

        Department department = new Department("Derwent", "defence", mock(Attack.class), pirateGame);

        // Assume player doesn't have enough gold
        when(pirateGame.getPlayer().payGold(anyInt())).thenReturn(false);
        assertFalse(department.buyWeapon());

        // Now assume player has enough gold
        when(pirateGame.getPlayer().payGold(anyInt())).thenReturn(true);

        assertTrue(department.buyWeapon());
        // Verify that method to add weapon is run
        verify(pirateGame.getPlayer().ownedAttacks).add(any(Attack.class));
    }

    @Test
    public void getUpgradeCost() {
        when(pirateGame.getPlayer()).thenReturn(mock(Player.class));
        when(pirateGame.getPlayer().getPlayerShip()).thenReturn(mock(Ship.class));
        when(pirateGame.getPlayer().getPlayerShip().getDefence()).thenReturn(5);
        when(pirateGame.getPlayer().getPlayerShip().getAtkMultiplier()).thenReturn(1.0f); // default atkMultiplier value
        when(pirateGame.getPlayer().getPlayerShip().getAccMultiplier()).thenReturn(1.0f); // default accMultiplier

        Department noneDepartment = new Department("Wentworth", "", mock(Attack.class), pirateGame);

        assertEquals(noneDepartment.getUpgradeCost(), 0);

        Department defenceDepartment = new Department("Derwent", "defence", mock(Attack.class), pirateGame);
        defenceDepartment.getUpgradeCost();

        verify(pirateGame.getPlayer().getPlayerShip()).getDefence();
        verify(pirateGame.getPlayer().getPlayerShip(), never()).getAtkMultiplier();
        verify(pirateGame.getPlayer().getPlayerShip(), never()).getAccMultiplier();

        Department attackDepartment = new Department("Vanbrugh", "attack", mock(Attack.class), pirateGame);
        attackDepartment.getUpgradeCost();

        verify(pirateGame.getPlayer().getPlayerShip()).getDefence();
        verify(pirateGame.getPlayer().getPlayerShip()).getAtkMultiplier();
        verify(pirateGame.getPlayer().getPlayerShip(), never()).getAccMultiplier();

        Department accuracyDepartment = new Department("James", "accuracy", mock(Attack.class), pirateGame);
        accuracyDepartment.getUpgradeCost();

        // Check that each method is only called once for their respective department
        verify(pirateGame.getPlayer().getPlayerShip()).getDefence();
        verify(pirateGame.getPlayer().getPlayerShip()).getAtkMultiplier();
        verify(pirateGame.getPlayer().getPlayerShip()).getAccMultiplier();
    }
}