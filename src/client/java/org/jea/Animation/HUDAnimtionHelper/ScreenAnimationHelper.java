package org.jea.Animation.HUDAnimtionHelper;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import org.jea.TrippyquestClient;
import org.jea.Vector3DebugStorage;

import java.util.AbstractMap;
import java.util.HashMap;


public class ScreenAnimationHelper extends Screen {

    private String diplayofWigets =
                    "####[---Y---]##[---YP---]#########" +
                    "####[---X---]##[---ZP---]#########" +
                    "####[---Z---]##[---XP---]#########" +
                    "##################################" +
                    "[anim]############################" +
                    "[debugMode]#######################" +
                    "[time]############################" +
                    "[setEnd]##########################" +
                    "[setStart]########################";
    private HashMap<String, AbstractWidget> allWidget = new HashMap<>();
    public ScreenAnimationHelper(Component component) {
        super(component);
    }

    @Override
    public void init() {
        super.init();
        Button saveButton = Button.builder(Component.literal("save"), (btn) -> {
            Vector3DebugStorage.PoseMap.forEach((key , value) ->{ TrippyquestClient.print(key + " = " +value);});
        }).bounds(210, 0, 100, 20).build();

        Button setStartPos = Button.builder(Component.literal("Set StartPos"), (btn) -> {
            Vector3DebugStorage.updateStartPos();
        }).bounds(210, 0, 100, 20).build();
        Button setEndPos = Button.builder(Component.literal("Set EndPos"), (btn) -> {
            Vector3DebugStorage.updateEndPos();
        }).bounds(210, 0, 100, 20).build();
        Button Animation = Button.builder(Component.literal("anim"), (btn) -> {
            Vector3DebugStorage.toggleAnimation();
        }).bounds(210, 0, 100, 20).build();
        Button debugMode = Button.builder(Component.literal("Toggle DebugMode"), (btn) -> {
            Vector3DebugStorage.toggleDebugMode();
        }).bounds(210, 0, 100, 20).build();

        allWidget.put("Y",  new SliderPose(0,0, 100,30, Component.literal("Y"), -2, 2));
        allWidget.put("Z", new SliderPose(0,75,100,30, Component.literal("Z"), -2, 2));
        allWidget.put("X",new SliderPose(0,150,100,30,Component.literal("X"), -2 ,2));
        allWidget.put("YP",new SliderPose(110, 0 , 100, 30 , Component.literal("YP"), -360, 360));
        allWidget.put("ZP",new SliderPose(110, 75 , 100, 30 , Component.literal("ZP"),0, 360));
        allWidget.put("XP",new SliderPose(110, 150, 100, 30, Component.literal("XP"),0, 360));
        allWidget.put("time", new SliderPose(0,0,0,0, Component.literal("time"), 0, 10));
        allWidget.put("setEnd", setEndPos);
        allWidget.put("setStart", setStartPos);
        allWidget.put("debugMode", debugMode);
        allWidget.put("anim", Animation);

        parseStringMap();


    }

    private void parseStringMap(){
        StringReader reader = new StringReader(diplayofWigets);
        int rowcount = 1;
        int y =  reader.getTotalLength()/34;
        int x = 34/y;
        int sy = this.height / y;
        int sx = this.width /x;

        while (reader.canRead()){
            switch (reader.peek()){
                case '#':
                    reader.skip();
                    break;
                case '[':
                    try {
                        int startCursorPos = sx * reader.getCursor();
                        String blockreaded = reader.readStringUntil(']');

                        blockreaded = blockreaded.replace("-", "").replace("[","").replace("]", "");

                        int width = sx *reader.getCursor() - startCursorPos;
                        TrippyquestClient.print(blockreaded);
                        AbstractWidget abstractWidget= allWidget.get(blockreaded);


                        addSliderPoseWidget(abstractWidget, sx * reader.getCursor() , sy*(reader.getCursor()/34), width);
                    } catch (CommandSyntaxException e) {
                        TrippyquestClient.print("cursor pos :" + reader.getCursor()+ " string advancement :" + reader.getRead());
                        throw new RuntimeException(e);
                    }
                    break;
                case '{':
                    reader.skip(); //expect after an [ a button r:nameofthewidget
                    if(reader.peek() == 'r'){
                        reader.skip();
                        String StringTargetedWidget = String.valueOf(reader.peek());
                        AbstractWidget TargetWidget = allWidget.get(StringTargetedWidget);
                        createResetButton(TargetWidget, reader.getCursor()%34, rowcount *20, 20);
                    }
                    break;
            }
        }

    }

    private void addSliderPoseWidget(AbstractWidget toadd , int x, int y, int width){
        toadd.setHeight(15);
        toadd.setWidth(width);
        toadd.setPosition(x,y);
        this.addRenderableWidget(toadd);
    }

    private void createResetButton(AbstractWidget abstractWidget, int x , int y , int width){
        if (abstractWidget instanceof SliderPose sliderPose){
        Button resetButton = Button.builder(Component.literal("reset"), (btn) -> {
            Vector3DebugStorage.PoseMap.put(sliderPose.valuename, 0f);
        }).bounds(x, y, width, 20).build();
        this.addRenderableWidget(resetButton);
        }
        else TrippyquestClient.print("try to create a reset button on not expected widget");
    }


    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
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
