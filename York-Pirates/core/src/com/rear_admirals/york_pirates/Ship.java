package com.rear_admirals.york_pirates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rear_admirals.york_pirates.College;
import com.rear_admirals.york_pirates.ShipType;
import com.rear_admirals.york_pirates.base.PhysicsActor;

import static com.rear_admirals.york_pirates.College.Derwent;

public class Ship extends PhysicsActor {
	private String name;
    private int attack;
	private int defence;
	private int accuracy;
	private int health;
    private ShipType type;
    private int healthMax;
    private Texture sailingTexture;
    private College college;
    private boolean isBoss = false;

    // For testing purposes only. Use of this constructor in-game WILL cause errors.
    @Deprecated
    public Ship(){
        this.name = "DEBUG SHIP";
        this.attack = 5;
        this.defence = 5;
        this.accuracy = 5;
        this.healthMax = defence*20;
        this.health = healthMax;
        this.college = Derwent;
    }

    public Ship(ShipType type, College college) {
        this.name = college.getName() + " " + type.getName();
        this.attack = type.getAttack();
        this.defence = type.getDefence();
        this.accuracy = type.getAccuracy();
        this.healthMax = defence*20;
        this.health = healthMax;
        this.college = college;
        this.type = type;
        this.sailingTexture = new Texture(Gdx.files.internal("ship (1).png"));
        setupShip();
    }

    public Ship(ShipType type, College college, String texturePath) {
        this.name = college.getName() + " " + type.getName();
        this.attack = type.getAttack();
        this.defence = type.getDefence();
        this.accuracy = type.getAccuracy();
        this.healthMax = defence*20;
        this.health = healthMax;
        this.college = college;
        this.type = type;
        this.sailingTexture = new Texture(Gdx.files.internal(texturePath));
        setupShip();
    }

    public Ship(ShipType type, String name, College college) {
	    this(type, college);
	    this.name = name;
    }

    public Ship(int attack, int defence, int accuracy, ShipType type, College college, String name, boolean isBoss) {
        this.attack = attack;
        this.defence = defence;
        this.accuracy = accuracy;
        this.type = type;
        this.name = name;
        this.healthMax = defence*20;
        this.college = college;
        this.health = healthMax;
        this.sailingTexture = new Texture(Gdx.files.internal("ship (1).png"));
        this.isBoss = isBoss;
        setupShip();
    }

    public void setupShip(){
        this.setWidth(this.sailingTexture.getWidth());
        this.setHeight(this.sailingTexture.getHeight());
        this.setOriginCentre();
        this.setMaxSpeed(200);
        this.setDeceleration(20);
        this.setEllipseBoundary();
    }

    public void playerMove(float dt) {
        this.setAccelerationXY(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.rotateBy(90 * dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            this.rotateBy(-90 * dt );
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            this.setAnchor(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            this.setAnchor(true);
        }
    }

    public void damage(int amt){
    	health = health - amt;
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

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getAccuracy() { return accuracy; }

    public int getHealth() {
        return health;
    }

	public String getType() {
		return type.getName();
	}

	public Texture getSailingTexture() { return this.sailingTexture; }

    public boolean getIsBoss() { return this.isBoss; }

	public void setName(String name) { this.name = name; }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
        this.healthMax = defence * 20;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setType(ShipType type) { this.type = type; }

}