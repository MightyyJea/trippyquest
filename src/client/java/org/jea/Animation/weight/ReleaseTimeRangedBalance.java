package org.jea.Animation.weight;

import net.minecraft.Util;
import org.jea.ReleaseEvent;

public class ReleaseTimeRangedBalance extends Balance implements BalanceEvent{
    float maxduring;
    float lastReleaseMS;
    public ReleaseTimeRangedBalance(float during){
        maxduring = during;
    }

    @Override
    public float getWeight() {
        float range = lastReleaseMS - Util.getMillis();
        return range/maxduring;
    }

    @Override
    public void register() {
        ReleaseEvent.RELEASE_EVENT.register(((item, level) -> {
            lastReleaseMS = Util.getMillis();
        }));
    }
}
