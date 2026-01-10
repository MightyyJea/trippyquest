package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;

public abstract class AnimationDefinition {
    public AnimationDefinition(){
    }
    public abstract void applyAnimation(PoseStack poseStack,float weight);

}
