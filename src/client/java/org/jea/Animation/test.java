package org.jea.Animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.serialization.v1.view.FabricReadView;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jea.ClientCache;
import org.jea.LerpingUtils;
import org.jea.TrippyquestClient;

public class test extends AnimationDefinition{
    public test(){
        super();
        weightCodecsSet.add(WeightCodecs.itemStack);
        weightCodecsSet.add(WeightCodecs.isRightM);
    }
    @Override
    public void applyAnimation(PoseStack poseStack) {
        float time = ClientCache.USEBLUNT_HOLDER.tickCounter /20;
        Vec3 startpos = new Vec3(0,0,0);
        Vec3 finalpos = new Vec3(-0.6  ,0.2 ,0.1 );
        //TrippyquestClient.print("i : "+i+" f : " +f+" g : "+g +" h : " +h + " j : " +j);
//        if(time > 1F ){
//            poseStack.translate(finalpos);
//            return;
//        }
        float lerp = LerpingUtils.easeInBack(time) ;
        TrippyquestClient.print("lerp " +lerp +" time "+ time);
        Vec3 smoothon = Mth.lerp( lerp  , startpos, finalpos);

        poseStack.translate(smoothon);
    }
}
