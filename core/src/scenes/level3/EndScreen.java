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

public class EndScreen implements Screen {

    private GameMain parent;
    private GameMain game;
    private Stage stage;

    private OrthographicCamera mainCamera;

    Skin skin;

    private Texture bg;

    private Music endMusic;

    public EndScreen(GameMain gameMain) {
        parent = gameMain;
        this.game = gameMain;
        stage = new Stage(new ScreenViewport());

        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        endMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Cars.ogg"));
        endMusic.setVolume(1);
        endMusic.setLooping(true);
        endMusic.play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

        bg = new Texture("Backgrounds/EndScreen.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.55f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg, 480, 320);
        game.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();

        mainCamera.position.set(960, 640, 0);
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            endMusic.stop();
            parent.changeScreen(GameMain.MENU);
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
        endMusic.dispose();
    }
}
