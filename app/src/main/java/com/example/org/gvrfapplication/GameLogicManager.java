package com.example.org.gvrfapplication;


import android.graphics.Color;

import com.samsung.gearvrf.helper.Gvr;

import org.gearvrf.GVRContext;
import org.gearvrf.GVREventListeners;
import org.gearvrf.GVRPicker;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.IPickEvents;
import org.gearvrf.ITouchEvents;
import org.joml.Vector3f;

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
    private Vector3f mTargetAreaOrigin = new Vector3f(0,-2,-4);
    private static final float TARGET_WIDTH = 4f;
    private static final float TARGET_HEIGHT = 1f;
    private static final float TARGET_DEPTH = 1f;

    public GameLogicManager(GVRContext context, GVRScene scene) {
        mContext = context;
        mScene = scene;
        mTargets = new ArrayList<>(MAX_TARGETS_NUM);

        Gvr.setPickHandler(mPickHandler);

        initTargets();
    }

    private void initTargets() {
        for (int i = 0; i < MAX_TARGETS_NUM; i++) {
            ShooterTargetObject t = new ShooterTargetObject(mContext);
            mTargets.add(t);
            mScene.addSceneObject(t);

            genRandomPosition(t);
        }
    }

    private void genRandomPosition(GVRSceneObject obj){
        float x = (float) (Math.random() - 0.5f) * 2 * TARGET_WIDTH + mTargetAreaOrigin.x;
        float y = (float) (Math.random() - 0.5f) * 2 * TARGET_HEIGHT + mTargetAreaOrigin.y;
        float z = (float) (Math.random() - 0.5f) * 2 * TARGET_DEPTH + mTargetAreaOrigin.z;

        obj.getTransform().setPosition(x, y, z);
    }

    private ITouchEvents mPickHandler = new GVREventListeners.TouchEvents()
    {
        public void onEnter(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            setTargetColor(sceneObj, Color.RED);
        }

        public void onTouchStart(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            setTargetColor(sceneObj, Color.BLUE);
        }

        public void onTouchEnd(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            setTargetColor(sceneObj, Color.RED);
        }

        public void onExit(GVRSceneObject sceneObj, GVRPicker.GVRPickedObject pickInfo) {
            setTargetColor(sceneObj, Color.GRAY);
        }
    };

    private void setTargetColor(GVRSceneObject sceneObj, int color) {
        ShooterTargetBehavior logic = (ShooterTargetBehavior) sceneObj.getComponent(ShooterTargetBehavior.getComponentType());
        if(logic != null) {
            logic.setColor(color);
        }
    }
}
