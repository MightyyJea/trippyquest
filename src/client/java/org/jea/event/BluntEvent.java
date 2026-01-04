package org.jea.event;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;


public class BluntEvent {
    public static final Event<ReleaseUsing> RELEASE_EVENT = EventFactory.createArrayBacked(ReleaseUsing.class, (callback)->{
        return (time) ->{
            ReleaseUsing[] releaseUsings =  callback;
            int lenght = callback.length;
            for(int i = 0 ; i < lenght; i++){
                ReleaseUsing event = releaseUsings[i];
                event.onReleaseUsing(time);
            }

        };
    } );
    @Environment(EnvType.CLIENT)
    @FunctionalInterface
    public interface ReleaseUsing{
        void onReleaseUsing(float time);

    }
}
