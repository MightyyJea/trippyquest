package org.jea;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public class AllDataComponents {
    public static final DataComponentType<Integer> USE_DURATION = Registry
            .register(BuiltInRegistries.DATA_COMPONENT_TYPE, "use_duration",
                    new DataComponentType.Builder<Integer>()
                            .persistent(Codec.INT).build());
    public static final DataComponentType<Boolean> TRIGGER_RELEASE_ANIMATION = Registry
            .register(BuiltInRegistries.DATA_COMPONENT_TYPE, "trigger_release_animation",
                    new DataComponentType.Builder<Boolean>()
                            .persistent(Codec.BOOL).build());
}
