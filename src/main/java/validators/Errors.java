package validators;

import collection.Fields;
import l10n_i18n.CurrentLanguage;

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
    NOTEXISTFILE("not exist file"),
    /**
     * Notcanreadfile errors.
     */
    NOTCANREADFILE("not can read file"),
    /**
     * Notcanwritefile errors.
     */
    NOTCANWRITEFILE("not can write file"),
    /**
     * Notcsvfile errors.
     */
    NOTCSVFILE("Данный файл не является csv файлом, передайте в переменную окружения csv-файл"),
    /**
     * Nothaveerrors errors.
     */
    NOTHAVEERRORS("not have errors"),
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
    NOTHASELEMENT("not has element"),
    /**
     * Notcantransformtoint errors.
     */
    NOTCANTRANSFORMTOINT("not int"),
    /**
     * Emptyfield errors.
     */
    EMPTYFIELD("empty field"),
    /**
     * Biggerx errors.
     */
    BIGGERX("bigger x"),
    /**
     * Biggerimpactspeed errors.
     */
    BIGGERIMPACTSPEED("bigger impactSpeed"),

    /**
     * Nothasfield errors.
     */
    NOTHASFIELD("not has field"),
    /**
     * Impossiblereadfile errors.
     */
    IMPOSSIBLEREADFILE("impossible read file"),
    /**
     * Impossiblewritetofile errors.
     */
    IMPOSSIBLEWRITETOFILE("Невозможно записать данные в файл"),
    /**
     * Manyrecursions errors.
     */
    RECURSION("recursion"),
    /**
     * Nothastwocoordinates errors.
     */
    NOTHASTWOCOORDINATES("not has two coords"),

    /**
     * Isnotexistentoption errors.
     */
    ISNOTEXISTENTOPTION("not exist option"),

    /**
     * Ishavespace errors.
     */
    ISHAVESPACE("not space"),

    /**
     * Notcreatethisuser errors.
     */
    NOTCREATETHISUSER("not create this user"),
    /**
     * Usedid errors.
     */
    USEDLOGIN("used login"),

    UNKNOWNERROR("unknown error"),

    USEDID("exist id");
    /**
     * The Error.
     */
    final String error;
    Errors(String error){
        this.error = error;
    }

    public String getError() {
        return CurrentLanguage.getCurrentLanguage().getString(getNameError());
    }

    public String getNameError(){
        return error;
    }

    @Override
    public String toString() {
        return RED + error + RESET;
    }
}
