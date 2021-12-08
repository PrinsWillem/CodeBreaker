package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.codeclan.game.GameMain;

public class MenuScreen implements Screen {

    private GameMain parent;
    private GameMain game;
    private Stage stage;

    private Texture bg;
    private Music menuMusic;

    public MenuScreen(GameMain gameMain) {
        parent = gameMain;
        this.game = gameMain;
        stage = new Stage(new ScreenViewport());

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/The Walk.ogg"));
        menuMusic.setVolume(1);
        menuMusic.setLooping(true);
        menuMusic.play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("Skin/clean-crispy-ui.json"));

        bg = new Texture("Backgrounds/titleScreen.jpg");

        TextButton newGame = new TextButton("Play", skin);
        TextButton preferences = new TextButton(" Settings ", skin);
        TextButton exit = new TextButton("Exit", skin);

        table.add().height(475).pad(0, 0, 20, 0);
        table.row().pad(5, 0, 5, 0);
        table.row().pad(5, 0, 5, 0);
        table.add(newGame).fillX().uniformX();
        table.add(preferences).fillX().uniformX();
        table.add(exit).fillX().uniformX();

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menuMusic.stop();
                parent.changeScreen(GameMain.INTROMORSEGAME);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameMain.PREFERENCES);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menuMusic.stop();
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.55f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);
        game.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        bg.dispose();
        menuMusic.dispose();
    }
}