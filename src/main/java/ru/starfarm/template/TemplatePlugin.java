package ru.starfarm.template;

import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.starfarm.core.CorePlugin;

public class TemplatePlugin extends CorePlugin {

    @Override
    protected void enable() {
        //Events
        getEventContext().on(PlayerJoinEvent.class, event -> {
            event.setJoinMessage(null);
            event.getPlayer().giveExp(1_000);
        }, EventPriority.NORMAL);

        getEventContext().on(PlayerQuitEvent.class, event -> {
            event.setQuitMessage(null);
        });
    }

}
