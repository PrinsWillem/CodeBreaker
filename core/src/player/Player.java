package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import helpers.GameInfo;

public class Player extends Sprite {

    private World world;
    private Body body;

    private Texture playerD;
    private Texture playerU;

    private TextureAtlas playerAtlas;
    private TextureAtlas playerAtlasD;
    private TextureAtlas playerAtlasU;
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion> animationD;
    private Animation<TextureRegion> animationU;
    private float elapsedTime;

    private boolean isWalking;
    private boolean isWalkingD;
    private boolean isWalkingU;
    private boolean isChangedSide;

    public Player(World world, float x, float y) {
        super(new Texture("Player/Player 1 Small.png"));
        this.world = world;
        setPosition(x - getWidth() / 2f, y - getHeight() / 2f);

        playerD = new Texture("Player/Player 1D Small.png");
        playerU = new Texture("Player/Player 1U Small.png");

        playerAtlas = new TextureAtlas("Player Animation/Player Animation Small.atlas");
        playerAtlasD = new TextureAtlas("Player Animation/Player AnimationD Small.atlas");
        playerAtlasU = new TextureAtlas("Player Animation/Player AnimationU Small.atlas");
        createBody();
    }


    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2) / GameInfo.PPM,
                (getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
//        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Player");
        shape.dispose();
    }

    public void drawPlayerIdle(SpriteBatch batch) {
        if (!isWalking) {
            if (!isChangedSide && !this.isFlipX()) {
                this.flip(true, false);
                batch.draw(this, this.getX() - (this.getWidth() / 2),
                        this.getY() - (this.getHeight() / 2));
            } else if (isChangedSide && this.isFlipX()) {
                this.flip(true, false);
                batch.draw(this, this.getX() - (this.getWidth() / 2),
                        this.getY() - (this.getHeight() / 2));
            } else if (!isWalkingD) {
                batch.draw(playerD, this.getX() - (this.getWidth() / 2),
                        this.getY() - (this.getHeight() / 2));
            } else if (!isWalkingU) {
                batch.draw(playerU, this.getX() - (this.getWidth() / 2),
                        this.getY() - (this.getHeight() / 2));
            }
        }
    }

    public void drawPlayerAnimation(SpriteBatch batch) {
        if (isWalking) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            Array<TextureAtlas.AtlasRegion> frames = playerAtlas.getRegions();

            for (TextureRegion frame : frames) {
                if (body.getLinearVelocity().x < 0 && !frame.isFlipX()) {
                    frame.flip(true, false);
                } else if (body.getLinearVelocity().x > 0 && frame.isFlipX()) {
                    frame.flip(true, false);
                }
            }
            animation = new Animation<TextureRegion>(1f / 14f, playerAtlas.getRegions());
            batch.draw(animation.getKeyFrame(elapsedTime,true), this.getX() - (this.getWidth() / 2),
                    this.getY() - (this.getHeight() / 2));
        } else if (isWalkingD) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            Array<TextureAtlas.AtlasRegion> frames = playerAtlasD.getRegions();
            animationD = new Animation<TextureRegion>(1f / 14f, playerAtlasD.getRegions());
            batch.draw(animationD.getKeyFrame(elapsedTime,true), this.getX() - (this.getWidth() / 2),
                    this.getY() - (this.getHeight() / 2));
        } else if (isWalkingU) {
            elapsedTime += Gdx.graphics.getDeltaTime();
            Array<TextureAtlas.AtlasRegion> frames = playerAtlasU.getRegions();
            animationU = new Animation<TextureRegion>(1f / 14f, playerAtlasU.getRegions());
            batch.draw(animationU.getKeyFrame(elapsedTime,true), this.getX() - (this.getWidth() / 2),
                    this.getY() - (this.getHeight() / 2));
        }
    }

    public void updatePlayer() {
        this.setPosition(body.getPosition().x * GameInfo.PPM,
                body.getPosition().y * GameInfo.PPM);
    }

    public Body getBody() {
        return this.body;
    }

    public void setWalking(boolean walking) {
        this.isWalking = walking;
    }

    public void setWalkingD(boolean walkingD) {
        this.isWalkingD = walkingD;
    }

    public void setWalkingU(boolean walkingU) {
        this.isWalkingU = walkingU;
    }

    public void setChangedSide(boolean changedSide) {
        this.isChangedSide = changedSide;
    }

}
