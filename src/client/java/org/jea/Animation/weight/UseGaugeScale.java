package org.jea.Animation.weight;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UseGaugeScale extends Scale implements ScaleEvent {
    float gauge;
    float increase = 2f;
    float decrease = 4f;
    float lastMS;
    List<ScaleSearch> after;
    public UseGaugeScale(){
        gauge = 0;
        lastMS = 0f;
        after = new ArrayList<>();
    }
    @Override
    public float getWeight() {
        return gauge;
    }

    @Override
    public void register() {
        ClientTickEvents.END_CLIENT_TICK.register((clientLevel -> {
            float pt = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks() / 20;

            boolean righpressed = Minecraft.getInstance().mouseHandler.isRightPressed();
            //TrippyquestClient.print("gauge "+gauge);
//            if(righpressed){
//                if(gauge + increase * pt >= 1f){
//                    return;
//                }
//               gauge += increase *pt;
//            }else if (gauge - decrease * pt>0f){
//                gauge -= decrease *pt;
//            }
            while (gauge + increase * pt >= 1f && righpressed){
                gauge += increase *pt;
            }
            gauge = gauge -decrease * pt < 0 ? 0 : gauge - decrease*pt;
            for(ScaleSearch search : this.after){
                search.Search();
            }
        }
        ));
    }
    public void After(ScaleSearch search){
        this.after.add(search);
    }
//    public void atGaugeAmount(ScaleSearch scaleSearch, float amount, boolean above){
//        Consumer<Integer> consumer = a ->{
//            if(above && amount < getWeight()){
//                scaleSearch.Search();
//                return;
//            }
//            if(!above && amount > getWeight()){
//                scaleSearch.Search();
//            }
//        };
//
//        this.after.add(consumer);
//    }
}