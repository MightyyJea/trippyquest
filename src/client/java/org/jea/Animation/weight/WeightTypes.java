package org.jea.Animation.weight;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.jea.ClientCache;
import org.jea.TrippyquestClient;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class WeightTypes {
    private static ResourceKey WEIGHT_TYPE= ResourceKey.createRegistryKey(TrippyquestClient.fromMod("weightype"));
    //public static DefaultedMappedRegistry<WeightType> WeightTypeRegistry = new DefaultedMappedRegistry("weight", ResourceKey.createRegistryKey(TrippyquestClient.fromMod("weight_type_registry")), Lifecycle.stable(), true);

    public static WeightType<?> UseMS = register(new WeightType<>( TrippyquestClient.fromMod("usems"),new UseTimeRangedBalance(2f)));

    private static WeightType<?> register(WeightType<?> type){
        if(type.balance() instanceof  BalanceEvent event){
            event.register();
        }else if (type.balance() instanceof  BalanceSearch search) {
            ClientTickEvents.START_WORLD_TICK.register((ClientLevel)-> {search.Search(type);});
        }
        //WeightTypeRegistry.register(WEIGHT_TYPE, type, RegistrationInfo.BUILT_IN);
         return  type;
    }
}
