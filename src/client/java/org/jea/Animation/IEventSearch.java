package org.jea.Animation;

import net.fabricmc.fabric.api.event.Event;

import java.util.concurrent.CompletableFuture;

public interface IEventSearch {
    <EventType extends Event<?>>CompletableFuture<WeightType.Weightned> onSearch();
}
