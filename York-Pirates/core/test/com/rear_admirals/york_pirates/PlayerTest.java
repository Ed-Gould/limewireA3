package com.rear_admirals.york_pirates;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest extends GameTest {

    @Test
    public void payGoldNoGoldTest() {
        Player player = new Player();

        // Player gold = 0
        assertFalse(player.payGold(1));
        assertFalse(player.payGold(10));
        assertFalse(player.payGold(50));
        assertTrue(player.payGold(0));
        assertEquals(player.getGold(), 0);
    }

    @Test
    public void payGoldFailedTest() {
        Player player = new Player();

        player.setGold(50);
        // Negative values originally were accepted, now changed
        assertFalse(player.payGold(-80));
        assertFalse(player.payGold(-50));
        assertFalse(player.payGold(-10));

        assertFalse(player.payGold(51));
        assertFalse(player.payGold(100));
    }

    @Test
    public void payGoldSuccessfulTest() {
        Player player = new Player();

        player.setGold(50);

        assertTrue(player.payGold(0));
        assertEquals(player.getGold(),50);

        assertTrue(player.payGold(10));
        assertEquals(player.getGold(), 40);

        assertTrue(player.payGold(40));
        assertEquals(player.getGold(), 0);

        assertFalse(player.payGold(1));
    }
}