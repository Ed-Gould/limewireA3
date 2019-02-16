package com.rear_admirals.york_pirates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rear_admirals.york_pirates.base.PhysicsActor;

import java.util.concurrent.ThreadLocalRandom;

import static com.rear_admirals.york_pirates.College.Derwent;

public class Ship extends PhysicsActor {
	private String name;
    private float atkMultiplier;
	private int defence;
	private int accuracy;
	private int hullHealth;
	private int sailHealth;
	private double damageRatio;
    private ShipType type;
    private int healthMax;
    private Texture sailingTexture;
    private College college;
    private boolean isBoss = false;

    // For testing purposes only. Use of this constructor in-game WILL cause errors.
    @Deprecated
    public Ship(){
        this.name = "DEBUG SHIP";
        this.atkMultiplier = 1.0f;
        this.defence = 5;
        this.accuracy = 5;
        this.healthMax = defence*20;
        this.hullHealth = healthMax / 2;
        this.sailHealth = healthMax / 2;
        this.college = Derwent;
    }

    public Ship(ShipType type, College college) {
        this.name = college.getName() + " " + type.getName();
        this.atkMultiplier = type.getAttack();
        this.defence = type.getDefence();
        this.accuracy = type.getAccuracy();
        this.healthMax = defence*20;
        this.hullHealth = healthMax / 2;
        this.sailHealth = healthMax / 2;
        this.college = college;
        this.type = type;
        this.sailingTexture = new Texture(Gdx.files.internal("ship (1).png"));
        setupShip();
    }

    public Ship(ShipType type, College college, String texturePath) {
        this.name = college.getName() + " " + type.getName();
        this.atkMultiplier = type.getAttack();
        this.defence = type.getDefence();
        this.accuracy = type.getAccuracy();
        this.healthMax = defence*20;
        this.hullHealth = healthMax / 2;
        this.sailHealth = healthMax / 2;
        this.college = college;
        this.type = type;
        this.sailingTexture = new Texture(Gdx.files.internal(texturePath));
        setupShip();
    }

    public Ship(ShipType type, String name, College college) {
	    this(type, college);
	    this.name = name;
    }

    public Ship(float atkMultiplier, int defence, int accuracy, ShipType type, College college, String name, boolean isBoss) {
        this.atkMultiplier = atkMultiplier;
        this.defence = defence;
        this.accuracy = accuracy;
        this.type = type;
        this.name = name;
        this.healthMax = defence*20;
        this.college = college;
        this.hullHealth = healthMax / 2;
        this.sailHealth = healthMax / 2;
        this.sailingTexture = new Texture(Gdx.files.internal("ship (1).png"));
        this.isBoss = isBoss;
        setupShip();
    }

    public void setupShip(){
        this.setWidth(this.sailingTexture.getWidth());
        this.setHeight(this.sailingTexture.getHeight());
        this.setOriginCentre();
        this.setMaxSpeed(500);
        this.setDeceleration(250);
        this.setEllipseBoundary();
    }

    public void playerMove(float dt) {
        this.setAccelerationXY(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.rotateBy(90 * dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            this.rotateBy(-90 * dt );
        }
        //Causes ship to accelerate (Lifting the anchor)
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
            this.setAnchor(false);
        }
        //Causes ship to decelerate to a stop (Dropping the anchor)
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
            this.setAnchor(true);
        }
    }

    public void damage(String attack, int value) {
        if (attack.equals("Broadside")) {
            damageRatio = ThreadLocalRandom.current().nextInt(1, 25);
            sailHealth -= value * (damageRatio / 100);
            hullHealth -= value * ((100 - damageRatio) / 100);
        } else if (attack.equals("Grape Shot")) {
            sailHealth -= value;
        } else if (attack.equals("Ram") || (attack.equals("Board"))) {
            hullHealth -= value;
        }
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.setColor(1,1,1,alpha);
        batch.draw(new TextureRegion(sailingTexture),getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
    }

    // Getters and Setters
    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public int getHealthMax() {
        return healthMax;
    }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getAtkMultiplier() {
        return atkMultiplier;
    }

    public void setAtkMultiplier(float atkMultiplier) {
        this.atkMultiplier = atkMultiplier;
    }

    public void addAttack(float increase){
        this.atkMultiplier = atkMultiplier + increase;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
        this.healthMax = defence * 20;
    }

    public void addDefence(int increase){
        this.defence = defence + increase;
        this.healthMax = (defence) * 20;
    }

    public int getAccuracy() { return accuracy; }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void addAccuracy(int increase){
        this.accuracy = accuracy + increase;
    }

    public int getHullHealth() {
        return hullHealth;
    }

    public void setHullHealth(int hullHealth) {
        this.hullHealth = hullHealth;
    }

    public int getSailHealth() { return sailHealth; }

    public void setSailHealth(int sailHealth) { this.sailHealth = sailHealth; }

    public void healHull(int value) {
        if (this.hullHealth + value > healthMax / 2) {
            this.hullHealth = healthMax / 2;
        } else {
            this.hullHealth += value;
        }
    }

    public void healSail(int value) {
        if (this.sailHealth + value > healthMax / 2) {
            this.sailHealth = healthMax / 2;
        } else {
            this.sailHealth += value;
        }
    }

    public int getHullHealthFromMax() {
        return this.healthMax - this.hullHealth;
    }

    public int getSailHealthFromMax() {
        return this.healthMax - this.sailHealth;
    }

    public String getType() {
		return type.getName();
	}

    public void setType(ShipType type) { this.type = type; }

    public Texture getSailingTexture() { return this.sailingTexture; }

    public boolean getIsBoss() { return this.isBoss; }
}