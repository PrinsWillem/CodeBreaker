package scenes;

import collideItems.Platform;
import collideItems.SouthEdgeFront;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.codeclan.game.GameMain;
import helpers.GameInfo;
import player.Player;

public class MainScreen implements Screen, ContactListener {

    private GameMain parent;
    private GameMain game;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    private World world;

    private Texture bg;
    private Player player;
    private Platform platform;
    private SouthEdgeFront southEdgeFront;

    private Music menuMusic;
    private Sound waves;
    private Sound thunder;

    public MainScreen(GameMain gameMain) {
        parent = gameMain;
        this.game = gameMain;

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/music.ogg"));
        menuMusic.setVolume(1);
        menuMusic.setLooping(true);
        menuMusic.play();

        waves = Gdx.audio.newSound(Gdx.files.internal("Sounds/waves.ogg"));
        waves.play();

        thunder= Gdx.audio.newSound(Gdx.files.internal("Sounds/ThunderStorm.ogg"));
        thunder.play();

        mainCamera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);
        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM,
                GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(this);

        bg = new Texture("Backgrounds/ship1.jpg");
        player = new Player(world,GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        platform = new Platform(world, "Backgrounds/ShipCollision/platform.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        southEdgeFront = new SouthEdgeFront(world, "Backgrounds/ShipCollision/WallShipFront.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
    }

    void update(float dt) {

        float x = 0, y = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.setWalkingU(true);
            y += 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.setWalkingD(true);
            y -= 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.setWalking(true);
            player.setChangedSide(false);
            x -= 1;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.setWalking(true);
            player.setChangedSide(true);
            x += 1;
        }else{
            player.setWalking(false);
            player.setWalkingD(false);
            player.setWalkingU(false);
        }
        player.getBody().setLinearVelocity(x, y);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();

        game.getBatch().draw(bg, 0, 0);

        player.drawPlayerIdle(game.getBatch());
        player.drawPlayerAnimation(game.getBatch());

        game.getBatch().draw(platform, platform.getX() - (platform.getWidth() / 2),
                platform.getY() - ((platform.getWidth() / 2) + 8));
        game.getBatch().draw(southEdgeFront, southEdgeFront.getX() - 160,
                southEdgeFront.getY());

        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        mainCamera.position.set(player.getX(), player.getY(), 0);
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        player.updatePlayer();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            parent.changeScreen(GameMain.MENU);
        }
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
        bg.dispose();
        player.getTexture().dispose();
        platform.getTexture().dispose();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture firstBody, secondBody;

        if(contact.getFixtureA().getUserData() == "Player"){
            firstBody = contact.getFixtureA();
            secondBody = contact.getFixtureB();
        }else{
            firstBody = contact.getFixtureB();
            secondBody = contact.getFixtureA();
        }
        System.out.println("The name of the first body is " + firstBody.getUserData());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
