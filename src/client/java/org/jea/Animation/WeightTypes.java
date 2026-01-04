package org.jea.Animation;

import com.mojang.serialization.Lifecycle;
import net.minecraft.client.Minecraft;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import org.jea.TrippyquestClient;

public class WeightTypes {
    private static ResourceKey WEIGHT_TYPE= ResourceKey.createRegistryKey(TrippyquestClient.fromMod("weightType"));
    public static DefaultedMappedRegistry<WeightType> WeightTypeRegistry = new DefaultedMappedRegistry("WeightType", ResourceKey.createRegistryKey(TrippyquestClient.fromMod("weight_type_registry")), Lifecycle.stable(), true);



    public static Holder.Reference<WeightType<Boolean>> isRightM = register(new WeightType<>(Boolean.TYPE, TrippyquestClient.fromMod("isRightM") , ()->{return true;})) ;
    public static Holder.Reference<WeightType<ItemStack>> itemStack = register( new WeightType<>(ItemStack.class, TrippyquestClient.fromMod("ItemStack"), () -> {
        return Minecraft.getInstance().player.getUseItem();
    }));


    private static Holder.Reference<WeightType> register( WeightType type ){
         return  WeightTypeRegistry.register(WEIGHT_TYPE, type, RegistrationInfo.BUILT_IN);
    }
}
