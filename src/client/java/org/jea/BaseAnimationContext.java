package org.jea;

import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.serialization.Codec;
import com.sun.tools.javac.jvm.Code;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.timers.TimerQueue;
import org.jea.Animation.WeightType;
import org.jea.Animation.WeightTypes;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class BaseAnimationContext {
    public BaseAnimationContext(){
    }


    public void tickSearching(){
        for(WeightType weightType : WeightTypes.WeightTypeRegistry.stream().toList()){
            weightType.ValueFinder().onSearch();
        }
        LocalPlayer player = Minecraft.getInstance().player;
        player.getEntityData().get(SynchedEntityData.defineId(LivingEntity.class, EntityDataSerializers.BYTE));
    }
    public<ArgEvent extends Consumer<?>, EventType extends Event<ArgEvent>> void EventCrawler(ArgEvent p, EventType event){
        event.register(p);
    }
    public record Context(WeightType.Weightned... value){


    }
}
