package com.example.org.gvrfapplication;

import com.samsung.gearvrf.helper.Gvr;

import org.gearvrf.GVRCollider;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMeshCollider;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRSphereCollider;
import org.gearvrf.animation.GVRAnimator;
import org.gearvrf.scene_objects.GVRModelSceneObject;

import java.io.IOException;

/**
 * Created by nite.luo on 2/28/2018.
 */

public class ShooterTargetObject extends GVRSceneObject {

    private GVRSceneObject mTarget;
    private GVRModelSceneObject mTargetShatter;
    private ShooterTargetBehavior mBehavior;
    private GVRAnimator mAnimator;

    public ShooterTargetObject(GVRContext gvrContext) {
        super(gvrContext);

        try {
//            mTarget = gvrContext.getAssetLoader().loadModel("ShooterTarget.fbx");
//            mTarget.getTransform().setScale(0.01f,0.01f,0.01f);
            mTarget = Gvr.createCube();
            mTarget.getTransform().setScale(0.4f,0.4f,0.4f);
            mTarget.setName("ShooterTarget");
            mTargetShatter = gvrContext.getAssetLoader().loadModel("ShooterTargetShatter.fbx");
            mTargetShatter.getTransform().setScale(0.01f,0.01f,0.01f);
            mTargetShatter.setName("ShooterTargetShatter");
            addChildObject(mTarget);
            addChildObject(mTargetShatter);
            mTargetShatter.setEnable(false);

            mAnimator = (GVRAnimator) mTargetShatter.getComponent(GVRAnimator.getComponentType());

        } catch (IOException e) {
            e.printStackTrace();
        }

        GVRSphereCollider collider = new GVRSphereCollider(getGVRContext());
        collider.setRadius(0.3f);

        attachComponent(collider);

        mBehavior = new ShooterTargetBehavior(gvrContext);
        mBehavior.setListener(new ShooterTargetBehavior.Listener() {
            @Override
            public void onShot() {
                if(mAnimator != null) {
                    mTargetShatter.setEnable(true);
                    mAnimator.start();
                }
            }
        });

        attachComponent(mBehavior);
    }

    //

    private void attachMeshCollider(GVRSceneObject sceneObject)
    {
        sceneObject.attachComponent(new GVRMeshCollider(getGVRContext(), false));
    }

    private void attachSphereCollider(GVRSceneObject sceneObject)
    {
        sceneObject.attachComponent(new GVRSphereCollider(getGVRContext()));
    }

    private void attachBoundsCollider(GVRSceneObject sceneObject)
    {
        sceneObject.attachComponent(new GVRMeshCollider(getGVRContext(), true));
    }
}
