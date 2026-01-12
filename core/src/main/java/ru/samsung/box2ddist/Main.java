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

    @Override
    public void create() {
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -9.8f), true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
        debugRenderer = new Box2DDebugRenderer();
        Static wall1 = new Static(world, 2, 4.5f, 1f, 8f);
        Static wall2 = new Static(world, 14, 4.5f, 1f, 8f);
        Static floor = new Static(world, 8f, 1f, 11, 1f);
        Dynamic[] ball = new Dynamic[60];
        for (int i = 0; i < ball.length; i++) {
            ball[i] = new Dynamic(world, 4f+i/10f, 5f+i*2, 0.2f);
        }
    }

    @Override
    public void render() {
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
