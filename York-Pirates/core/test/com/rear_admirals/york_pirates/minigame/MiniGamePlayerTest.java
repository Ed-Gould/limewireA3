package com.rear_admirals.york_pirates.minigame;

import com.rear_admirals.york_pirates.GameTest;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MiniGamePlayerTest extends GameTest {

    @Test
    public void isDeadTest() {
        MiniGamePlayer player = new MiniGamePlayer();
        ArrayList<MiniGameEnemy> enemies = new ArrayList<MiniGameEnemy>();
        MiniGameEnemy enemy1 = mock(MiniGameEnemy.class);
        MiniGameEnemy enemy2 = mock(MiniGameEnemy.class);
        MiniGameEnemy enemy3 = mock(MiniGameEnemy.class);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);

        // Set positions of enemies on screen
        // Co-ordinates to be passed to method must be multiples of 64
        when(enemy1.getX()).thenReturn(64f);
        when(enemy1.getY()).thenReturn(128f);

        when(enemy2.getX()).thenReturn(192f);
        when(enemy2.getY()).thenReturn(64f);

        when(enemy3.getX()).thenReturn(128f);
        when(enemy3.getY()).thenReturn(256f);

        // Co-ordinates where player should not be in a position to be killed by an enemy
        player.setX(128f);
        player.setY(64f);
        assertFalse(player.isDead(enemies, player));

        player.setX(64f);
        player.setY(192f);
        assertFalse(player.isDead(enemies, player));

        player.setX(384f);
        player.setY(128f);
        assertFalse(player.isDead(enemies, player));

        // Co-ordinates where player should be killed by an enemy
        player.setX(64f);
        player.setY(128f);
        assertTrue(player.isDead(enemies, player));

        player.setX(192f);
        player.setY(64f);
        assertTrue(player.isDead(enemies, player));

        player.setX(128f);
        player.setY(256f);
        assertTrue(player.isDead(enemies, player));
    }

    @Test
    public void movableTest() {
        MiniGamePlayer player = new MiniGamePlayer();
        boolean[][] isWall = new boolean[20][20];
        boolean[][] isExit = new boolean[20][20];

        // Set walls around co-ordinate (3, 3) except in cell above
        isWall[2][3] = true;
        isWall[4][3] = true;
        isWall[3][2] = true;

        player.setX(192f);
        player.setY(192f);
        player.resetMovable(); // Reset which cells are stored as valid moves
        assertFalse(player.movable(player, isWall, isExit));
        // Check that player should only be allowed to move to cell above
        assertTrue(player.moveUp);
        assertFalse(player.moveLeft && player.moveRight && player.moveDown);

        // Set walls around (2, 8) except in cell to the right
        isWall[1][8] = true;
        isWall[2][7] = true;
        isWall[2][9] = true;

        player.setX(128f);
        player.setY(512f);
        player.resetMovable();
        assertFalse(player.movable(player, isWall, isExit));
        assertTrue(player.moveRight);
        assertFalse(player.moveLeft && player.moveUp && player.moveDown);

        // Set walls around (10, 5) except in cell to left
        isWall[11][5] = true;
        isWall[10][4] = true;
        isWall[10][6] = true;

        player.setX(640f);
        player.setY(320f);
        player.resetMovable();
        assertFalse(player.movable(player, isWall, isExit));
        assertTrue(player.moveLeft);
        assertFalse(player.moveUp && player.moveRight && player.moveDown);

        // Set walls around (14, 4) except in cell below
        isWall[13][4] = true;
        isWall[15][4] = true;
        isWall[14][5] = true;

        player.setX(896f);
        player.setY(256f);
        player.resetMovable();
        assertFalse(player.movable(player, isWall, isExit));
        assertTrue(player.moveDown);
        assertFalse(player.moveLeft && player.moveUp && player.moveRight);

        isExit[1][18] = true; // Set exit of maze to (1, 18)
        player.setX(64f);
        player.setY(1152f);
        player.resetMovable();
        assertTrue(player.movable(player, isWall, isExit));
    }

}