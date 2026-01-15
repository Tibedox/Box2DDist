package ru.samsung.box2ddist;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Kinematic {
    Body body;
    float vx, vy;
    float rotation;

    public Kinematic(World world, float x, float y, float width, float height, float vx, float vy, float rotation) {
        this.vx = vx;
        this.vy = vy;
        this.rotation = rotation;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(x, y));

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2f, height/2f);
        body.createFixture(shape, 0.0f);
        shape.dispose();
        body.setLinearVelocity(vx, vy);
        body.setAngularVelocity(rotation);
    }

    void move() {
        if(body.getPosition().x < 0 || body.getPosition().x > 16){
            vx = -vx;
            body.setLinearVelocity(vx, vy);
        }
    }
}
