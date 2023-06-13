package l10n_i18n;

import collection.VehicleCollection;

import java.util.ListResourceBundle;

public class GUILabels_sp extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"loginText", "Inicia sesión en tu cuenta"},
                    {"createAccountText", "Crea tu cuenta"},
                    {"loginField", "usuario"},
                    {"passwordField", "contraseña"},
                    {"loginButton", "Iniciar sesión"},
                    {"dontHaveAccountText", "¿No tienes una cuenta?"},
                    {"haveAccountText", "¿Ya tienes una cuenta?"},
                    {"createAccountButton", "Crear"},
                    {"createButton", "Crear"},
                    {"invalidAuth", "Nombre de usuario o contraseña incorrectos"},
                    {"ru", "Ruso"},
                    {"sp", "Español"},
                    {"it", "Inglese"},
                    {"du", "Holandés"},
                    {"language", "Idioma:"},
                    {"not exist file", "Este archivo no existe"},
                    {"not can read file", "Este archivo no tiene permisos de lectura"},
                    {"not can write file", "Este archivo no tiene permisos de escritura"},
                    {"not have errors", "No se encontraron errores"},
                    {"not has element", "No existe un elemento con esa clave en la colección"},
                    {"not int", "Este argumento no es un número entero"},
                    {"empty field", "Este campo no puede estar vacío"},
                    {"bigger x", "El valor del campo coordinate.x no puede ser mayor que -809.0"},
                    {"bigger impactSpeed", "El valor del campo impactSpeed no puede ser mayor que 496"},
                    {"not has field", "No existe un número con ese campo"},
                    {"impossible read file", "No se puede leer el archivo"},
                    {"recursion", "Se detectó recursión"},
                    {"not has two coords", "No se ingresaron dos coordenadas x e y."},
                    {"not exist option", "No existe esta opción"},
                    {"not space", "Este campo no puede contener espacios"},
                    {"not create this user", "Este objeto fue creado por otro usuario, no puedes modificarlo"},
                    {"used login", "Este nombre de usuario ya existe"},
                    {"unknown error", "Error desconocido"},
                    {"exist id", "Este id ya existe"},
                    {"mapButton", "Mapa"},
                    {"deleteByIdButton", "Eliminar por ID"},
                    {"executeScriptButton", "Ejecutar script"},
                    {"addButton", "Agregar"},
                    {"leaveButton", "Salir"},
                    {"commands", "Comandos"},
                    {"countGreaterSpeedButton", "Contar objetos con velocidad mayor"},
                    {"clearButton", "Limpiar colección"},
                    {"removeGreaterKeyButton", "Eliminar claves mayores"},
                    {"removeGreaterHumanButton", "Eliminar objetos mayores"},
                    {"removeLowerHumanButton", "Eliminar objetos menores"},
                    {"showCollectionButton", "Mostrar toda la colección"},
                    {"showInfoButton", "Mostrar información de la colección"},
                    {"showLessSpeedButton", "Contar el número de objetos con una velocidad inferior"},
                    {"helpButton", "Ayuda de comandos"},
                    {"searchLabel", "Búsqueda"},
                    {"searchField", "Ingrese para buscar"},
                    {"updateTableFieldButton", "Actualizar"},
                    {"deleteTableFieldButton", "Eliminar"},
                    {"closeTableFieldButton", "Cerrar"},
                    {"name", "Nombre"},
                    {"coordinates", "Coordenadas(x,y)"},
                    {"impactSpeed", "Velocidad"},
                    {"isRealHero", "Héroe real"},
                    {"hasToothPick", "Tiene palillo dental"},
                    {"weaponType", "Tipo de arma"},
                    {"mood", "Estado de ánimo"},
                    {"carCool", "Auto genial"},
                    {"creation date", "Fecha de creación"},
                    {"user login", "Nombre de usuario"},
                    {"to table", "A la tabla"},
                    {"impossible edit object", "No se puede editar el objeto"},
                    {"not created this user", "No puedes editar este objeto porque no lo creaste."},
                    {"confirm coordinate changes", "Confirmación de cambio de coordenadas"},
                            {"want change coordinates", "¿Quieres cambiar las coordenadas del objeto?"},
                            {"yes", "Sí"},
                            {"no", "No"},
                            {"ok", "OK"},
                            {"createObject", "Crear objeto"},
                            {"enter id", "Ingrese el Id"},
                            {"choose script", "Seleccionar script"},
                            {"info about commands", "Información sobre los comandos"},
                            {"info about collection", "Información sobre la colección"},
                            {"info", "Colección HashMap<java.util.UUID, HumanBeing>\n" +
                                    "Fecha de creación de la colección: " + VehicleCollection.getDateOfInitialization() + "\n" +
                                    "Fecha de la última modificación de la colección: " + VehicleCollection.getDateOfLastChange() + "\n" +
                                    "Cantidad de elementos en la colección: " + VehicleCollection.getCountVehicleCollection()},
                            {"info for commands", "filter_less_than_impact_speed impactSpeed : mostrar elementos cuyo valor de impactSpeed sea menor al valor dado\n" +
                                    "remove_greater_key null : eliminar de la colección todos los elementos cuya clave sea mayor a la clave dada\n" +
                                    "execute_script file_name : leer y ejecutar el script del archivo especificado. El script contiene comandos en el mismo formato que los ingresa el usuario en el modo interactivo.\n" +
                                    "show : mostrar todos los elementos de la colección en el flujo de salida estándar en formato de cadena\n" +
                                    "clear : vaciar la colección\n" +
                                    "insert null {element} : agregar un nuevo elemento con la clave dada\n" +
                                    "update id {element} : actualizar el valor de un elemento de la colección cuyo id sea igual al dado\n" +
                                    "remove_greater {element} : eliminar de la colección todos los elementos que sean mayores al elemento dado\n" +
                                    "remove_lower {element} : eliminar de la colección todos los elementos que sean menores al elemento dado\n" +
                                    "print_field_descending_mood : mostrar los valores del campo mood de todos los elementos en orden descendente\n" +
                                    "help : mostrar ayuda sobre los comandos disponibles\n" +
                                    "count_greater_than_impact_speed impactSpeed : mostrar la cantidad de elementos cuyo valor de impactSpeed sea mayor al valor dado\n" +
                                    "remove_key null : eliminar un elemento de la colección por su clave\n" +
                                    "info : mostrar información sobre la colección (tipo, fecha de inicialización, cantidad de elementos, etc.)\n"
                            },
                    {"count", "Calcule"},
                    {"enter speed", "Introducir velocidad"},
                    {"impossible clear", "No es posible limpiar la colección"},
                    {"not admin", "Solo un administrador puede limpiar toda la colección"},
                    {"dataFormat", "dd/MM/yyyy"},
                    {"error", "Error!"},
                    {"not update", "No se puede actualizar este objeto"},
                    {"not this user", "No se pueden actualizar valores de objetos que no se hayan creado"},



            };
    public Object[][] getContents() {
        return contents;
    }
}
