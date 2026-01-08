package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jea.Animation.weight.WeightType;
import org.jea.Animation.weight.WeightTypes;
import org.jea.TrippyquestClient;

import java.util.*;

public class CustomAnimationManager {

    private HashMap<WeightType<?>, AnimationDefinition> WeightAnimationSet;
    public CustomAnimationManager() {
        WeightAnimationSet = new HashMap<>();
        defineAnimationSet();
    }
    public void ResolveAnimations(PoseStack stack) {
        for(Map.Entry<WeightType<?>, AnimationDefinition> weightType : WeightAnimationSet.entrySet()){
            weightType.getValue().applyAnimation(stack, weightType.getKey().balance().getWeight());
        }
    }
    private void defineAnimationSet() {
        addAnimation(WeightTypes.UseMS, new test());
    }
    private void addAnimation(WeightType<?> weightType, AnimationDefinition definition){

        this.WeightAnimationSet.put(weightType, definition);

    }
}