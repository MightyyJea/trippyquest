package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jea.Animation.HUDAnimtionHelper.ScreenAnimationHelper;
import org.jea.LerpingUtils;
import org.jea.TrippyquestClient;

public class releaseAnimation extends AnimationDefinition{
    @Override
    public void applyAnimation(PoseStack poseStack, float weight) {
        Vec3 startpos = new Vec3(0,0,0);
        Vec3 finalpos = new Vec3(-0.6  ,0.2 ,0.1 );
        float lerp = LerpingUtils.easeInBack(weight) ;
        Vec3 smoothon = Mth.lerp( lerp  , finalpos, startpos);

        Minecraft.getInstance().setScreen(new ScreenAnimationHelper(Component.literal("test")));
        poseStack.translate(smoothon);
    }
}
