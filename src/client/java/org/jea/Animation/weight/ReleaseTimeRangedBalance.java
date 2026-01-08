package org.jea.Animation.weight;

import net.minecraft.Util;
import org.jea.ReleaseEvent;
import org.jea.TrippyquestClient;

public class ReleaseTimeRangedBalance extends Balance implements BalanceEvent{
    float maxduring;
    float lastReleaseMS;
    public ReleaseTimeRangedBalance(float during){
        maxduring = during;
    }

    @Override
    public float getWeight() {
        float range =  Util.getMillis() - lastReleaseMS;
        return  range/(maxduring*1000);
    }

    @Override
    public void register() {
        ReleaseEvent.RELEASE_EVENT.register(((item, level) -> {
            lastReleaseMS = Util.getMillis();
        }));
    }
}
