package org.jea.mixinInterface;

import net.fabricmc.fabric.api.event.Event;

public interface IEventMixin<T extends Event> {

    public T getInstance();
}
