package scenes.level3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.codeclan.game.GameMain;
import helpers.GameInfo;

public class ConclusionMorseAnswer implements Screen {

    private GameMain parent;
    private GameMain game;
    private Stage stage;

    private OrthographicCamera mainCamera;

    Table table;
    Skin skin;

    private Texture bg;

    private Music battle;

    public ConclusionMorseAnswer(GameMain gameMain) {
        parent = gameMain;
        this.game = gameMain;
        stage = new Stage(new ScreenViewport());

        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        battle = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Braveheart.ogg"));
        battle.setVolume(1);
        battle.setLooping(true);
        battle.play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

        bg = new Texture("Backgrounds/battleScreen.jpg");
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

        mainCamera.position.set(480, 320, 0);
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            battle.stop();
            parent.changeScreen(GameMain.MENU);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            battle.stop();
            parent.changeScreen(GameMain.ENDSCREEN);
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
        bg.dispose();
        battle.stop();
    }
}
