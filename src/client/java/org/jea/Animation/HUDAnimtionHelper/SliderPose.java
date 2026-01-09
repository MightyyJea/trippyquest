package org.jea.Animation.HUDAnimtionHelper;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import org.jea.Vector3DebugStorage;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SliderPose extends AbstractSliderButton {
    public String valuename;
    public int MAX;
    public int MIN;
    public int range;
    public SliderPose(int i, int j, int k, int l, Component component, int min, int max) {
        super(i, j, k, l, component,0d);
        valuename = component.getString();
        if(Vector3DebugStorage.PoseMap.containsKey(valuename)){
            value = Vector3DebugStorage.PoseMap.get(valuename);
        }else
            Vector3DebugStorage.PoseMap.put(valuename, 0f);
        this.value = (double)Vector3DebugStorage.PoseMap.get(valuename);
        MAX = max;
        MIN = min;
        range = MAX - MIN;

    }

    @Override
    protected void updateMessage() {
        double valueRatio = value * range;
        this.setMessage(Component.literal(valuename+" :" + (float)valueRatio));
    }

    @Override
    protected void applyValue() {
        double valueRatio = value * range;
        Vector3DebugStorage.PoseMap.put(valuename, (float)valueRatio);
    }
}
