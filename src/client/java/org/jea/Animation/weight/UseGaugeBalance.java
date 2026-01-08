package org.jea.Animation.weight;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.mixin.client.gametest.screenshot.RenderTickCounterConstantAccessor;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionResult;
import org.jea.AllItems;
import org.jea.TrippyquestClient;

import java.time.chrono.MinguoEra;

public class UseGaugeBalance extends Balance implements BalanceEvent{
    float gauge;
    float increase = 2f;
    float decrease = 4f;
    float lastMS;
    public UseGaugeBalance(){
        gauge = 0;
        lastMS = 0f;

    }
    @Override
    public float getWeight() {
        return gauge;
    }

    @Override
    public void register() {
        ClientTickEvents.START_WORLD_TICK.register((clientLevel -> {

            float pt = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks() / 20;

            TrippyquestClient.print("gauge "+gauge);
            if(Minecraft.getInstance().mouseHandler.isRightPressed()){
                if(gauge + increase * pt >= 1f){
                    return;
                }
               gauge += increase *pt;
            }else if (gauge - decrease * pt>0f){
                gauge -= decrease *pt;
            }
        }
        ));
    }
}
