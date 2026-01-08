package org.jea.Animation.weight;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionResult;
import org.jea.AllItems;
import org.jea.TrippyquestClient;

public class UseGaugeBalance extends Balance implements BalanceEvent{
    float gauge;
    float increase = 2/20f;
    float decrease = 3/20f;
    public UseGaugeBalance(){
        gauge = 0;

    }
    @Override
    public float getWeight() {
        return gauge;
    }

    @Override
    public void register() {
        ClientTickEvents.START_WORLD_TICK.register((clientLevel -> {
            LocalPlayer player = Minecraft.getInstance().player;
            TrippyquestClient.print("gauge "+gauge);
            if(Minecraft.getInstance().mouseHandler.isRightPressed()){
                if(gauge + increase >= 1f){
                    return;
                }
               gauge += increase;
            }else if (gauge - decrease>0f){
                gauge -= decrease;
            }
        }
        ));
    }
}
