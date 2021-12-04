package scenes;

import clouds.Cloud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.codeclan.game.GameMain;
import helpers.GameInfo;
import player.Player;

public class MainMenu implements Screen, ContactListener {

    private GameMain game;
    private Texture bg;
    private Player player;
    private Cloud cloud;
    private World world;
    OrthographicCamera camera;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    public MainMenu(GameMain game) {
        this.game = game;

//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM,
                GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2, 0);
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(this);

        bg = new Texture("7 - Backgrounds/ship1.jpg");
        player = new Player(world, "0 - Player/Player 1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);

        cloud = new Cloud(world, "platform.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
    }

    void update(float dt) {

        float x =0, y =0;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += 1;
//            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0),
//                    player.getBody().getWorldCenter(), true);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= 1;
//            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0),
//                    player.getBody().getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= 1;
//            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0),
//                    player.getBody().getWorldCenter(), true);
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += 1;
//            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0),
//                    player.getBody().getWorldCenter(), true);
        }
        player.getBody().setLinearVelocity(x, y);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        player.updatePlayer();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        camera.position.set(player.getX(), player.getY(), 0);
//        camera.update();

//        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();

        game.getBatch().draw(bg, 0, 0);
        game.getBatch().draw(player, player.getX() - (player.getWidth() / 2),
                player.getY() - (player.getHeight() / 2));
        game.getBatch().draw(cloud, cloud.getX() - (cloud.getWidth() / 2),
                cloud.getY() - ((cloud.getWidth() / 2) + 8));

        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
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
