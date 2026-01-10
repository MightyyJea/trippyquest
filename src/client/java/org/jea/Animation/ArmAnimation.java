package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;

public class ArmAnimation extends AnimationDefinition{
    @Override
    public void applyAnimation(PoseStack poseStack, float weight) {
        poseStack.mulPose(Axis.YP.rotationDegrees(31.30f*weight));
        poseStack.mulPose(Axis.ZN.rotationDegrees(13.69f*weight));
        poseStack.translate(-0.4347f*weight, 0.0217f*weight, 0.7173f*weight);
    }
}
