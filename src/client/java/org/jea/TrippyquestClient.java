package org.jea;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class TrippyquestClient implements ClientModInitializer {
	boolean wasPressed;
		@Override
  	public void onInitializeClient() {
			wasPressed = false;
		registerBluntRelatedEvent();
            HudElementRegistry.addFirst(Trippyquest.);
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
	public static ResourceLocation fromMod(String path){
		return ResourceLocation.fromNamespaceAndPath(Trippyquest.MOD_ID, path);
	}
	private void registerBluntRelatedEvent(){
		ClientTickEvents.START_WORLD_TICK.register(clientLevel->{
			if(Minecraft.getInstance().mouseHandler.isRightPressed()){
				wasPressed = true;
			}else if (!Minecraft.getInstance().mouseHandler.isRightPressed() && wasPressed){
				wasPressed = false;
				ReleaseEvent.RELEASE_EVENT.invoker().onReleaseUsing(Minecraft.getInstance().player, clientLevel);
			}
		});
	}
}