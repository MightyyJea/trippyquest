package org.jea.Animation;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import org.jea.TrippyquestClient;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class WeightTypes {
    private static ResourceKey WEIGHT_TYPE= ResourceKey.createRegistryKey(TrippyquestClient.fromMod("weightType"));
    public static DefaultedMappedRegistry<WeightType> WeightTypeRegistry = new DefaultedMappedRegistry("WeightType", ResourceKey.createRegistryKey(TrippyquestClient.fromMod("weight_type_registry")), Lifecycle.stable(), true);

    private static <P, EventType extends Event<Consumer<P>>> CompletableFuture<WeightType.Weightned> registerTypeEvent(WeightType type, EventType eventType, BiConsumer<P, CompletableFuture<WeightType.Weightned>> biconsumer){
        CompletableFuture<WeightType.Weightned> weightnedCompletableFuture = new CompletableFuture<>();
        Consumer<P> eventListener = (P arg) ->{
            biconsumer.accept(arg,weightnedCompletableFuture);
        };
        eventType.register(eventListener);
        return weightnedCompletableFuture;
    }

    private static Holder.Reference<WeightType> register( WeightType type ){
         return  WeightTypeRegistry.register(WEIGHT_TYPE, type, RegistrationInfo.BUILT_IN);
    }
}
