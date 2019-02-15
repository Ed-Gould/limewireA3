package com.rear_admirals.york_pirates.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.rear_admirals.york_pirates.base.PhysicsActor;

import java.util.ArrayList;

public class MiniGamePlayer extends PhysicsActor {
    public Texture playerTexture;
    public int moveSpeed = 250;
    public boolean moveLeft;
    public boolean moveRight;
    public boolean moveUp;
    public boolean moveDown;

    public MiniGamePlayer(){
        this.playerTexture = new Texture("miniGamePlayer.png");
        this.setSpeed(moveSpeed);
        this.moveLeft = true;
        this.moveRight = true;
        this.moveUp = true;
        this.moveDown = true;
    }
    @Override
    public void draw(Batch batch, float alpha){
        batch.setColor(1,1,1,alpha);
        batch.draw(new TextureRegion(playerTexture),getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
    }

    public Texture getPlayerTexture(){return this.playerTexture;}

    public void playerMove(float dt) {
        this.setAccelerationXY(0,0);
        if ((moveLeft)&&(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))) {
            this.moveBy(-(moveSpeed * dt),0);
        }
        if ((moveRight)&&(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))){
            this.moveBy(moveSpeed * dt,0);
        }
        if ((moveUp)&&(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))){
            this.moveBy(0,moveSpeed * dt);
        }
        if ((moveDown)&&(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))){
            this.moveBy(0,-(moveSpeed * dt));
        }
    }

    public boolean isDead(ArrayList<MiniGameEnemy> enemies, MiniGamePlayer player,float elementSize){
        for(MiniGameEnemy enemy : enemies){
            int x = (int)((enemy.getX())/64);
            int y = (int)((enemy.getY())/64);
            if(((int)(player.getX()/64)== x)&&((int)(player.getY()/64)==y)){

                return true;
            }
        }
        return false;
    }

    public void resetMoveAble(){
        this.moveUp = true;
        this.moveRight = true;
        this.moveLeft = true;
        this.moveDown = true;
    }
    public boolean moveAble( MiniGamePlayer player, boolean[][] isWall, boolean[][] isExit){
        int x = (int)((player.getX())/64);
        int y = (int)((player.getY())/64);
        System.out.println(x+" "+y);
        if(isExit[x][y]){
            return true;
        }
        else {
            if ((x - 1 < 0) || (isWall[x][y])) {
                this.moveLeft = false;
            }
            if ((y - 1 < 0) || (isWall[x][y])) {
                this.moveDown = false;
            }
            if (isWall[x+1][y]) {
                this.moveRight = false;
            }
            if (isWall[x][y+1]) {
                this.moveUp = false;
            }
            return false;
        }

    }

}
