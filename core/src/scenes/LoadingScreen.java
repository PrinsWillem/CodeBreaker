package scenes;

import com.badlogic.gdx.Screen;
import com.codeclan.game.GameMain;

public class LoadingScreen implements Screen {

    private GameMain parent;

    public LoadingScreen(GameMain gameMain) {
        parent = gameMain;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.changeScreen(GameMain.MENU);
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