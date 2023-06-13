package l10n_i18n;

import collection.HumanBeingCollection;
import java.util.ListResourceBundle;

public class GUILabels_ru extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"loginText", "Войдите в свой аккаунт"},
                    {"createAccountText", "Создайте свой аккаунт"},
                    {"loginField", "логин"},
                    {"passwordField", "пароль"},
                    {"loginButton", "Войти"},
                    {"dontHaveAccountText", "Ещё нет аккаунта?"},
                    {"haveAccountText", "Уже есть аккаунт?"},
                    {"createAccountButton", "Создайте"},
                    {"createButton", "Создать"},
                    {"invalidAuth", "Неверное имя пользователя или пароль"},
                    {"ru", "Русский"},
                    {"sp", "Испанский"},
                    {"it", "Английский"},
                    {"du", "Нидерландский"},
                    {"language", "Язык:"},
                    {"not exist file", "Данный файл не существует"},
                    {"not can read file", "Данный файл не имеет права доступа на чтение файла"},
                    {"not can write file", "Данный файл не имеет права доступа на запись данных в файл"},
                    {"not have errors", "Ошибок не было найдено"},
                    {"not has element", "Элемента с таким ключом не существует в коллекции"},
                    {"not int", "Данный аргумент не является целым числом"},
                    {"empty field", "Данное поле не может быть пустым"},
                    {"bigger x", "Значение поля coordinate.x не может превышать -809.0"},
                    {"bigger enginePower", "Значение поля impactSpeed не может превышать 496"},
                    {"not has field", "Номера с таким полем не существует"},
                    {"impossible read file", "Невозможно прочитать файл"},
                    {"recursion", "Обнаружена рекурсия"},
                    {"not has two coords", "Не было введено 2 координаты x и y."},
                    {"not exist option", "Данного варианта не существует"},
                    {"not space", "Данное поле не может содержать пробел"},
                    {"not create this user", "Данный объект был создан другим пользователем, Вы не можете его модифицировать"},
                    {"used login", "Такое имя пользователя уже существует"},
                    {"unknown error", "Неизвестная ошибка"},
                    {"exist id", "Данный id уже существует"},
                    {"mapButton", "Карта"},
                    {"deleteByIdButton", "Удалить по ID"},
                    {"executeScriptButton", "Выполнить скрипт"},
                    {"addButton", "Добавить"},
                    {"leaveButton", "Выйти"},
                    {"commands", "Команды"},
                    {"countGreaterSpeedButton", "Посчитать количество объектов с большей скоростью"},
                    {"clearButton", "Очистить коллекцию"},
                    {"removeGreaterKeyButton", "Удалить ключи, превышающий заданный"},
                    {"removeGreaterHumanButton", "Удалить объекты, превышающий заданный"},
                    {"removeLowerHumanButton", "Удалить объекты меньше заданного"},
                    {"showCollectionButton", "Показать всю коллекцию"},
                    {"showInfoButton", "Показать информацию о коллекции"},
                    {"showLessSpeedButton", "Посчитать количество объектов с меньшей скоростью"},
                    {"helpButton", "Помощь по командам"},
                    {"searchLabel", "Поиск"},
                    {"searchField", "Введите для поиска"},
                    {"updateTableFieldButton", "Обновить"},
                    {"deleteTableFieldButton", "Удалить"},
                    {"closeTableFieldButton", "Закрыть"},
                    {"name", "Имя"},
                    {"coordinates", "Координаты(x,y)"},
                    {"impactSpeed", "Сила двигателя"},
                    {"isRealHero", "Нитро"},
                    {"hasToothPick", "Полный привод"},
                    {"weaponType", "Тип транспорта"},
                    {"mood", "Тип топлива"},
                    {"carCool", "Общественный"},
                    {"creation date", "Дата создания"},
                    {"user login", "Имя пользователя"},
                    {"to table", "К таблице"},
                    {"impossible edit object", "Невозможно редактировать объект"},
                    {"not created this user", "Вы не можете редактировать данный объект, так как не вы его создали."},
                    {"confirm coordinate changes", "Подтверждение изменения координат"},
                    {"want change coordinates", "Хотите изменить координаты объекта?"},
                    {"yes", "Да"},
                    {"no", "Нет"},
                    {"ok", "ОК"},
                    {"createObject", "Создание объекта"},
                    {"enter id", "Введите Id"},
                    {"choose script", "Выберите скрипт"},
                    {"info about commands", "Информация о командах"},
                    {"info about collection", "Информация о коллекции"},
                    {"info", "Коллекция HashMap<java.util.UUID, Velicles>\n" +
                            "Дата создания коллекции:" + HumanBeingCollection.getDateOfInitialization() + "\n" +
                            "Дата последнего изменения коллекции: " + HumanBeingCollection.getDateOfLastChange() + "\n" +
                            "Количество элементов в коллекции: " + HumanBeingCollection.getCountHumanBeingCollection()},
                    {"info for commands", "filter_less_than_engine_power enginePower : вывести элементы, значение поля enginePower которых меньше заданного\n" +
                            "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                            "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.       \n" +
                            "clear : очистить коллекцию\n" +
                            "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                            "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                            "print_field_descending_fuelType : вывести значения поля fuelType всех элементов в порядке убывания\n" +
                            "help : вывести справку по доступным командам\n" +
                            "count_greater_than_engine_power enginePower  вывести количество элементов, значение поля impactSpeed которых больше заданного\n" +
                            "info :  вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n"
                    },
                    {"count", "Посчитать"},
                    {"enter speed", "Введите скорость"},
                    {"impossible clear", "Невозможно очистить коллекцию"},
                    {"not admin", "Только у админа есть право очищать всю коллекцию"},
                    {"dataFormat", "dd.MM.yyyy"}




            };
    public Object[][] getContents() {
        return contents;
    }
}
