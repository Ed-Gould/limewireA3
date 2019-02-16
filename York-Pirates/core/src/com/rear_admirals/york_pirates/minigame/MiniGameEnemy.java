package com.rear_admirals.york_pirates.minigame;

import com.badlogic.gdx.graphics.Texture;
import com.rear_admirals.york_pirates.base.PhysicsActor;
public class MiniGameEnemy extends PhysicsActor {
    public String moveDirection;
    public int moveSpeed = 150;
    public Texture enemyTexture;
    public int randomDrection;

    public MiniGameEnemy(float x, float y){
        this.moveDirection = setMovementDirection();
        this.setSpeed(moveSpeed);
        this.enemyTexture = new Texture("ghost.png");
        this.setPosition(x,y);
    }

    public String setMovementDirection(){
        return "Right";
    }
    public Texture getEnemyTexture(){return this.enemyTexture;}

    public void getRandomDrection(){
        this.randomDrection = (int)(Math.random()*4);
    }

    public void enemyMovement(float dt, boolean[][] isWall){

        int x = (int)((this.getX())/64);
        int y = (int)((this.getY())/64);
        if(randomDrection ==0 ){
            if ((x - 1 < 0) || (isWall[x][y]) ||(x >= 29)) {
                getRandomDrection();
                enemyMovement(dt,isWall);
            }else{
                this.moveBy(-moveSpeed*dt,0);
            }

        }else if(randomDrection == 1) {
            if ((y - 1 < 0) || (isWall[x][y]) || (y >= 16)) {
                getRandomDrection();
                enemyMovement(dt,isWall);
            }else{
            this.moveBy(0, -moveSpeed * dt);}
        }else if(randomDrection ==2){
            if (isWall[x + 1][y]) {
                getRandomDrection();
                enemyMovement(dt,isWall);
            }else{
            this.moveBy(moveSpeed*dt,0);}
        }else if(randomDrection ==3){
            if (isWall[x][y + 1]) {
                getRandomDrection();
                enemyMovement(dt,isWall);
            }else{
            this.moveBy(0, moveSpeed * dt);}

        }

    }
}
