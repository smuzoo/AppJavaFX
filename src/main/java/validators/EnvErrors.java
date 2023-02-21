package validators;

import static colors.Colors.RED;

public enum EnvErrors {
    NOTHAVEENVIRONMENT(RED + "При запуске программы переменная окружения не была передана, " +
            " запустите программу заново, передав переменную окружения" + RED),
    WRONGENVIRONMENT(RED + "При запуске программы переменная окружения не имеет никакого значения, " +
            "либо было передано значение переменной окружения запустите программу заново с указанием переменной окружения" + RED),
    EMPTYENVIRONMENT(RED + "Данная переменная окружения имеет пустое значение, задайте правильное расположение файла в переменной и " +
            "запустите программу заново с указанием переменной окружения" + RED),
    NOTHAVEERRORS(RED + "Ошибок не было найдено" + RED);

    final String error;
    EnvErrors(String error){
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
