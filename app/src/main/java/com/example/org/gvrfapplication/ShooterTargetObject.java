package com.example.org.gvrfapplication;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.scene_objects.GVRModelSceneObject;

import java.io.IOException;

/**
 * Created by nite.luo on 2/28/2018.
 */

public class ShooterTargetObject extends GVRSceneObject {

    private GVRModelSceneObject mTarget;
    private GVRModelSceneObject mTargetShatter;

    public ShooterTargetObject(GVRContext gvrContext) {
        super(gvrContext);

        try {
            mTarget = gvrContext.getAssetLoader().loadModel("ShooterTarget.fbx");
            mTargetShatter = gvrContext.getAssetLoader().loadModel("ShooterTargetShatter.fbx");
            addChildObject(mTarget);
            addChildObject(mTargetShatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
}
