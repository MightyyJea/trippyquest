package org.jea;

import net.minecraft.world.phys.Vec3;

public class PoseFrame {
    public float YP;
    public float XP;
    public float ZP;
    public float x;
    public float y;
    public float z;
    public PoseFrame(){

    }
    public Vec3 getVec(){
        return new Vec3(x,y,z);
    }
    public Vec3 getVecQ(){
        return  new Vec3(XP, YP, ZP);
    }

    @Override
    public String toString() {
        return "PoseFrame{" +
                "YP=" + YP +
                ", XP=" + XP +
                ", ZP=" + ZP +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
