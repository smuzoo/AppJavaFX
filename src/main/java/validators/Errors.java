package validators;

import collection.Fields;

import static colors.Colors.*;

/**
 * The enum Errors.
 */
public enum Errors {
    /**
     * Nothaveenvironment errors.
     */
    NOTHAVEENVIRONMENT("При запуске программы переменная окружения не была передана," +
            " запустите программу заново, передав переменную окружения"),
    /**
     * Wrongenvironment errors.
     */
    WRONGENVIRONMENT("При запуске программы переменная окружения не имеет никакого значения, " +
            "либо было передано пустое значение переменной окружения, запустите программу заново с указанием переменной окружения"),
    /**
     * Emptyenvironment errors.
     */
    EMPTYENVIRONMENT("Данная переменная окружения имеет пустое значение, " +
            "задайте правильное расположение файла в переменной и запустите программу заново с указанием переменной окружения"),
    /**
     * Notexistfile errors.
     */
    NOTEXISTFILE("Данный файл не существует, передайте в переменную окружения существующий файл"),
    /**
     * Notcanreadfile errors.
     */
    NOTCANREADFILE("Данный файл не имеет права доступа на чтение файла, разрешите доступ для чтения файла"),
    /**
     * Notcanwritefile errors.
     */
    NOTCANWRITEFILE("Данный файл не имеет права доступа на запись данных в файл, разрешите доступ для записи в файл"),
    /**
     * Notcsvfile errors.
     */
    NOTCSVFILE("Данный файл не является csv файлом, передайте в переменную окружения csv-файл"),
    /**
     * Nothaveerrors errors.
     */
    NOTHAVEERRORS("Ошибок не было найдено"),
    /**
     * Nothasargument errors.
     */
    NOTHASARGUMENT("Данная команда принимает на вход 1 аргумент"),
    /**
     * Notcantransformtouuid errors.
     */
    NOTCANTRANSFORMTOUUID("Невозможно преобразовать данный ключ к типу UUID"),
    /**
     * Nothaselement errors.
     */
    NOTHASELEMENT("Элемента с таким ключом не существует в коллекции"),
    /**
     * Notcantransformtoint errors.
     */
    NOTCANTRANSFORMTOINT("Данный аргумент не является целым числом"),
    /**
     * Emptyfield errors.
     */
    EMPTYFIELD("Данное поле не может быть пустым"),
    /**
     * Biggerx errors.
     */
    BIGGERX("Значение поля coordinate.x не может превышать " + Fields.MAXIMUM_X),
    /**
     * Biggerimpactspeed errors.
     */
    BIGGERIMPACTSPEED("Значение поля impactSpeed не может превышать " + Fields.MAXIMUM_IMPACT_SPEED),
    /**
     * Notcantrasformtouuid errors.
     */
    NOTCANTRASFORMTOUUID("Данное значение не корректно для UUID"),
    /**
     * Nothasfield errors.
     */
    NOTHASFIELD("Номера с таким полем не существует"),
    /**
     * Impossiblereadfile errors.
     */
    IMPOSSIBLEREADFILE("Невозможно прочитать файл"),
    /**
     * Impossiblewritetofile errors.
     */
    IMPOSSIBLEWRITETOFILE("Невозможно записать данные в файл"),
    /**
     * Manyrecursions errors.
     */
    RECURSION("Обнаружена рекурсия"),
    /**
     * Nothastwocoordinates errors.
     */
    NOTHASTWOCOORDINATES("Не было введено 2 координаты x и y."),

    ISNOTEXISTENTOPTION("Данного варианта не существует"),

    ISHAVESPACE("Данное поле не может содержать пробел"),
    /**
     * Usedid errors.
     */
    USEDID("Данный id уже существует");
    /**
     * The Error.
     */
    final String error;
    Errors(String error){
        this.error = error;
    }

    @Override
    public String toString() {
        return RED + error + RESET;
    }
}
