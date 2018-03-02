package com.example.org.gvrfapplication;

import android.util.Log;

import org.gearvrf.GVRBehavior;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRSceneObject;

import java.util.List;

/**
 * Created by nite.luo on 2/28/2018.
 */

public class ShooterTargetBehavior extends GVRBehavior {

    private static final String TAG = ShooterTargetBehavior.class.getSimpleName();

    interface Listener {
        void onShot();
    }

    static private long TYPE_SHOOTER_TARGET_BEHAVIOR = newComponentType(ShooterTargetBehavior.class);
    private Listener mListener;

    protected ShooterTargetBehavior(GVRContext gvrContext) {
        super(gvrContext);
        mType = TYPE_SHOOTER_TARGET_BEHAVIOR;
    }

    static public long getComponentType(){return TYPE_SHOOTER_TARGET_BEHAVIOR;}

    public void setListener(Listener listener){
        mListener = listener;
    }

    public void removeListener() {
        mListener = null;
    }

    void onShot() {
        if(mListener != null) {
            mListener.onShot();
        }
    }

    void setColor(int color) {
        GVRSceneObject owner = getOwnerObject();
        List<GVRSceneObject> children = owner.getChildren();

        for (GVRSceneObject child : children) {
            if(child.getRenderData() != null)
                child.getRenderData().getMaterial().setColor(color);
        }
    }
}
