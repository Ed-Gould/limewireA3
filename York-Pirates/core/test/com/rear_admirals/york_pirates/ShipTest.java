package com.rear_admirals.york_pirates;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTest extends GameTest{

    @Test
    public void damageBroadsideTest() {
        Ship ship = new Ship();

        ship.damage("Broadside", 50);
        // Check total damage caused is approximately the same as the value passed (account for rounding error)
        assertTrue(ship.getSailsHealthFromMax() + ship.getHullHealthFromMax()  > 48
                && ship.getSailsHealthFromMax() + ship.getHullHealthFromMax() < 52);

        // Check damage caused to sail is at most a quarter of the total damage caused
        assertTrue(ship.getSailsHealthFromMax() <=  13);
    }

    @Test
    public void damageDoubleShotTest() {
        Ship ship = new Ship();

        ship.damage("Double Shot", 28);
        assertTrue(ship.getSailsHealthFromMax() + ship.getHullHealthFromMax()  > 26
                && ship.getSailsHealthFromMax() + ship.getHullHealthFromMax() < 30);
        assertTrue(ship.getSailsHealthFromMax() <=  7);
    }

    @Test
    public void damageExplosiveShellTest() {
        Ship ship = new Ship();

        ship.damage("Explosive Shell", 92);
        assertTrue(ship.getSailsHealthFromMax() + ship.getHullHealthFromMax()  > 90
                && ship.getSailsHealthFromMax() + ship.getHullHealthFromMax() < 94);
        assertTrue(ship.getSailsHealthFromMax() <=  23);
    }

    @Test
    public void damageGrapeShotTest() {
        Ship ship = new Ship();

        ship.damage("Grape Shot", 40);
        // Ensure damage is only done to sails
        assertEquals(ship.getSailsHealthFromMax(), 40);
        assertEquals(ship.getHullHealthFromMax(), 0);
    }

    @Test
    public void damageRamTest() {
        Ship ship = new Ship();

        ship.damage("Ram", 65);
        // Ensure damage is only done to hull
        assertEquals(ship.getHullHealthFromMax(), 65);
        assertEquals(ship.getSailsHealthFromMax(), 0);
    }

    @Test
    public void damageBoardTest() {
        Ship ship = new Ship();

        ship.damage("Board", 93);
        assertEquals(ship.getHullHealthFromMax(), 93);
        assertEquals(ship.getSailsHealthFromMax(), 0);
    }

    @Test
    public void damageSwivelTest() {
        Ship ship = new Ship();

        ship.damage("Swivel", 22);
        assertEquals(ship.getHullHealthFromMax(), 22);
        assertEquals(ship.getSailsHealthFromMax(), 0);
    }

    @Test
    public void healSailsTest() {
        Ship ship = new Ship();
        assertEquals(ship.getHealthMax(), 100); // assume healthMax = 100 for the test

        ship.healSails(0);
        assertEquals(ship.getSailsHealth(), 100);

        ship.setSailsHealth(50);
        ship.healSails(20);
        assertEquals(ship.getSailsHealth(), 70);

        ship.healSails(50);
        assertEquals(ship.getSailsHealth(), 100);
    }

    @Test
    public void healHullTest() {
        Ship ship = new Ship();
        assertEquals(ship.getHealthMax(), 100); // assume healthMax = 100 for the test

        ship.healHull(0);
        assertEquals(ship.getHullHealth(), 100);

        ship.setHullHealth(30);
        ship.healHull(35);
        assertEquals(ship.getHullHealth(), 65);

        ship.healHull(35);
        assertEquals(ship.getHullHealth(), 100);
    }
}