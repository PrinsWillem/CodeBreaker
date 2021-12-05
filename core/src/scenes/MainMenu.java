package scenes;

import collideItems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.codeclan.game.GameMain;
import helpers.GameInfo;
import player.Player;

public class MainMenu implements Screen, ContactListener {

    private GameMain game;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    private World world;

    private Texture bg;
    private Player player;
    private Platform platform;
    private SouthEdge1 southEdge1;
    private SouthEdge2 southEdge2;
    private SouthEdge3 southEdge3;
    private EastEdge eastEdge;
    private NorthEdge northEdge;
    private Room1WestEdge room1WestEdge;
    private Room2WestEdge room2WestEdge;
    private Room2EastEdge room2EastEdge;
    private Room2DoorEast room2DoorEast;
    private HallwayWestEdge1 hallwayWestEdge1;
    private HallwayEastEdge hallwayEastEdge;
    private HallwayDoorWest hallwayDoorWest;
    private HallwayDoorEast hallwayDoorEast;
    private HallwayNorthEdge1 hallwayNorthEdge1;
    private HallwayNorthEdge2 hallwayNorthEdge2;
    private HallwaySouthEdge1 hallwaySouthEdge1;

    public MainMenu(GameMain game) {
        this.game = game;

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

        bg = new Texture("Backgrounds/ship.jpg");
        player = new Player(world,GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        platform = new Platform(world, "Backgrounds/ShipCollision/platform.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        southEdge1 = new SouthEdge1(world, "Backgrounds/ShipCollision/southEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        southEdge2 = new SouthEdge2(world, "Backgrounds/ShipCollision/southEdge2.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        southEdge3 = new SouthEdge3(world, "Backgrounds/ShipCollision/southEdge3.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        eastEdge = new EastEdge(world, "Backgrounds/ShipCollision/eastEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        northEdge = new NorthEdge(world, "Backgrounds/ShipCollision/northEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room1WestEdge = new Room1WestEdge(world, "Backgrounds/ShipCollision/room1WestEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayWestEdge1 = new HallwayWestEdge1(world, "Backgrounds/ShipCollision/hallwayWestEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayDoorWest = new HallwayDoorWest(world, "Backgrounds/ShipCollision/hallwayDoorWest.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayDoorEast = new HallwayDoorEast(world, "Backgrounds/ShipCollision/hallwayDoorEast.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayNorthEdge1 = new HallwayNorthEdge1(world, "Backgrounds/ShipCollision/hallwayNorthEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayNorthEdge2 = new HallwayNorthEdge2(world, "Backgrounds/ShipCollision/hallwayNorthEdge2.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwaySouthEdge1 = new HallwaySouthEdge1(world, "Backgrounds/ShipCollision/hallwaySouthEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayEastEdge = new HallwayEastEdge(world, "Backgrounds/ShipCollision/hallwayEastEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room2EastEdge = new Room2EastEdge(world, "Backgrounds/ShipCollision/room2EastEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room2DoorEast = new Room2DoorEast(world, "Backgrounds/ShipCollision/room2DoorEast.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room2WestEdge = new Room2WestEdge(world, "Backgrounds/ShipCollision/room2WestEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
    }

    void update(float dt) {

        float x = 0, y = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += 5;
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= 5;
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.setWalking(true);
            player.setChangedSide(false);
            x -= 7;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.setWalking(true);
            player.setChangedSide(true);
            x += 7;
        }else{
            player.setWalking(false);
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
        game.getBatch().draw(southEdge1, southEdge1.getX() - 200,
                southEdge1.getY());
        game.getBatch().draw(southEdge2, southEdge2.getX() - 816,
                southEdge2.getY());
        game.getBatch().draw(southEdge3, southEdge3.getX() - 334,
                southEdge3.getY());
        game.getBatch().draw(eastEdge, eastEdge.getX() - 0,
                eastEdge.getY() - 250);
        game.getBatch().draw(northEdge, northEdge.getX() - 1370,
                northEdge.getY() - 28);
        game.getBatch().draw(room1WestEdge, room1WestEdge.getX() - 1,
                room1WestEdge.getY() - 115);
        game.getBatch().draw(hallwayWestEdge1, hallwayWestEdge1.getX() - 1,
                hallwayWestEdge1.getY() - 35);
        game.getBatch().draw(hallwayDoorWest, hallwayDoorWest.getX() - 1,
                hallwayDoorWest.getY() - -14);
        game.getBatch().draw(hallwayDoorEast, hallwayDoorEast.getX() - 1,
                hallwayDoorEast.getY() - -23);
        game.getBatch().draw(hallwayNorthEdge1, hallwayNorthEdge1.getX() - 150,
                hallwayNorthEdge1.getY() - -2);
        game.getBatch().draw(hallwayNorthEdge2, hallwayNorthEdge2.getX() - 640,
                hallwayNorthEdge2.getY() - -2);
        game.getBatch().draw(hallwaySouthEdge1, hallwaySouthEdge1.getX() - 140,
                hallwaySouthEdge1.getY() - -2);
        game.getBatch().draw(hallwayEastEdge, hallwayEastEdge.getX() - 1,
                hallwayEastEdge.getY() - 29);
        game.getBatch().draw(room2EastEdge, room2EastEdge.getX() - 1,
                room2EastEdge.getY() - 114);
        game.getBatch().draw(room2DoorEast, room2DoorEast.getX() - 1,
                room2DoorEast.getY() - -14);
        game.getBatch().draw(room2WestEdge, room2WestEdge.getX() - 1,
                room2WestEdge.getY() - 114);

        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        mainCamera.position.set(player.getX(), player.getY(), 0);
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        player.updatePlayer();
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
