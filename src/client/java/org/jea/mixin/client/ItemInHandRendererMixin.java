package org.jea.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jea.Animation.AnimationContextManager;
import org.jea.CustomAnimationItem;
import org.jea.Vector3DebugStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Shadow protected abstract void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, float f, float g, HumanoidArm humanoidArm);

    @Shadow public abstract void renderItem(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i);

    @Inject(method = "renderArmWithItem", at= @At(value = "HEAD"), cancellable = true)
    public void onrenderArmWithItemAtBegin(AbstractClientPlayer abstractClientPlayer, float f, float g, InteractionHand interactionHand, float h, ItemStack itemStack, float i, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int j, CallbackInfo ci){
        if(itemStack.getItem() instanceof CustomAnimationItem){
            AnimationContextManager animationContextManager = AnimationContextManager.CustomItems.get(itemStack.getItem());
            boolean bl = interactionHand == InteractionHand.MAIN_HAND;
            HumanoidArm humanoidArm = bl ? abstractClientPlayer.getMainArm() : abstractClientPlayer.getMainArm().getOpposite();

            poseStack.pushPose();
            if (Vector3DebugStorage.debugMode){
                Vector3DebugStorage.transformStack(poseStack);
                if(Vector3DebugStorage.animation){
                    poseStack.popPose();
                    poseStack.pushPose();
                    Vector3DebugStorage.transformToAnimationStack(poseStack);
                }
            }

            //animationContextManager.ResolveAnimations(poseStack, true);
            renderPlayerArm(poseStack, submitNodeCollector, j, i, h, abstractClientPlayer.getMainArm());
            poseStack.popPose();

            poseStack.pushPose();
            animationContextManager.ResolveAnimations(poseStack, false);
            this.renderItem(abstractClientPlayer, itemStack, humanoidArm == HumanoidArm.RIGHT ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, poseStack, submitNodeCollector, j);
            poseStack.popPose();
            ci.cancel();
        }
    }

//    @Inject(method = "renderArmWithItem", at= @At(value = "INVOKE", opcode = Opcodes.IFEQ, ordinal = 1, shift = At.Shift.BEFORE, target = "net/minecraft/client/renderer/ItemInHandRenderer.renderItem (Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V"))
//    public void onRenderArmWithItem(AbstractClientPlayer abstractClientPlayer, float f, float g, InteractionHand interactionHand, float h, ItemStack itemStack, float i, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int j, CallbackInfo ci){
//        //pushPose
//        if(abstractClientPlayer.getMainHandItem().getItem() == AllItems.BLUNTITEM){
//            PoseStack newPoseStack = new PoseStack();
//            ClientCache.ANIMATION_CONTEXT_MANAGER.ResolveAnimations(newPoseStack);
//            renderPlayerArm(newPoseStack, submitNodeCollector, j, i, h, abstractClientPlayer.getMainArm());
//            poseStack.translate(-0.3152f, 0.06521f, -0.3043f);
//            poseStack.mulPose(Axis.YP.rotationDegrees(-58.69f));
//
//
//            //popPose
//        }
//    }
}

