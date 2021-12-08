package scenes.level2;


import collideItems.*;

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

public class FindTheCaptain implements Screen, ContactListener {

    private GameMain parent;
    private GameMain game;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    private World world;

    private Texture bg;
    private Player player;
    private Captain captain;
    private Platform platform;

    private SouthEdge1 southEdge1;
    private SouthEdge2 southEdge2;
    private SouthEdge3 southEdge3;
    private EastEdge eastEdge;
    private NorthEdge northEdge;

    private Room1WestEdge room1WestEdge;
    private Room1EastEdge1 room1EastEdge1;
    private Room1EastEdge2 room1EastEdge2;
    private Room1EastEdge3 room1EastEdge3;

    private Room2WestEdge room2WestEdge;
    private Room2EastEdge room2EastEdge;
    private Room2DoorEast room2DoorEast;

    private Room3EastEdge room3EastEdge;
    private Room3WestEdge room3WestEdge;

    private Room4WestEdge room4WestEdge;
    private Room4EastEdge1 room4EastEdge1;
    private Room4EastEdge2 room4EastEdge2;

    private Room5EastEdge1 room5EastEdge1;
    private Room5EastEdge2 room5EastEdge2;
    private Room5EastEdge3 room5EastEdge3;

    private StairsSouthEdge stairsSouthEdge;
    private StairsNorthEdge stairsNorthEdge;
    private StairsCollision stairsCollision;

    private HallwayWestEdge1 hallwayWestEdge1;
    private HallwayWestEdge2 hallwayWestEdge2;
    private HallwayEastEdge hallwayEastEdge;
    private HallwayDoorWest hallwayDoorWest;
    private HallwayDoorEast hallwayDoorEast;
    private HallwayNorthEdge1 hallwayNorthEdge1;
    private HallwayNorthEdge2 hallwayNorthEdge2;
    private HallwaySouthEdge1 hallwaySouthEdge1;
    private HallwaySouthEdge2 hallwaySouthEdge2;
    private HallwaySouthEdge3 hallwaySouthEdge3;
    private HallwaySouthEdge4 hallwaySouthEdge4;
    private HallwaySouthEdge5 hallwaySouthEdge5;
    private HallwaySouthEdge6 hallwaySouthEdge6;


    private Gun gun;
    private Gun gun1;
    private ShipLights shipLights;
    private Shades shades;
    private TopBeams topBeams;
    private Doors doors;
    private Embellishment embellishment;

    private Music menuMusic;
    private Sound waves;
    private Sound thunder;

