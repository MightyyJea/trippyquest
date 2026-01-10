package org.jea.Animation.weight;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ScaleType {
    public final static Scale GaugeUse = register(new UseGaugeScale());

    private static Scale register(Scale type){
        if(type instanceof  ScaleEvent event){
            event.register();
        }else if (type instanceof  ScaleSearch search) {
            ClientTickEvents.START_WORLD_TICK.register((ClientLevel)-> {search.Search();});
        }
        //WeightTypeRegistry.register(WEIGHT_TYPE, type, RegistrationInfo.BUILT_IN);
         return  type;
    }
}
