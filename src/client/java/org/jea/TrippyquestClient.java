package org.jea;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jea.Animation.test;
import org.jea.event.BluntEvent;

public class TrippyquestClient implements ClientModInitializer {
	public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			ResourceLocation.fromNamespaceAndPath(Trippyquest.MOD_ID,
					"item_group"));
	public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(AllItems.BLUNTITEM))
			.title(Component.translatable("itemGroup." +Trippyquest.MOD_ID))
			.build();

	@Override
  	public void onInitializeClient() {
		AllItems.init();
		registerBluntRelatedEvent();
		ClientCache.ANIMATION_CONTEXT_MANAGER.initWeightSet();
		registerAnimationDefinition();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
		ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY);
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(
					ClientCommandManager.literal("vector")
							.then(
									ClientCommandManager.argument("clientvec3", new ClientVec3Argument())
											.executes(context -> {
												Vector3DebugStorage.blunt = context.getArgument("clientvec3", Vec3.class);
												context.getSource().sendFeedback(Component.literal("called"));

												return 1;
											}))
			);
		});
    }
	public static void print(String s){
		LogUtils.getLogger().info(s);
	}
	public static void registerArgumentType(){
		ArgumentTypeRegistry
				.registerArgumentType(ResourceLocation.fromNamespaceAndPath(Trippyquest.MOD_ID, "clientvec3"), ClientVec3Argument.class, SingletonArgumentInfo.contextFree(ClientVec3Argument::new));
	}
	private void registerAnimationDefinition(){
		ClientCache.ANIMATION_CONTEXT_MANAGER.registerAnimationDefinition(new test());
	}
	public static ResourceLocation fromMod(String path){
		return ResourceLocation.fromNamespaceAndPath(Trippyquest.MOD_ID, path);
	}

	private void registerBluntRelatedEvent(){
		ClientTickEvents.START_WORLD_TICK.register(clientLevel->{
			if (ClientCache.USEBLUNT_HOLDER != null){
				ClientCache.USEBLUNT_HOLDER.tick();
			}
		});
		UseItemCallback.EVENT.register((player, level, interactionHand)  ->{
			if(level.isClientSide() && player instanceof AbstractClientPlayer && player.getMainHandItem().getItem() == AllItems.BLUNTITEM){
				ClientCache.USEBLUNT_HOLDER = new ClientCache.UseBlunt();
				//ClientCache.ANIMATION_CONTEXT_MANAGER.SubmitAnimation((poseStack -> poseStack.translate(Vector3DebugStorage.blunt)));
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		});
		BluntEvent.RELEASE_EVENT.register((time ->{
			ClientCache.USEBLUNT_HOLDER = null;
			Minecraft.getInstance().player.stopUsingItem();


		}));

	}

}