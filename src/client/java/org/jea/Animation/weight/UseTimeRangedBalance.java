package org.jea.Animation.weight;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.Util;
import net.minecraft.world.InteractionResult;
import org.jea.AllItems;

public class UseTimeRangedBalance extends Balance implements BalanceEvent {
    float maxDuring;
    float UseStartMS;
    public UseTimeRangedBalance(float during){
        maxDuring=during;
    }
    @Override
    public float  getWeight() {
        float range = UseStartMS - Util.getMillis();
        return  range/maxDuring;
    }

    @Override
    public void register() {
        UseItemCallback.EVENT.register((player, level, interactionHand) -> {
            if(player.getUseItem().getItem() == AllItems.BLUNTITEM){
                UseStartMS = Util.getMillis();
            }
            return InteractionResult.PASS;
        });
    }
}
