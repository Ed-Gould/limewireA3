package com.rear_admirals.york_pirates.minigame;

import com.badlogic.gdx.graphics.Texture;
import com.rear_admirals.york_pirates.base.PhysicsActor;

import java.awt.*;

public class MiniGameEnemy extends PhysicsActor {
    public String moveDirection;
    public int moveSpeed = 200;
    public Texture enemyTexture;

    public MiniGameEnemy(float x, float y){
        this.moveDirection = setMovementDirection();
        this.setSpeed(moveSpeed);
        this.enemyTexture = new Texture("ghost.png");
        this.setPosition(x,y);
    }

    public String setMovementDirection(){
        return "Right";
    }

    public void enemyMovement(){

    }
}
