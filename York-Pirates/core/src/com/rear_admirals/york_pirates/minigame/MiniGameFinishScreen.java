package com.rear_admirals.york_pirates.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.base.BaseScreen;

public class MiniGameFinishScreen extends BaseScreen {
    //Assets variables.
    private Texture winTexture;
    private Texture loseTexture;
    private Texture escape;
    //Screen variables.
    private float screenWidth;
    private float screenHeight;
    //Status.
    private boolean isDead;


    private SpriteBatch batch;

    public MiniGameFinishScreen(final PirateGame main, boolean isDead){
        super(main);
        //Initialize.
        this.isDead = isDead;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        winTexture = new Texture("minigamewin.png");
        loseTexture = new Texture("minigamelose.png");
        escape = new Texture("escape.png");
        // Adding extra points to the main game.
        if(!isDead){
            pirateGame.getPlayer().addPoints(100);
        }

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //Show different messages in different status of the mini game.
        if(isDead){
            batch.draw(loseTexture,25*screenWidth/640,150*screenHeight/480,600*screenWidth/640,200*screenHeight/480);
            batch.draw(escape,200*screenWidth/640,50*screenHeight/480,300*screenWidth/640,100*screenHeight/480);
        }else{
            batch.draw(winTexture,25*screenWidth/640,150*screenHeight/480,600*screenWidth/640,200*screenHeight/480);
            batch.draw(escape,200,50,300*screenWidth/640,100*screenHeight/480);
        }
        batch.end();

    }

    @Override
    public void update(float delta){
        //Set way to return to the sailing mode.
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.out.println("ESCAPE");
            pirateGame.setScreen(pirateGame.getSailingScene());
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
