package com.rear_admirals.york_pirates.minigame;

import com.badlogic.gdx.graphics.Texture;
import com.rear_admirals.york_pirates.base.PhysicsActor;
public class MiniGameEnemy extends PhysicsActor {
    //Enemy variables.
    public int moveSpeed = 150;
    public Texture enemyTexture;
    //Movement variables
    public int randomDirection;

    //Setup new enemy.
    public MiniGameEnemy(float x, float y){
        this.setSpeed(moveSpeed);
        this.enemyTexture = new Texture("ghost.png");
        this.setPosition(x,y);
    }

    public Texture getEnemyTexture(){return this.enemyTexture;}
    //Get a random int number between 0 to 3 as the random direction enemy will move towards.
    public void getRandomDirection(){
        this.randomDirection = (int)(Math.random()*4);
    }

    //Enemy movement AI. Change a direction when meet the wall or at the bound.
    public void enemyMovement(float dt, boolean[][] isWall){

        int x = (int)((this.getX())/64);
        int y = (int)((this.getY())/64);
        if(randomDirection ==0 ){
            if ((x - 1 < 0) || (isWall[x][y]) ||(x >= 29)) {
                getRandomDirection();
                enemyMovement(dt,isWall);
            }else{
                this.moveBy(-moveSpeed*dt,0);
            }

        }else if(randomDirection == 1) {
            if ((y - 1 < 0) || (isWall[x][y]) || (y >= 16)) {
                getRandomDirection();
                enemyMovement(dt,isWall);
            }else{
            this.moveBy(0, -moveSpeed * dt);}
        }else if(randomDirection ==2){
            if (isWall[x + 1][y]) {
                getRandomDirection();
                enemyMovement(dt,isWall);
            }else{
            this.moveBy(moveSpeed*dt,0);}
        }else if(randomDirection ==3){
            if (isWall[x][y + 1]) {
                getRandomDirection();
                enemyMovement(dt,isWall);
            }else{
            this.moveBy(0, moveSpeed * dt);}

        }

    }
}
