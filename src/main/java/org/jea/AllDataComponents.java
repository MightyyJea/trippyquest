package org.jea;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;

public class AllDataComponents {
    public static final DataComponentType<Boolean> CUSTOM_ANIMATION = Registry
            .register(BuiltInRegistries.DATA_COMPONENT_TYPE, "custom_animation",
                    new DataComponentType.Builder<Boolean>()
                            .persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
    public static final DataComponentType<Boolean> TRIGGER_RELEASE_ANIMATION = Registry
            .register(BuiltInRegistries.DATA_COMPONENT_TYPE, "trigger_release_animation",
                    new DataComponentType.Builder<Boolean>()
                            .persistent(Codec.BOOL).build());
}
