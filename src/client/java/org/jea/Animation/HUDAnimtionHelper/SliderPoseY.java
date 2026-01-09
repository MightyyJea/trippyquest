package org.jea.Animation.HUDAnimtionHelper;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import org.jea.Vector3DebugStorage;

public class SliderPoseY extends AbstractSliderButton {
    public SliderPoseY(int i, int j, int k, int l, Component component, double d) {
        super(i, j, k, l, component, d);
    }

    @Override
    protected void updateMessage() {

    }

    @Override
    protected void applyValue() {
        Vector3DebugStorage.y = this.value;
    }
}