    public FindTheCaptain(GameMain gameMain) {
        parent = gameMain;
        this.game = gameMain;

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Bing Crosby.ogg"));
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

        bg = new Texture("Backgrounds/ship3.png");
        player = new Player(world,GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        captain = new Captain(world, "Captain/captain Small.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        platform = new Platform(world, "Backgrounds/ShipCollision/platform.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        gun = new Gun(world, "Backgrounds/ShipCollision/Canon.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        gun1 = new Gun(world, "Backgrounds/ShipCollision/CanonShade.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        shipLights = new ShipLights(world, "Backgrounds/ShipCollision/Shiplights.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        shades = new Shades(world, "Backgrounds/ShipCollision/Shades.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        topBeams = new TopBeams(world, "Backgrounds/ShipCollision/TopBeams.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        doors = new Doors(world, "Backgrounds/ShipCollision/Doors.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        embellishment = new Embellishment(world, "Backgrounds/ShipCollision/embellishment.png", GameInfo.WIDTH / 2,
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

        // room 1
        room1WestEdge = new Room1WestEdge(world, "Backgrounds/ShipCollision/room1WestEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room1EastEdge1 = new Room1EastEdge1(world, "Backgrounds/ShipCollision/room1EastEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room1EastEdge2 = new Room1EastEdge2(world, "Backgrounds/ShipCollision/room1EastEdge2.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room1EastEdge3 = new Room1EastEdge3(world, "Backgrounds/ShipCollision/room1EastEdge3.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);

        // room 2
        room2EastEdge = new Room2EastEdge(world, "Backgrounds/ShipCollision/room2EastEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room2DoorEast = new Room2DoorEast(world, "Backgrounds/ShipCollision/room2DoorEast.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room2WestEdge = new Room2WestEdge(world, "Backgrounds/ShipCollision/room2WestEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);

        // room 3
        room3EastEdge = new Room3EastEdge(world, "Backgrounds/ShipCollision/room3EastEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room3WestEdge = new Room3WestEdge(world, "Backgrounds/ShipCollision/room3WestEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);

        // room 4
        room4WestEdge = new Room4WestEdge(world, "Backgrounds/ShipCollision/room4WestEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room4EastEdge1 = new Room4EastEdge1(world, "Backgrounds/ShipCollision/room4EastEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room4EastEdge2 = new Room4EastEdge2(world, "Backgrounds/ShipCollision/room4EastEdge2.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);

        // room 5
        room5EastEdge1 = new Room5EastEdge1(world, "Backgrounds/ShipCollision/room5EastEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room5EastEdge2 = new Room5EastEdge2(world, "Backgrounds/ShipCollision/room5EastEdge2.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        room5EastEdge3 = new Room5EastEdge3(world, "Backgrounds/ShipCollision/room5EastEdge3.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);

        // stairs
        stairsSouthEdge = new StairsSouthEdge(world, "Backgrounds/ShipCollision/stairsSouthEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        stairsNorthEdge = new StairsNorthEdge(world, "Backgrounds/ShipCollision/stairsNorthEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        stairsCollision = new StairsCollision(world, "Backgrounds/ShipCollision/stairsCollision1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);

        // hallway
        hallwayWestEdge1 = new HallwayWestEdge1(world, "Backgrounds/ShipCollision/hallwayWestEdge1.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayWestEdge2 = new HallwayWestEdge2(world, "Backgrounds/ShipCollision/hallwayWestEdge2.png", GameInfo.WIDTH / 2,
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
        hallwaySouthEdge2 = new HallwaySouthEdge2(world, "Backgrounds/ShipCollision/hallwaySouthEdge2.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwaySouthEdge3 = new HallwaySouthEdge3(world, "Backgrounds/ShipCollision/hallwaySouthEdge3.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwaySouthEdge4 = new HallwaySouthEdge4(world, "Backgrounds/ShipCollision/hallwaySouthEdge4.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwaySouthEdge5 = new HallwaySouthEdge5(world, "Backgrounds/ShipCollision/hallwaySouthEdge5.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwaySouthEdge6 = new HallwaySouthEdge6(world, "Backgrounds/ShipCollision/hallwaySouthEdge6.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);
        hallwayEastEdge = new HallwayEastEdge(world, "Backgrounds/ShipCollision/hallwayEastEdge.png", GameInfo.WIDTH / 2,
                GameInfo.HEIGHT / 2);


    }

    void update(float dt) {

        float x = 0, y = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.setWalkingU(true);
            y += 2;
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.setWalkingD(true);
            y -= 2;
        }else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.setWalking(true);
            player.setChangedSide(false);
            x -= 2;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.setWalking(true);
            player.setChangedSide(true);
            x += 2;
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

        // characters
        game.getBatch().draw(captain, captain.getX() - (captain.getWidth() / 2),
                captain.getY() - (platform.getWidth() / 2) + 75);

        // embellishment
        game.getBatch().draw(platform, platform.getX() - (platform.getWidth() / 2),
                platform.getY() - ((platform.getWidth() / 2) + 8));
        game.getBatch().draw(gun, gun.getX() - (gun.getWidth() / 2),
                gun.getY() - 565);
        game.getBatch().draw(gun1, gun1.getX() - (gun1.getWidth() / 2),
                gun1.getY() - 521);
        game.getBatch().draw(shades, shades.getX() + 349,
                shades.getY() - 770);
        game.getBatch().draw(topBeams, topBeams.getX() + 887,
                topBeams.getY() - 543);
        game.getBatch().draw(doors, doors.getX() + 592,
                doors.getY() - 622);


        // edges
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

        // room 1
        game.getBatch().draw(room1WestEdge, room1WestEdge.getX() - 1,
                room1WestEdge.getY() - 115);
        game.getBatch().draw(room1EastEdge1, room1EastEdge1.getX() - 7,
                room1EastEdge1.getY() - 34);
        game.getBatch().draw(room1EastEdge2, room1EastEdge2.getX() - 7,
                room1EastEdge2.getY() - 34);
        game.getBatch().draw(room1EastEdge3, room1EastEdge3.getX() - 7,
                room1EastEdge3.getY() - -12);

        // room 2
        game.getBatch().draw(room2EastEdge, room2EastEdge.getX() - 1,
                room2EastEdge.getY() - 114);
        game.getBatch().draw(room2DoorEast, room2DoorEast.getX() - 1,
                room2DoorEast.getY() - -14);
        game.getBatch().draw(room2WestEdge, room2WestEdge.getX() - 1,
                room2WestEdge.getY() - 114);

        // room 3
        game.getBatch().draw(room3EastEdge, room3EastEdge.getX() - 1,
                room3EastEdge.getY() - 62);
        game.getBatch().draw(room3WestEdge, room3WestEdge.getX() - 1,
                room3WestEdge.getY() - 62);


        // room 4
        game.getBatch().draw(room4WestEdge, room4WestEdge.getX() - 1,
                room4WestEdge.getY() - 62);
        game.getBatch().draw(room4EastEdge1, room4EastEdge1.getX() - 1,
                room4EastEdge1.getY() - 18);
        game.getBatch().draw(room4EastEdge2, room4EastEdge2.getX() - 1,
                room4EastEdge2.getY() - -22);

        // room 5
        game.getBatch().draw(room5EastEdge1, room5EastEdge1.getX() - 1,
                room5EastEdge1.getY() - 16);
        game.getBatch().draw(room5EastEdge2, room5EastEdge2.getX() - 1,
                room5EastEdge2.getY() - 1);
        game.getBatch().draw(room5EastEdge3, room5EastEdge3.getX() - 1,
                room5EastEdge3.getY() - 1);

        // stairs
        game.getBatch().draw(stairsSouthEdge, stairsSouthEdge.getX() - 50,
                stairsSouthEdge.getY() - -7);
        game.getBatch().draw(stairsNorthEdge, stairsNorthEdge.getX() - 50,
                stairsNorthEdge.getY() - 1);
        game.getBatch().draw(stairsCollision, stairsCollision.getX() - 50,
                stairsCollision.getY() + 30);

        // hallway
        game.getBatch().draw(hallwayWestEdge1, hallwayWestEdge1.getX() - 1,
                hallwayWestEdge1.getY() - 35);
        game.getBatch().draw(hallwayWestEdge2, hallwayWestEdge2.getX() - 1,
                hallwayWestEdge2.getY() - 29);
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
        game.getBatch().draw(hallwaySouthEdge2, hallwaySouthEdge2.getX() - 74,
                hallwaySouthEdge2.getY() - -2);
        game.getBatch().draw(hallwaySouthEdge3, hallwaySouthEdge3.getX() - 22,
                hallwaySouthEdge3.getY() - -0);
        game.getBatch().draw(hallwaySouthEdge4, hallwaySouthEdge4.getX() - 184,
                hallwaySouthEdge4.getY() - -6);
        game.getBatch().draw(hallwaySouthEdge5, hallwaySouthEdge5.getX() - 38,
                hallwaySouthEdge5.getY() - -6);
        game.getBatch().draw(hallwaySouthEdge6, hallwaySouthEdge6.getX() - 185,
                hallwaySouthEdge6.getY() - -6);
        game.getBatch().draw(hallwayEastEdge, hallwayEastEdge.getX() - 1,
                hallwayEastEdge.getY() - 29);

        game.getBatch().draw(embellishment, embellishment.getX() + 347,
                embellishment.getY() - 1151);
        game.getBatch().draw(shipLights, shipLights.getX() + 378,
                shipLights.getY() - 693);



        game.getBatch().end();

//        debugRenderer.render(world, box2DCamera.combined);

        mainCamera.position.set(player.getX(), player.getY(), 0);
        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        player.updatePlayer();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            menuMusic.stop();
            waves.stop();
            thunder.stop();
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
        world.dispose();
        debugRenderer.dispose();
        bg.dispose();
        player.getTexture().dispose();
        platform.getTexture().dispose();
        menuMusic.dispose();
        waves.dispose();
        thunder.dispose();
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
        if(firstBody.getUserData() == "Player" && secondBody.getUserData() == "Captain"){
            menuMusic.stop();
            waves.stop();
            thunder.stop();
            parent.changeScreen(GameMain.INTROMORSEANSWER);
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
