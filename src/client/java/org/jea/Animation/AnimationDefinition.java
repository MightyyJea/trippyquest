package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import org.jea.Animation.weight.WeightType;
import org.jea.Animation.weight.WeightTypes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AnimationDefinition {
    public AnimationDefinition(){
    }
    public abstract void applyAnimation(PoseStack poseStack,float weight);

}
