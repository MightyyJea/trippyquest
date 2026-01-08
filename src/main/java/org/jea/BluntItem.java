package org.jea;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jea.mixin.UseDurationModifierMixin;

public class BluntItem extends Item {
    public BluntItem(Properties properties) {
        super(properties);
        properties.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Trippyquest.MOD_ID, "blunt")));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (level.isClientSide()){
            return InteractionResult.PASS;
        }
        player.startUsingItem(interactionHand);
        return InteractionResult.SUCCESS;
    }
//
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remaining) {
        if(level.isClientSide()){
            ((UseDurationModifierMixin) livingEntity).setuseItemRemaining(remaining+1);
        }
    }
    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 3000;
    }
//
//    @Override
//    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
//        return itemStack;
//    }
//    @Override
//    public void applyAnimation(AbstractClientPlayer abstractClientPlayer, float f, float g, InteractionHand interactionHand, float h, ItemStack itemStack, float i, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int j) {
//        float l =  (abstractClientPlayer.getUseItemRemainingTicks() - itemStack.getUseDuration(abstractClientPlayer) );
//
//
//    }
//
//    @Override
//    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
//        super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot);
//    }
//
//    @Override
//    public void releasingAnimation(AbstractClientPlayer abstractClientPlayer, InteractionHand hand, ItemStack stack, PoseStack poseStack) {
//        Vec3 startpos = new Vec3(0,0,0);
//        Vec3 finalpos = new Vec3(-0.6  ,0.2 ,0.1 );
//
//
//    }


}
