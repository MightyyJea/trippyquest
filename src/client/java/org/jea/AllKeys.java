package org.jea;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public class AllKeys {
    public static void init(){}
    public static  KeyMapping.Category CATEGORY = new KeyMapping.Category(
            ResourceLocation.fromNamespaceAndPath(Trippyquest.MOD_ID, "custom_category")
    );
    public static KeyMapping toggleAnimUtils = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                    "key.example-mod.send_to_chat", // The translation key for the key mapping.
                    InputConstants.Type.KEYSYM, // // The type of the keybinding; KEYSYM for keyboard, MOUSE for mouse.
                    GLFW.GLFW_KEY_J, // The GLFW keycode of the key.
                    CATEGORY // The category of the mapping.
            ));

}
