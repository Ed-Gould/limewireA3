package com.rear_admirals.york_pirates.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.base.BaseScreen;

public class MiniGameScreen extends BaseScreen {
    private int tileSize = 64;
    private int tileCountWidth = 30;
    private int tileCountHeight = 30;

    private final int mapWidth = tileSize * tileCountWidth;
    private final int mapHeight = tileSize * tileCountHeight;
    private TiledMap tiledMap;

    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera tiledCamera;

    private Label pointsLabel;

    public MiniGameScreen(final PirateGame main){
        super(main);
        System.out.println("Mini Game");

        Table uiTable = new Table();
        Label pointsTextLabel = new Label("Points: ", main.getSkin(),"default_black");
        pointsLabel = new Label(Integer.toString(main.getPlayer().getPoints()), main.getSkin(), "default_black");
        pointsLabel.setAlignment(Align.left);
        uiTable.add(pointsTextLabel);
        uiTable.add(pointsLabel).width(pointsTextLabel.getWidth());
        uiTable.align(Align.topRight);
        uiTable.setFillParent(true);

        uiStage.addActor(uiTable);


        tiledMap = new TmxMapLoader().load("miniGame_map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false, viewwidth, viewheight);
        tiledCamera.update();

        InputMultiplexer im = new InputMultiplexer(uiStage, mainStage);
        Gdx.input.setInputProcessor(im);

    }
    @Override
    public void render(float delta) {

        tiledMapRenderer.render();
        super.render(delta);
        tiledMapRenderer.render();

    }

    @Override
    public void update(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            System.out.println("ESCAPE");
            pirateGame.setScreen(pirateGame.getSailingScene());
        }

        cameraMove();

        pointsLabel.setText(Integer.toString(pirateGame.getPlayer().getPoints()));
    }

    public void cameraMove(){
        Camera mainCamera = mainStage.getCamera();

        // bound camera to layout
        mainCamera.position.x = MathUtils.clamp(mainCamera.position.x, viewwidth / 2, mapWidth - viewwidth / 2);
        mainCamera.position.y = MathUtils.clamp(mainCamera.position.y, viewheight / 2, mapHeight - viewheight / 2);
        mainCamera.update();

        // adjust tilemap camera to stay in sync with main camera
        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);
    }

}
