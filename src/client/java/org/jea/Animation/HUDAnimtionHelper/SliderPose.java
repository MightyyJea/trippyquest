package org.jea.Animation.HUDAnimtionHelper;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jea.TrippyquestClient;
import org.jea.Vector3DebugStorage;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SliderPose extends AbstractSliderButton {
    public String valuename;
    public int MAX;
    public int MIN;
    public int range;
    private boolean isnegative = false;
    private double offValue;
    public SliderPose(int i, int j, int k, int l, Component component, int min, int max) {
        super(i, j, k, l, component,0.5f);
        valuename = component.getString();
        MAX = max;
        MIN = min;
        range = MAX - MIN;
        if(MIN < 0){
            isnegative =true;
        }
    }

    @Override
    protected void updateMessage() {
        double valueRatio = value * range;
        if (isnegative){
            valueRatio = (value  - 0.5d) * range;
        }
        this.setMessage(Component.literal(valuename+" :" + (float)valueRatio));
    }

    @Override
    protected void applyValue() {
        double valueRatio = value * range;
        if (isnegative){
            valueRatio = (value  - 0.5d) * range;
        }

        Vector3DebugStorage.PoseMap.put(valuename, (float)valueRatio);
    }
}
