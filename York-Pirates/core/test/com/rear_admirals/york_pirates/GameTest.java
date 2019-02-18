package com.rear_admirals.york_pirates;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rear_admirals.york_pirates.Department;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.screen.SailingScreen;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.badlogic.gdx.backends.headless.HeadlessApplication;

import java.awt.*;

import static com.rear_admirals.york_pirates.PirateGame.Chemistry;

public class GameTest {

    public static Application application;

    // Before running any tests, initialise application with headless backend
    @BeforeClass
    public static void init() {
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        // Use Mockito to mock OpenGL methods since we are running headlessly
        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }


    // Clean up after testing
    @AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }
}
