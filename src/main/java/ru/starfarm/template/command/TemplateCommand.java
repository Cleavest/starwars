package ru.starfarm.template.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.starfarm.core.command.Command;
import ru.starfarm.core.command.base.annotation.*;
import ru.starfarm.core.command.context.CommandContext;
import ru.starfarm.core.command.require.Require;
import ru.starfarm.core.command.type.TypeInteger;
import ru.starfarm.core.command.type.TypeString;
import ru.starfarm.core.profile.group.DonateGroup;
import ru.starfarm.core.profile.group.StaffGroup;

public class TemplateCommand extends Command<Player> {

    public TemplateCommand() {
        super("template", "Пример обычной команды", "tmpl"); //Базовая информация

        setPrefix("§aTemplate §e> §f"); //Установка префикса (будет автоматически добавляться при отправки сообщения через контекст)
        addRequire(Require.groups(DonateGroup.VIP, StaffGroup.YT)); //Создание требования доступа от YT или от VIP

        addParameter("Число", new TypeInteger()); //Добавляем параметр числа

        addCommand(new SubCommand()); //Добавляем под-кмаонду
    }

    @Override
    public void execute(@NotNull CommandContext<Player> ctx) {
        ctx.sendMessage("Введенное число - %s", ctx.<Integer>getArg(0));
    }

    class SubCommand extends Command<Player> {

        public SubCommand() {
            super("sub", "Пример обычной под-команды"); //Базовая информация

            setPrefix("§aTemplate §e> §f"); //Установка префикса

            addParameter("Строка", new TypeString(3, 16)); //Добавляем параметр строки
        }

        @Override
        public void execute(@NotNull CommandContext<Player> ctx) {
            ctx.sendMessage("Введенное число - %s", ctx.<Integer>getArg(0));
        }

    }

}