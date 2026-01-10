package org.jea.Animation.weight;

import net.minecraft.Util;
import org.jea.ReleaseEvent;

public class ReleaseTimeRangedScale extends Scale implements ScaleEvent {
    float maxduring;
    float lastReleaseMS;
    public ReleaseTimeRangedScale(float during){
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
