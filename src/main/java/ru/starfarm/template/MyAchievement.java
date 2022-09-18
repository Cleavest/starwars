package ru.starfarm.template;

import org.jetbrains.annotations.NotNull;
import ru.starfarm.core.profile.achievement.CoreAchievement;

import java.util.Arrays;
import java.util.List;

public enum MyAchievement implements CoreAchievement {

    FIRST("Достижение #1", "Зайдите на этот режим"),
    SECOND("Достижение #2", "Умрите на этом режиме")
    ;

    //Отображаемое имя достижения
    private final String name;
    //Отображаемое описание достижения
    private final List<String> description;

    MyAchievement(String name, String... description) {
        this.name = name;
        this.description = Arrays.asList(description);
    }

    //Отображаемое описание достижения
    @NotNull
    @Override
    public List<String> getDescription() {
        return description;
    }

    //Отображаемое имя достижения
    @NotNull
    @Override
    public String getDisplayName() {
        return name;
    }

    //Секция достижения, ставится в зависимости от режима (PRISON, SKYBLOCK, SKYWARS) КАПСОМ!!
    @NotNull
    @Override
    public String getSection() {
        return "MY";
    }

}
