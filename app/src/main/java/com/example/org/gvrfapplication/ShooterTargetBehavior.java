package com.example.org.gvrfapplication;

import org.gearvrf.GVRBehavior;
import org.gearvrf.GVRContext;

/**
 * Created by nite.luo on 2/28/2018.
 */

public class ShooterTargetBehavior extends GVRBehavior {

    static private long TYPE_SHOOTER_TARGET_BEHAVIOR = newComponentType(ShooterTargetBehavior.class);

    protected ShooterTargetBehavior(GVRContext gvrContext) {
        super(gvrContext);
        mType = TYPE_SHOOTER_TARGET_BEHAVIOR;
    }

    static public long getComponentType(){return TYPE_SHOOTER_TARGET_BEHAVIOR;}

    public void onShot() {
        //
    }
}
