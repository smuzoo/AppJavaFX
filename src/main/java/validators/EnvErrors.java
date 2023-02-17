package validators;

public enum EnvErrors {
    NOTHAVEENVIRONMENT("При запуске программы переменная окружения не была передана," +
            " запустите программу заново, передав переменную окружения"),
    WRONGENVIRONMENT("При запуске программы была неправильно передана переменная окружения," +
            " запустите программу заново с указанием переменной окружения"),
    EMPTYENVIRONMENT("Данная переменная окружения пустая, задайте правильное расположение файла в переменной и " +
            "запустите программу заново с указанием переменной окружения");

    final String error;
    EnvErrors(String error){
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
