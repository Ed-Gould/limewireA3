package com.rear_admirals.york_pirates.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rear_admirals.york_pirates.PirateGame;

public abstract class BaseScreen implements Screen {

    protected Stage mainStage;
    protected Stage uiStage;

    protected PirateGame pirateGame;

    protected final int viewwidth = 1920;
    protected final int viewheight = 1080;

    public BaseScreen(PirateGame game){
        this.pirateGame = game;
        this.mainStage = new Stage(new FitViewport(this.viewwidth, this.viewheight));
        this.uiStage = new Stage(new FitViewport(this.viewwidth, this.viewheight));
    }

    public abstract void update(float delta);

    public void render (float delta) {
        this.uiStage.act(delta);
        this.mainStage.act(delta);
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.mainStage.draw();
        this.uiStage.draw();
    }

    @Override
    public void dispose () {
        this.mainStage.dispose();
        this.uiStage.dispose();
    }

    public void resize(int width, int height) {
        this.uiStage.getViewport().update(width, height);
        this.mainStage.getViewport().update(width, height);
    }

    @Override
    public void show(){ }

    @Override
    public void pause() { }

    @Override
    public void hide(){ }

    @Override
    public void resume() { }
}

