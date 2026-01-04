package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import org.jea.ClientCache;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimationDefinition {
    protected List<WeightCodec> weightCodecsSet;
    public AnimationDefinition(){
       //should set weights in constructor
        weightCodecsSet = new ArrayList<>();
    }
    public abstract void applyAnimation(PoseStack poseStack);

    public List<WeightCodec> getWeightCodecsSet() {
        return weightCodecsSet;
    }
    public static AnimationDefinition NONE = new AnimationDefinition() {
        @Override
        public void applyAnimation(PoseStack poseStack) {
        }
    };
}
