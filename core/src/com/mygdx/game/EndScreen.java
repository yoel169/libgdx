package com.mygdx.game;

import com.badlogic.gdx.Screen;

public class EndScreen implements Screen {

    private Box2DTutorial parent; // a field to store our orchestrator

    // our constructor with a Box2DTutorial argument
    public EndScreen(Box2DTutorial box2dTutorial){
        parent = box2dTutorial;     // setting the argument to our field.
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
