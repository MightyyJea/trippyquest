package org.jea;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jea.Animation.CustomAnimationManager;

@Environment(EnvType.CLIENT)
public class ClientCache {
    public static void init(){

    }
    public static CustomAnimationManager ANIMATION_CONTEXT_MANAGER = new CustomAnimationManager();
}
