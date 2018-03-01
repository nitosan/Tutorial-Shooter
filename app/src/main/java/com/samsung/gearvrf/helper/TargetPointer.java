package com.samsung.gearvrf.helper;

import org.gearvrf.GVRBehavior;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRSceneObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * Created by nite.luo on 2/28/2018.
 */

public class TargetPointer extends GVRBehavior {

    static private long TYPE_TARGET_POINTER = newComponentType(TargetPointer.class);

    private GVRSceneObject _target;

    public TargetPointer(GVRContext gvrContext) {
        super(gvrContext);
        mType = TYPE_TARGET_POINTER;
    }

    static public long getComponentType() { return TYPE_TARGET_POINTER; }

    @Override
    public void onDrawFrame(float frameTime) {
        super.onDrawFrame(frameTime);

        updatePointToTarget();
    }

    private void updatePointToTarget() {
        if(_target == null) {
            return;
        }

        Vector3f eye = Gvr.getWorldPosition(getOwnerObject());
        Vector3f center = Gvr.getWorldPosition(_target);
        Vector3f up = new Vector3f(0,1,0);

        Vector3f pos = new Vector3f(getTransform().getPositionX(), getTransform().getPositionY(), getTransform().getPositionZ());

        Matrix4f mat = new Matrix4f();
        mat.lookAt(eye, center, up);

        Matrix4f matP = getOwnerObject().getParent().getTransform().getModelMatrix4f();
        matP.setTranslation(0,0,0);

        mat.mul(matP.invert());
        mat.setTranslation(pos);

        getTransform().setModelMatrix(mat);
    }

    public void setTarget(GVRSceneObject target) {
        this._target = target;
    }
}
