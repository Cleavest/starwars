package ru.starfarm.template;

import org.bukkit.Material;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.starfarm.core.ApiManager;
import ru.starfarm.core.CorePlugin;
import ru.starfarm.core.profile.achievement.AchievementSection;
import ru.starfarm.template.command.TemplateCommand;
import ru.starfarm.template.event.TemplateListener;

public class TemplatePlugin extends CorePlugin {

    @Override
    protected void enable() {
        //Регистрация функциональных эвенов
        getEventContext().on(PlayerJoinEvent.class, event -> {
            event.setJoinMessage(null);
            event.getPlayer().giveExp(1_000);
            //Выдача достижения
            ApiManager.getPlayerProfile(event.getPlayer()).giveAchievement(MyAchievement.FIRST);
        }, EventPriority.NORMAL);
        getEventContext().on(PlayerQuitEvent.class, event -> {
            event.setQuitMessage(null);
        });
        //Регистрация эвентов через классы слушателей
        //Класс слушателя не обязан реализовывать Listener
        getEventContext().onListeners(new TemplateListener());

        //Регистрация анотированной команды
        //Сервис пройдется по всем классам в указанном пакете и зарегистрирует анотированные
        registerBaseCommands("ru.starfarm.template.command");
        //Регистрация обычной команды
        registerCommands(new TemplateCommand());
    }

    @Override
    public void handleTowerConnect() {
        //Регистрация своих атчивок
        ApiManager.appendAchievements(
                //В секции указываем id, ОБЯЗАТЕЛЬНО должен совпадать с id в классе атчивок (КАПСОМ), имя секции (режима) и иконку
                new AchievementSection("MY", "§aМой режим", Material.DIAMOND_SWORD),
                MyAchievement.class
        );
    }
}
