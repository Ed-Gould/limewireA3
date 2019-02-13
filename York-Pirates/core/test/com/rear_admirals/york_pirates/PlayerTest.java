package com.rear_admirals.york_pirates;

import org.junit.Test;
import sources.GameTest;

import static org.junit.Assert.*;

public class PlayerTest extends GameTest {

    @Test
    public void payGold() {
        Player player = new Player();

        assertFalse(player.payGold(50));
    }
}