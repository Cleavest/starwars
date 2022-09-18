package ru.starfarm.template;

import net.minecraft.server.v1_12_R1.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
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

        //Использование тасков
        //Вызовет выполнение таска через 20 тиков, так же есть afterAsync , выполнит то же, но асинхронно
        getTaskContext().after(20 /*тики*/, task -> {
            Bukkit.getWorlds().get(0).getEntities().forEach(Entity::remove);
        });
        EntityLiving entity = (EntityLiving) Bukkit.getWorlds().get(0).getEntities().stream().findFirst().get();
        //Вызывает выполнение таска через 10 тиков, каждые 20 тиков, так же есть everyAsync, 3 параметр числа не обязателен
        getTaskContext().every(10, 20, 20 /*кол-во повторений (не обязательно)*/, task -> {
            entity.heal(2);
        }).setOnTerminate(task -> entity.killEntity()); //Функция по окончанию таска, не обязательная

        //Регистрация анотированной команды
        //Сервис пройдется по всем классам в указанном пакете и зарегистрирует анотированные
        registerBaseCommands("ru.starfarm.template.command");
        //Регистрация обычной команды
        registerCommands(new TemplateCommand());
    }

    @Override
    public void handleTowerConnect() {
        //Регистрация своих атчивок, с указанием названием секции и иконки
        ApiManager.appendAchievements("§aМой режим", Material.DIAMOND_SWORD, MyAchievement.class);
        //Регистрация своих донат возможностей
        ApiManager.appendDonateAbilities("§aМой режим", Material.DIAMOND_SWORD, MyDonateAbility.class);
    }
}
