package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jea.LerpingUtils;

public class test extends AnimationDefinition{
    public test(){
        super();
    }
    @Override
    public void applyAnimation(PoseStack poseStack, float t) {
        Vec3 startpos = new Vec3(0,0,0);
        Vec3 finalpos = new Vec3(-0.6  ,0.2 ,0.1 );
        float lerp = LerpingUtils.easeOutQuint(t) ;
        Vec3 smoothon = Mth.lerp( lerp  , startpos, finalpos);

        poseStack.translate(smoothon);
    }
}
