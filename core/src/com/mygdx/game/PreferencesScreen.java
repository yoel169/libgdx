package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PreferencesScreen implements Screen {

    private Box2DTutorial parent; // a field to store our orchestrator
    private Stage stage;

    // our new fields
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;

    // our constructor with a Box2DTutorial argument
    public PreferencesScreen(Box2DTutorial box2dTutorial){
        parent = box2dTutorial;     // setting the argument to our field.
        //create stage and set it as input processor
        stage = new Stage(new ScreenViewport());

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        //load GUI Skin
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        //sliders
        final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
        volumeMusicSlider.setValue( parent.getPreferences().getMusicVolume() );
        volumeMusicSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setMusicVolume( volumeMusicSlider.getValue() );
                return false;
            }
        });


        final Slider soundMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
        soundMusicSlider.setValue( parent.getPreferences().getSoundVolume() );
        soundMusicSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setSoundVolume( soundMusicSlider.getValue() );
                return false;
            }
        });


        //checkboxes
        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked( parent.getPreferences().isMusicEnabled() );
        musicCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().setMusicEnabled( enabled );
                return false;
            }
        });

        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked( parent.getPreferences().isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getPreferences().setSoundEffectsEnabled( enabled );
                return false;
            }
        });


        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin, "small"); // the extra argument here "small" is used to set the button to the smaller version instead of the big default version
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Box2DTutorial.MENU);
            }
        });

        //labels
        titleLabel = new Label( "Preferences", skin );
        volumeMusicLabel = new Label( "Music Volume", skin );
        volumeSoundLabel = new Label( "Sound Volume", skin );
        musicOnOffLabel = new Label( "Music", skin );
        soundOnOffLabel = new Label( "Sound Effect", skin );

        table.add(titleLabel).colspan(2);
        table.row().pad(10,0,0,0);
        table.add(volumeMusicLabel).left();
        table.add(volumeMusicSlider);
        table.row().pad(10,0,0,0);
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row().pad(10,0,0,0);
        table.add(volumeSoundLabel).left();
        table.add(soundMusicSlider);
        table.row().pad(10,0,0,0);
        table.add(soundOnOffLabel).left();
        table.add(soundEffectsCheckbox);
        table.row().pad(10,0,0,0);
        table.add(backButton).center();

    }

    @Override
    public void render(float delta) {
        //clear screen ready for next set of images
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //tell the stage to act and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

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
