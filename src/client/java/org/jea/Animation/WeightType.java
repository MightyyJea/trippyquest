package org.jea.Animation;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public record WeightType<T  >(Class<T> type, ResourceLocation resourceLocation, SearchFunction ValueFinder){
    public record Weightned(WeightType type, float weight){

    }
    @FunctionalInterface
    public interface SearchFunction<R>{
        R onSearch();
    }
    public interface EventListener<E extends Event<L>, L, R, T extends Weightned > extends SearchFunction{
        default Future<T> onListener(E Event, SearchFunction function, WeightType type) throws ExecutionException, InterruptedException {

            CompletableFuture<WeightType.Weightned> futureWeight = CompletableFuture.completedFuture();

            Event.register();
            return futureWeight.isCompletedExceptionally();
        }
    }
}
