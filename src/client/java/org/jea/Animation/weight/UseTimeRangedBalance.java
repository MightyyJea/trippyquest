package org.jea.Animation.weight;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.Util;
import net.minecraft.world.InteractionResult;
import org.jea.AllItems;
import org.jea.TrippyquestClient;

public class UseTimeRangedBalance extends Balance implements BalanceEvent {
    float maxDuring;
    float UseStartMS;
    public UseTimeRangedBalance(float during){
        maxDuring=during*1000f;
    }
    @Override
    public float  getWeight() {
        float second = Util.getMillis();
        float interval = second - UseStartMS;
        float range = (maxDuring - interval)/maxDuring;
        range = range > 0f ? range : 0;
        TrippyquestClient.print("last use at time : " + UseStartMS+", current second: "+ second +" range : " + range + " interval :" + interval + " maxdurtion :"+maxDuring);

        return  range;
    }

    @Override
    public void register() {
        UseItemCallback.EVENT.register((player, level, interactionHand) -> {
            UseStartMS = Util.getMillis();
            return InteractionResult.PASS;
        });
    }
}
