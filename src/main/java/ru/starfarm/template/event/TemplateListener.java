package ru.starfarm.template.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import ru.starfarm.core.ApiManager;
import ru.starfarm.template.MyAchievement;

public class TemplateListener {

    @EventHandler
    public void on(PlayerDeathEvent event) {
        event.getEntity().spigot().respawn();

        //Выдача достижения
        ApiManager.getPlayerProfile(event.getEntity()).giveAchievement(MyAchievement.SECOND);
    }

}
