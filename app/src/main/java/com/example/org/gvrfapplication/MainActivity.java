package com.example.org.gvrfapplication;

import android.graphics.Color;
import android.os.Bundle;

import com.samsung.gearvrf.helper.ArrowIndicator;
import com.samsung.gearvrf.helper.Gvr;
import com.samsung.gearvrf.helper.TargetPointer;

import org.gearvrf.GVRActivity;
import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRCameraRig;
import org.gearvrf.GVRComponent;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRCursorController;
import org.gearvrf.GVREventListeners;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRMaterial;
import org.gearvrf.GVRPicker;
import org.gearvrf.GVRRenderData;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.ITouchEvents;
import org.gearvrf.io.GVRInputManager;
import org.gearvrf.scene_objects.GVRModelSceneObject;
import org.gearvrf.scene_objects.GVRSphereSceneObject;

public class MainActivity extends GVRActivity {

    private GVRScene mScene;
    private GVRContext mGvrContext;
    private GVRCursorController mController;
    private GVRCameraRig mCameraRig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Set Main Scene
         * It will be displayed when app starts
         */
        setMain(new Main());
    }

    private final class Main extends GVRMain {

        @Override
        public void onInit(GVRContext gvrContext) throws Throwable {

            Gvr.init(gvrContext);

            mGvrContext = gvrContext;
            mScene = gvrContext.getMainScene();
            mCameraRig = mScene.getMainCameraRig();

            //Skybox
            GVRTexture skybox_texture = gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.skybox));
            GVRMaterial skybox_material = new GVRMaterial(gvrContext);
            skybox_material.setMainTexture(skybox_texture);

            GVRSphereSceneObject skybox = new GVRSphereSceneObject(gvrContext, false, skybox_material);
            skybox.getTransform().setScale(200.0f, 200.0f, 200.0f);

            gvrContext.getMainScene().addSceneObject(skybox);

            GVRSceneObject cube = Gvr.createCube();
            cube.getTransform().setPosition(0,0,-4);
            cube.getTransform().setScale(0.5f, 0.5f, 0.5f);
            mScene.addSceneObject(cube);

            GVRSceneObject pointer = new GVRSceneObject(mGvrContext);

            GVRModelSceneObject arrow = mGvrContext.getAssetLoader().loadModel("arrow.fbx");
            arrow.getTransform().setPosition(0,0,-1f);
            arrow.getTransform().setRotationByAxis(90, 1, 0, 0);
            arrow.getTransform().setScale(.5f, .5f, .5f);

            pointer.addChildObject(arrow);
            pointer.getTransform().setPosition(0,0.5f,-2);
            mCameraRig.addChildObject(pointer);

            TargetPointer targetPointer = new TargetPointer(mGvrContext);
            targetPointer.setTarget(cube);
            pointer.attachComponent(targetPointer);

        }

        @Override
        public SplashMode getSplashMode() {
            return SplashMode.NONE;
        }

        @Override
        public void onStep() {
            //Add update logic here
        }
    }
}
