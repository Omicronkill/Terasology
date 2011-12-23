/*
 * Copyright 2011 Benjamin Glatzel <benjamin.glatzel@me.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.begla.blockmania.rendering.cameras;

import com.github.begla.blockmania.configuration.ConfigurationManager;
import com.github.begla.blockmania.datastructures.ViewFrustum;

import javax.vecmath.Vector3f;

/**
 * Provides global access to fonts.
 *
 * @author Benjamin Glatzel <benjamin.glatzel@me.com>
 */
public abstract class Camera {

    public static final float FOV = ((Double) ConfigurationManager.getInstance().getConfig().get("Player.fov")).floatValue();

    /* CAMERA PARAMETERS */
    protected final Vector3f _position = new Vector3f();
    protected final Vector3f _up = new Vector3f(0, 1, 0);
    protected final Vector3f _viewingDirection = new Vector3f();

    protected float _targetFov = FOV;
    protected float _activeFov = FOV - 20f;

    /* VIEW FRUSTUM */
    protected final ViewFrustum _viewFrustum = new ViewFrustum();

    /**
     * Applies the projection and modelview matrix.
     */
    public void lookThrough() {
        loadProjectionMatrix();
        loadModelViewMatrix();
    }

    /**
     * Applies the projection and the normalized modelview matrix (positioned at the origin without any offset like bobbing) .
     */
    public void lookThroughNormalized() {
        loadProjectionMatrix();
        loadNormalizedModelViewMatrix();
    }

    public abstract void loadProjectionMatrix();

    public abstract void loadModelViewMatrix();

    public abstract void loadNormalizedModelViewMatrix();


    public Vector3f getPosition() {
        return _position;
    }

    public Vector3f getViewingDirection() {
        return _viewingDirection;
    }

    public Vector3f getUp() {
        return _up;
    }

    public ViewFrustum getViewFrustum() {
        return _viewFrustum;
    }

    public void update() {
        if (_activeFov < _targetFov) {
            _activeFov += 0.5;
        } else if (_activeFov > _targetFov) {
            _activeFov -= 0.5;
        }
    }

    public void extendFov(float fov) {
        _targetFov = FOV + 10.0f;
    }

    public void resetFov() {
        _targetFov = FOV;
    }
}
