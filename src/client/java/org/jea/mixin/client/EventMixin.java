package org.jea.mixin.client;

import net.fabricmc.fabric.api.event.Event;
import org.jea.mixinInterface.IEventMixin;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Event.class)
public class EventMixin implements IEventMixin {
    @Override
    public Event getInstance() {
        return (Event) (Object) this;
    }
}
