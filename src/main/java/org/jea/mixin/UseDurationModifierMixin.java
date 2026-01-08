package org.jea.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface UseDurationModifierMixin {
    @Accessor("useItemRemaining")
    void setuseItemRemaining(int duration);
}
