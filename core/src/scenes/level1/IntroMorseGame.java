package scenes.level1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.codeclan.game.GameMain;

public class IntroMorseGame implements Screen {

    private GameMain parent;
    private Stage stage;

    Table table;
    Skin skin;

    Label startTranslate;
    Label startWords;
    Label startMorse;

    public IntroMorseGame(GameMain gameMain) {
        parent = gameMain;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

        startTranslate = new Label("Ready to train? Translate:", skin);
        startWords = new Label("L E A R N   C O D I N G", skin);
        startMorse = new Label( "", skin);

        table.add(startTranslate).height(50).colspan(5);
        table.row().pad(5, 0, 5, 0);
        table.add(startWords).height(50).colspan(5);
        table.row().pad(5, 0, 5, 0);
        table.add(startMorse).height(50).colspan(5);
        table.row().pad(5, 0, 5, 0);
        table.add().height(50).colspan(5);
        table.row().pad(5, 0, 5, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.55f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            parent.changeScreen(GameMain.MORSEGAME);
        }
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
    }
}
