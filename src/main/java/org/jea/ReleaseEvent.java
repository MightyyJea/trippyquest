package org.jea;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ReleaseEvent {
    public static final Event<ReleaseUsing> RELEASE_EVENT = EventFactory.createArrayBacked(ReleaseUsing.class, (callback)->{
        return (player, level) ->{
            ReleaseUsing[] releaseUsings =  callback;
            int lenght = callback.length;
            for(int i = 0 ; i < lenght; i++){
                ReleaseUsing event = releaseUsings[i];
                event.onReleaseUsing(player, level);
            }

        };
    } );
    @FunctionalInterface
    public interface ReleaseUsing{
        void onReleaseUsing(Player player, Level level);
    }
}
