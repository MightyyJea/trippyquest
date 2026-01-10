package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

public class BluntHandPosAnimation extends AnimationDefinition{
    @Override
    public void applyAnimation(PoseStack poseStack, float weight) {
        poseStack.translate(-0.3152f, 0.06521f, -0.3043f);
        poseStack.mulPose(Axis.YP.rotationDegrees(-58.69f));
    }
}
