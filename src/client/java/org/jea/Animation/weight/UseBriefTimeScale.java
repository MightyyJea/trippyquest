package org.jea.Animation.weight;

import net.minecraft.client.Minecraft;

public class UseBriefTimeScale extends Scale implements ScaleSearch{
    float weight;
    boolean waspressed;
    public UseBriefTimeScale(){
        weight = 0;
        waspressed = false;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public void Search() {
        float gauge = ScaleType.GaugeUse.getWeight();
        boolean righpressed = Minecraft.getInstance().mouseHandler.isRightPressed();
        if(righpressed && weight == 1f){
            weight = 0f;
        }
        if(gauge > 0.8 && Waspressed(righpressed)){
            weight = 1f;

        }
    }
    private boolean Waspressed(boolean righpressed){
        waspressed =false;
        if(righpressed){
            waspressed = true;
        }
        return waspressed;
    }
}
