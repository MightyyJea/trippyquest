package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.item.Item;
import org.jea.AllItems;
import org.jea.Animation.weight.Scale;
import org.jea.Animation.weight.ScaleType;

import java.util.*;

public class AnimationContextManager {
    Item item;
    public static HashMap<Item, AnimationContextManager> CustomItems = new HashMap<>();
    private HashMap<Scale, AnimationDefinition> WeightAnimationSet;

    private HashMap<Scale, AnimationDefinition> ArmAnimationSet;
    public AnimationContextManager() {
        WeightAnimationSet = new HashMap<>();
        ArmAnimationSet = new HashMap<>();
        defineAnimationSet();
    }
    public void ResolveAnimations(PoseStack stack, boolean rightHand) {
        if (rightHand){
            for(Map.Entry<Scale, AnimationDefinition> weightType : ArmAnimationSet.entrySet()){
                weightType.getValue().applyAnimation(stack, weightType.getKey().getWeight());
            }
        }
        for(Map.Entry<Scale, AnimationDefinition> weightType : WeightAnimationSet.entrySet()){
            weightType.getValue().applyAnimation(stack, weightType.getKey().getWeight());
        }
    }
    public void translateAndRotatatetoHandFP(){}
    private void defineAnimationSet() {
       addAnimation(ScaleType.GaugeUse, new test());
        addHandAnimation(ScaleType.GaugeUse, new ArmAnimation());
    }
    private void addHandAnimation(Scale scale, AnimationDefinition animationDefinition){
        this.ArmAnimationSet.put(scale, animationDefinition);
    }
    private void addAnimation(Scale weightType, AnimationDefinition definition){

        this.WeightAnimationSet.put(weightType, definition);

    }
    public class AnimationBuilder {



    }
}