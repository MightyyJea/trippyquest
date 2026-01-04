package org.jea;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.timers.TimerQueue;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.util.Locals;

import java.io.IOException;
import java.util.Random;

public class BluntItem extends Item {
    private final int MaxUseDuration = 10;
    private final int MinUseDuration = 2;
    public BluntItem(Properties properties) {
        super(properties);
        properties.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Trippyquest.MOD_ID, "blunt")));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()){
            player.startUsingItem(interactionHand);
        }
        return InteractionResult.SUCCESS;
    }
//
//    @Override
//    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remaining) {
//
//
//
//        (ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
//        return true;
//    }
//    @Override
//    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
//        //return itemStack.getOrDefault(AllDataComponents.USE_DURATION,MaxUseDuration);
//        return -1;
//    }
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
