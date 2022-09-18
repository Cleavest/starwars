package ru.starfarm.template;

import org.jetbrains.annotations.NotNull;
import ru.starfarm.core.donate.ability.CoreDonateAbility;

import java.util.Arrays;
import java.util.List;

public enum MyDonateAbility implements CoreDonateAbility {

    //Абилки называем в замивимости от группы (DonateGroup), либо переопределяем метод getGroup() и называем как хотим
    VIP(" §c- §fДает §aх3 §fбустер денег"),
    VIP_PLUS(" §c- §fДает §aх4 §fбустер денег"),
    //..
    UNIQUE(" §c- §fДает §4OP"),
    //P.S: Не обязательно указывать все группы, но желательно
    ;

    private final List<String> description;

    MyDonateAbility(String... description) {
        this.description = Arrays.asList(description);
    }

    @NotNull
    @Override
    public List<String> getDescription() {
        return description;
    }

}
