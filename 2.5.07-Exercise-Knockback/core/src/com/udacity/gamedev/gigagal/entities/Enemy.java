package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.util.Assets;
import com.udacity.gamedev.gigagal.util.Constants;
import com.udacity.gamedev.gigagal.util.Enums.Direction;
import com.udacity.gamedev.gigagal.util.Utils;


public class Enemy {

    public Vector2 position;
    private final Platform platform;
    Direction direction;
    public int health;
    long startTime;

    public Enemy(Platform platform) {
        this.platform = platform;
        direction = Direction.RIGHT;
        position = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);
        startTime = TimeUtils.nanoTime();
        health = Constants.ENEMY_HEALTH;
    }

    public void update(float delta) {
        switch (direction) {
            case LEFT:
                position.x -= Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
            case RIGHT:
                position.x += Constants.ENEMY_MOVEMENT_SPEED * delta;
        }

        if (position.x < platform.left) {
            position.x = platform.left;
            direction = Direction.RIGHT;
        } else if (position.x > platform.right) {
            position.x = platform.right;
            direction = Direction.LEFT;
        }

        float elapsedTime = Utils.secondsSince(startTime);
        position.y = platform.top + Constants.ENEMY_CENTER.y
                + Constants.ENEMY_BOB_AMPLITUDE * (1 + MathUtils.sin(MathUtils.PI2 * elapsedTime / Constants.ENEMY_BOB_PERIOD));


    }

    public void render(SpriteBatch batch) {
        TextureRegion region = Assets.instance.enemyAssets.enemy;
        Utils.drawTextureRegion(batch, region, position, Constants.ENEMY_CENTER);
    }
}
