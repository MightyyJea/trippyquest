package org.jea.Animation;

import com.mojang.serialization.Codec;
import jdk.jfr.EventType;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class WeightType<T> {
    private  WeightType(Class<T> type, ResourceLocation resourceLocation){
    }


    public record Weightned(WeightType type, float weight){ }

    public class WeightTypeEventSearch<T, P> implements IEventSearch{
        Consumer<P> eventListener;
        public WeightTypeEventSearch(Class<T> type, ResourceLocation resourceLocation, ){
            super();

        }

        private <Eventype extends Event<?>> void setRegister(Eventype event){
            event,
        }
        @Override
        public <EventType extends Event<?>> CompletableFuture<Weightned> onSearch() {
            return null;
        }
    }
    public class WeightTypeSeach<T> implements ISearch{

    }

}
