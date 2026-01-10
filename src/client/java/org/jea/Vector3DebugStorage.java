package org.jea;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;

public class Vector3DebugStorage {
    private static float startMS;
    public static PoseFrame startPos = new PoseFrame();
    public static PoseFrame endPos = new PoseFrame();
    public static PoseFrame currentModelPose = new PoseFrame();
    public static HashMap<String, Float> PoseMap = new HashMap<>();
    public static void transformStack(PoseStack poseStack){
        poseStack.mulPose(Axis.XP.rotationDegrees(Vector3DebugStorage.PoseMap.get("XP")));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Vector3DebugStorage.PoseMap.get("ZP")));
        poseStack.mulPose(Axis.YP.rotationDegrees(Vector3DebugStorage.PoseMap.get("YP")));;
        poseStack.translate(Vector3DebugStorage.PoseMap.get("X"),Vector3DebugStorage.PoseMap.get("Y"),Vector3DebugStorage.PoseMap.get("Z"));
    }
    public static PoseFrame getPoseFrame(){
        PoseFrame newPoseFrame = new PoseFrame();
        newPoseFrame.z = Vector3DebugStorage.PoseMap.get("Z");
        newPoseFrame.x = Vector3DebugStorage.PoseMap.get("X");
        newPoseFrame.y = Vector3DebugStorage.PoseMap.get("Y");
        newPoseFrame.XP = Vector3DebugStorage.PoseMap.get("XP");
        newPoseFrame.ZP = Vector3DebugStorage.PoseMap.get("ZP");
        newPoseFrame.YP = Vector3DebugStorage.PoseMap.get("YP");
        return  newPoseFrame;
    }
    public static void updateStartPos(){
        startPos = getPoseFrame();
    }
    public static void updateEndPos(){
        endPos = getPoseFrame();
    }
    public static void init(){
        Vector3DebugStorage.PoseMap.put("Y", 0f);
        Vector3DebugStorage.PoseMap.put("Z", 0f);
        Vector3DebugStorage.PoseMap.put("X", 0f);
        Vector3DebugStorage.PoseMap.put("XP", 0f);
        Vector3DebugStorage.PoseMap.put("ZP", 0f);
        Vector3DebugStorage.PoseMap.put("YP", 0f);
        Vector3DebugStorage.PoseMap.put("get", 0f);
    }
    public static void toggleDebugMode(){
        if (debugMode){
            debugMode = false;
            return;
        }
        debugMode = true;
    }
    public static void toggleAnimation(){
        if (animation){
            animation = false;
            return;
        }
        startMS = Util.getMillis();
        animation = true;
    }
    public static boolean animation = false;
    public static boolean debugMode = false;
    public static void transformToAnimationStack(PoseStack poseStack){
        float currentMS = Util.getMillis();
        float range = (currentMS - startMS)/1000;
        float max = PoseMap.get("time");
        float time =  range / max;
        Vec3 lerpVec = Mth.lerp(time, startPos.getVec(), endPos.getVec());
        Vec3 qDegrees = Mth.lerp(time, startPos.getVecQ(), endPos.getVecQ());
        if(time < 1){
            poseStack.translate(lerpVec);

            TrippyquestClient.print("time :" + time +" PoseFrame" + lerpVec.toString());

            poseStack.mulPose(Axis.ZP.rotationDegrees((float) qDegrees.z));
            poseStack.mulPose(Axis.YP.rotationDegrees((float) qDegrees.y));
            poseStack.mulPose(Axis.XP.rotationDegrees((float) qDegrees.x));
        }

    }
}
