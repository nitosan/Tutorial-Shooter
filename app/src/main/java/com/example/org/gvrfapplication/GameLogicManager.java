package com.example.org.gvrfapplication;


import android.graphics.Color;

import org.gearvrf.GVRContext;
import org.gearvrf.GVREventListeners;
import org.gearvrf.GVRPicker;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.ITouchEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nite.luo on 2/28/2018.
 */

public class GameLogicManager {

    public static final int MAX_TARGETS_NUM = 10;
    private GVRContext mContext;
    private GVRScene mScene;
    private List<ShooterTargetObject> mTargets;

    public GameLogicManager(GVRContext context, GVRScene scene) {
        mContext = context;
        mScene = scene;
        mTargets = new ArrayList<>(MAX_TARGETS_NUM);

        initTargets();
    }

    public void initTargets() {
        for (int i = 0; i < MAX_TARGETS_NUM; i++) {
            ShooterTargetObject t = new ShooterTargetObject(mContext);
            mTargets.add(t);
            mScene.addSceneObject(t);
        }
    }

    private ITouchEvents mControllerHandler = new GVREventListeners.TouchEvents() {

        public void onEnter(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            sceneObj.getRenderData().getMaterial().setColor(Color.RED);
        }

        public void onTouchStart(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            sceneObj.getRenderData().getMaterial().setColor(Color.BLUE);
        }

        public void onTouchEnd(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            sceneObj.getRenderData().getMaterial().setColor(Color.RED);

            sceneObj.getComponent(GVRC)
        }

        public void onExit(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            sceneObj.getRenderData().getMaterial().setColor(Color.GRAY);
        }
    };
}
