package org.jea.Animation.weight;

import net.minecraft.resources.ResourceLocation;

public  record WeightType< BalanceT extends Balance>( ResourceLocation resourceLocation, BalanceT balance) {
    public ResourceLocation getIdentifier(){
        return resourceLocation;
    }
    public record Weightned(WeightType<?> type, float weight){ }
}
