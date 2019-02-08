package com.rear_admirals.york_pirates.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rear_admirals.york_pirates.base.PhysicsActor;

public class MiniGamePlayer extends PhysicsActor {
    public boolean alive;
    public Texture playerTexture;
    public int moveSpeed = 250;

    public MiniGamePlayer(){
        this.alive = true;
        this.playerTexture = new Texture("miniGamePlayer.png");
        this.setSpeed(moveSpeed);
    }
    @Override
    public void draw(Batch batch, float alpha){
        batch.setColor(1,1,1,alpha);
        batch.draw(new TextureRegion(playerTexture),getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
    }

    public Texture getPlayerTexture(){return this.playerTexture;}

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

}
