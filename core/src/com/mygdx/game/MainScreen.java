package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MainScreen implements Screen {

    private Box2DTutorial parent; // a field to store our orchestrator
    private B2DModel model;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera cam;


    // our constructor with a Box2DTutorial argument
    public MainScreen(Box2DTutorial box2dTutorial){
        parent = box2dTutorial;     // setting the argument to our field.
        model = new B2DModel();
        cam = new OrthographicCamera(32,24);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
