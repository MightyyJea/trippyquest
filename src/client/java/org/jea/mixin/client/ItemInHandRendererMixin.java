package org.jea.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.Vec3;
import org.jea.*;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Shadow protected abstract void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, float f, float g, HumanoidArm humanoidArm);
    @Inject(method = "renderArmWithItem", at= @At(value = "JUMP",opcode = Opcodes.IFEQ, ordinal = 0, shift = At.Shift.BEFORE))
    public void onrenderArmWithItemAtBegin(AbstractClientPlayer abstractClientPlayer, float f, float g, InteractionHand interactionHand, float h, ItemStack itemStack, float i, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int j, CallbackInfo ci){
        if(itemStack.getItem() == AllItems.BLUNTITEM){
            poseStack.pushPose();
            TrippyquestClient.print("x :" +Vector3DebugStorage.x);
            poseStack.mulPose(Axis.XN.rotationDegrees(Vector3DebugStorage.PoseMap.get("XP")));
            poseStack.mulPose(Axis.ZP.rotationDegrees(Vector3DebugStorage.PoseMap.get("ZP")));
            renderPlayerArm(poseStack, submitNodeCollector, j, i, h, abstractClientPlayer.getMainArm());
            poseStack.popPose();
        }
    }

    @Inject(method = "renderArmWithItem", at= @At(value = "INVOKE", opcode = Opcodes.IFEQ, ordinal = 1, shift = At.Shift.BEFORE, target = "net/minecraft/client/renderer/ItemInHandRenderer.renderItem (Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V"))
    public void onRenderArmWithItem(AbstractClientPlayer abstractClientPlayer, float f, float g, InteractionHand interactionHand, float h, ItemStack itemStack, float i, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int j, CallbackInfo ci){
        if(abstractClientPlayer.getMainHandItem().getItem() == AllItems.BLUNTITEM){
            ClientCache.ANIMATION_CONTEXT_MANAGER.ResolveAnimations(poseStack);

        }
    }
}

