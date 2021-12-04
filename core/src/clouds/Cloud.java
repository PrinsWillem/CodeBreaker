package clouds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Cloud extends Sprite {
    private World world;
    private Body body;

    public Cloud(World world, String name) {
        super(new Texture("4 - Clouds/Cloud 1.png"));
        this.world = world;
        setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 150);
        createBody();
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((getWidth() / 2 ) - 21) / GameInfo.PPM,
                (getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Cloud");
//        fixture.setSensor(true);
        shape.dispose();
    }
}
