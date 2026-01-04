package org.jea;

import com.mojang.serialization.Codec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.jea.Animation.AnimationDefinition;
import org.jea.Animation.WeightCodec;
import org.jea.Animation.WeightCodecs;
import org.jea.event.BluntEvent;

import java.util.*;

@Environment(EnvType.CLIENT)
public class ClientCache {
    public static UseBlunt USEBLUNT_HOLDER = null;
    public static CustomAnimationContext ANIMATION_CONTEXT_MANAGER = new CustomAnimationContext();

    public static class UseBlunt{
        public float BeginUseMS;
        public int tickCounter;

        public UseBlunt(){
            tickCounter = 0;
            BeginUseMS = 0;
        }
        public void tick(){
            tickCounter++;
            TrippyquestClient.print("is working");
            if (!Minecraft.getInstance().player.isUsingItem() && !Minecraft.getInstance().mouseHandler.isRightPressed()){
                BluntEvent.RELEASE_EVENT.invoker().onReleaseUsing(tickCounter);
                TrippyquestClient.print("release fired");
            }
        }
        public float timeElapsed(){
            return this.BeginUseMS ;
        }
    }

    public static class CustomAnimationContext{
        private List<AnimationDefinition> AnimationPondered;
        private LinkedList<WeightCodec<?>> weightSetDefinition;
        private HashMap<Float, WeightCodec<?>> templist = new HashMap<>();
        public CustomAnimationContext(){
            this.AnimationPondered = new ArrayList<>();
            weightSetDefinition = new LinkedList<>();
        }
        private float getTotalWeight(AnimationDefinition animationDefinition){
            float weight =0;
            for(WeightCodec<?> weightfromaniatom : animationDefinition.getWeightCodecsSet()){
                for(int i = 0; i < this.weightSetDefinition.size(); i++){
                    if(weightfromaniatom.getNameIdentifier().equals(weightSetDefinition.get(i).getNameIdentifier()) ){
                        weight += i;
                   }
                }
            }
            TrippyquestClient.print("weight "+ weight);
            return weight;
        }
        public AnimationDefinition ResolveAnimation() {
            AnimationDefinition outputAnimation= null;
            float BestWeightReached = 0;

            for (AnimationDefinition animationDefinition : AnimationPondered){
                float currentWheight = getTotalWeight(animationDefinition);
                if( currentWheight > BestWeightReached ){
                    outputAnimation = animationDefinition;
                    BestWeightReached = currentWheight;
                }
            }
            if(outputAnimation == null){
                TrippyquestClient.print("Animation Definition not found");
                return AnimationDefinition.NONE;
            }
            return outputAnimation;
        }



        private void setCodecWeight(WeightCodec<?> codec, float weight){
            templist.put(weight, codec);
        }
        public void initWeightSet(){
            definePriority();

            Float[] temptemplist = Arrays.copyOf(templist.keySet().stream().toArray(), templist.keySet().stream().toArray().length, Float[].class);

            Arrays.sort(temptemplist, 0, temptemplist.length);

            for(int i = 0 ; i < temptemplist.length;i++){
                weightSetDefinition.add(templist.get(temptemplist[i]));
            }
            for(int i =0; i< weightSetDefinition.size(); i++){
                TrippyquestClient.print("index "+ i + " type " +weightSetDefinition.get(i).getNameIdentifier());
            }
        }
        private void definePriority(){
            setCodecWeight(WeightCodecs.isRightM,2);
            setCodecWeight(WeightCodecs.itemStack, 1);
        }
        public void registerAnimationDefinition(AnimationDefinition animationDefinition){
            this.AnimationPondered.add(animationDefinition);
        }
    }
}
