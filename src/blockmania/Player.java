/*
 *  Copyright 2011 Benjamin Glatzel <benjamin.glatzel@me.com>.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package blockmania;

import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Benjamin Glatzel <benjamin.glatzel@me.com>
 */
public class Player extends RenderObject {

    boolean playerModelHidden = false;
    float yaw = 0.0f;
    float pitch = 0.0f;
    float walkingSpeed = 0.01f;
    Vector3f acc = new Vector3f(0.0f, 0.0f, 0.0f);
    // The parent world
    World parent = null;

    public Player(World parent) {
        this.parent = parent;
        position = new Vector3f(0.0f, 64.0f, 0.0f);
    }

    /*
     * Positions the player within the world
     * and adjusts the players view accordingly.
     *
     * TODO: Create and render a player mesh.
     */
    @Override
    public void render() {
        move();
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(-position.x, -position.y, -position.z);
    }

    public void yaw(float diff) {
        yaw += diff;
    }

    public void pitch(float diff) {
        pitch += diff;
    }

    void move() {

        if (!parent.isHitting(new Vector3f(getPosition().x, getPosition().y - 4.0f, getPosition().z))) {
            if (acc.y > -1.0f) {
                acc.y -= 0.01f;
            }
        } else {
            acc.y = 0.0f;
        }

        getPosition().x += acc.x;
        getPosition().y += acc.y;
        getPosition().z += acc.z;


        if (acc.x > 0.0f) {
            acc.x -= 0.01f;
        } else if (acc.x < 0.0f) {
            acc.x += 0.01f;
        }

        if (acc.y > 0.0f) {
            acc.y -= 0.01f;
        } else if (acc.y < 0.0f) {
            acc.y += 0.01f;
        }

        if (acc.z > 0.0f) {
            acc.z -= 0.01f;
        } else if (acc.z < 0.0f) {
            acc.z += 0.01f;
        }
    }

    public void walkForward() {
        acc.x += walkingSpeed * (float) Math.sin(Math.toRadians(yaw));
        acc.z -= walkingSpeed * (float) Math.cos(Math.toRadians(yaw));
    }

    public void walkBackwards() {
        acc.x -= walkingSpeed * (float) Math.sin(Math.toRadians(yaw));
        acc.z += walkingSpeed * (float) Math.cos(Math.toRadians(yaw));
    }

    public void strafeLeft() {
        acc.x += walkingSpeed * (float) Math.sin(Math.toRadians(yaw - 90));
        acc.z -= walkingSpeed * (float) Math.cos(Math.toRadians(yaw - 90));
    }

    public void strafeRight() {
        acc.x += walkingSpeed * (float) Math.sin(Math.toRadians(yaw + 90));
        acc.z -= walkingSpeed * (float) Math.cos(Math.toRadians(yaw + 90));
    }
}