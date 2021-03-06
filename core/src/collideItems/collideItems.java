package collideItems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public abstract class collideItems extends Sprite {
    private World world;
    private Body body;
    private float x1;
    private float y1;
    private String collisionName;

    public collideItems(World world, String name, float x, float y, float x1, float y1, String collisionName) {
        super(new Texture(name));
        this.world = world;
        this.x1 = x1;
        this.y1 = y1;
        this.collisionName = collisionName;
        setPosition(x + x1, y + y1);
        createBody();
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public void setCollisionName(String collisionName) {
        this.collisionName = collisionName;
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2 ) / GameInfo.PPM,
                (getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
//        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(collisionName);
//        fixture.setSensor(true);
        shape.dispose();
    }
}
