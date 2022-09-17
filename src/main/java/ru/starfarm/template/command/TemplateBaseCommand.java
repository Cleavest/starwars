package ru.starfarm.template.command;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.starfarm.core.command.Command;
import ru.starfarm.core.command.base.annotation.*;
import ru.starfarm.core.command.context.CommandContext;
import ru.starfarm.core.profile.group.DonateGroup;
import ru.starfarm.core.profile.group.StaffGroup;

@BaseCommandDonateRequire(groups = DonateGroup.VIP) //Не обязательно, добавляет требование иметь группу >= VIP
@BaseCommandStaffRequire(groups = StaffGroup.YT) //Не обязательно, добавляет требование иметь группу >= YT
@BaseCommandSubs(inheritPrefix = true, commands = {}) //Установит под-командам префикс от основной
@BaseCommandPrefix(prefix = "§aTemplate §e> §f") //Установит префикс команды
@BaseCommand(name = "template", description = "Пример анотированной команды", aliases = "tmpl") //Задаст основную информацию
public class TemplateBaseCommand extends Command<Player> {

    @Override
    @BaseCommandParameter(name = "Число", type = "Integer") //Анотация для добавления параметра
    public void execute(@NotNull CommandContext<Player> ctx) {
        ctx.sendMessage("Введенное число - %s", ctx.<Integer>getArg(0));
    }

    @BaseCommand(name = "sub", description = "Пример аннотированной под-команды")
    @BaseCommandParameters( //Второй вариант установки параметров для команды, через масив анотаций
            parameters = @BaseCommandParameter(name = "Строка", type = "String", args = {"3", "16"})
            //В args передаются параметры возможного конструктора StringType
            //Так же есть параметр required позволяющий делать параметр не обязательным
    )
    public void executeSub(@NotNull CommandContext<Player> ctx) { //Название метода ни на что не влияет
        ctx.sendMessage("Введенная строка - %s", ctx.<String>getArg(0));
    }

}
