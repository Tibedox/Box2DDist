package ru.samsung.box2ddist;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    World world;
    Box2DDebugRenderer debugRenderer;
    OrthographicCamera camera;

    Kinematic platform;

    @Override
    public void create() {
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -9.8f), true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
        debugRenderer = new Box2DDebugRenderer();
        Static wall1 = new Static(world, 2, 4.5f, 1, 8);
        Static wall2 = new Static(world, 14, 4.5f, 1, 8);
        Static floor = new Static(world, 8, 1, 11, 1);
        Dynamic[] ball = new Dynamic[60];
        Dynamic[] brick = new Dynamic[10];
        Dynamic cross = new Dynamic(world, 7, 5, 1.5f, 2.5f, 0.8f);
        for (int i = 0; i < ball.length; i++) {
            ball[i] = new Dynamic(world, 4+i/10f, 5+i*2, 0.4f);
        }
        for (int i = 0; i < brick.length; i++) {
            brick[i] = new Dynamic(world, 5+i/10f, 5+i*2, 0.5f, 1);
        }
        platform = new Kinematic(world, 16, 3, 4, 1, -2, 0, 4);
    }

    @Override
    public void render() {
        platform.move();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.end();
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
