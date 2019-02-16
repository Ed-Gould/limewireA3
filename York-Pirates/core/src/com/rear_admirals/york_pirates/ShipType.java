package com.rear_admirals.york_pirates;

import com.badlogic.gdx.graphics.Texture;

public class ShipType {
	private float atkMultiplier;
	private int defence;
	private int accuracy;
	private String name;
	private Texture texture;

	public ShipType (String name, float atkMultiplier, int defence, int accuracy, int health) {
		this.name = name;
		this.atkMultiplier = atkMultiplier;
		this.defence = defence;
		this.accuracy = accuracy;
		this.texture = new Texture("ship4.png"); //TESTING (without assets created)
	} // There is currently no way to give ships a custom texture. Do we need this?

	public String getName() { return name; }

	public float getAttack() { return atkMultiplier; }

	public int getDefence() { return defence; }

	public int getAccuracy() { return accuracy; }

	public Texture getTexture() { return texture; }

	// Static Ship Types go here
//	public static ShipType Sloop = new ShipType("Sloop", 4, 4, 7, 80);
	public static ShipType Brig = new ShipType("Brig", 1.0f, 5, 5, 100);
//	public static ShipType Galleon = new ShipType("Galleon", 6, 6, 3, 120);
}
