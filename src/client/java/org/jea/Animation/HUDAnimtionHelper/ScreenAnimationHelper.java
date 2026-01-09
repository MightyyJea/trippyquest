package org.jea.Animation.HUDAnimtionHelper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ScrollableLayout;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.dialog.input.InputControlHandlers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.loot.IntRange;
import org.jea.Vector3DebugStorage;

public class ScreenAnimationHelper extends Screen {
    public ScreenAnimationHelper(Component component) {
        super(component);
    }

    @Override
    public void init() {
        super.init();

        SliderPoseY Y = new SliderPoseY(0,0, 100,50, Component.literal("Y"), Vector3DebugStorage.y);
        SliderPoseZ Z = new SliderPoseZ(0,75,100,50, Component.literal("Z"),Vector3DebugStorage.z);
        SliderPoseX x = new SliderPoseX(0,150,100,50, Component.literal("X"),Vector3DebugStorage.x);
        SliderPose YP = new SliderPose(110, 0 , 100, 50 , Component.literal("YP"), 0, 360);
        SliderPose ZP = new SliderPose(110, 75 , 100, 50 , Component.literal("ZP"),0, 360);
        SliderPose XP = new SliderPose(110, 150, 100, 50 , Component.literal("XP"),0, 360);
        // It's recommended to use the fixed height of 20 to prevent rendering issues with the button
        // textures.

        // Register the button widget.
        this.addRenderableWidget(Y);
        this.addRenderableWidget(Z);
        this.addRenderableWidget(x);
        this.addRenderableWidget(YP);
        this.addRenderableWidget(XP);
        this.addRenderableWidget(ZP);

    }


    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        guiGraphics.drawString(this.font, "Special Button", 40, 40 - this.font.lineHeight - 10, 0xFFFFFFFF, true);
        //this.renderBackground(guiGraphics, i,j,f);
        guiGraphics.fillGradient(0, 0, this.width, this.height, 0, 0);
        super.render(guiGraphics, i, j, f);
    }


    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        guiGraphics.fillGradient(0, 0, this.width, this.height, 0, 0);
    }

    @Override
    public void removed() {
        super.removed();
    }

    @Override
    public void onClose() {
        super.onClose();
    }
}
