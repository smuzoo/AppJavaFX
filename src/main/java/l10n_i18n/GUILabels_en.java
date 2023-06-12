package l10n_i18n;

import collection.HumanBeingCollection;

import java.util.ListResourceBundle;

public class GUILabels_en extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"loginText", "Log in to your account"},
                    {"createAccountText", "Create your account"},
                    {"loginField", "Login"},
                    {"passwordField", "Password"},
                    {"loginButton", "Log In"},
                    {"dontHaveAccountText", "Don't have an account yet?"},
                    {"haveAccountText", "Already have an account?"},
                    {"createAccountButton", "Create"},
                    {"createButton", "Create"},
                    {"invalidAuth", "Invalid username or password"},
                    {"ru", "Russian"},
                    {"sp", "Spanish"},
                    {"it", "English"},
                    {"du", "Dutch"},
                    {"language", "Language:"},
                    {"not exist file", "This file does not exist"},
                    {"not can read file", "This file does not have read access"},
                    {"not can write file", "This file does not have write access"},
                    {"not have errors", "No errors were found"},
                    {"not has element", "There is no element with such key in the collection"},
                    {"not int", "This argument is not an integer"},
                    {"empty field", "This field cannot be empty"},
                    {"bigger x", "The value of the coordinate.x field cannot exceed -809.0"},
                    {"bigger enginePower", "The value of the impactSpeed field cannot exceed 496"},
                    {"not has field", "There is no number with such field"},
                    {"impossible read file", "Unable to read the file"},
                    {"recursion", "Recursion detected"},
                    {"not has two coords", "Two coordinates x and y were not entered"},
                    {"not exist option", "This option does not exist"},
                    {"not space", "This field cannot contain a space"},
                    {"not create this user", "This object was created by another user, you cannot modify it"},
                    {"used login", "This username already exists"},
                    {"unknown error", "Unknown error"},
                    {"exist id", "This id already exists"},
                    {"mapButton", "Map"},
                    {"deleteByIdButton", "Delete by ID"},
                    {"executeScriptButton", "Execute Script"},
                    {"addButton", "Add"},
                    {"leaveButton", "Leave"},
                    {"commands", "Commands"},
                    {"countGreaterSpeedButton", "Count the number of objects with a higher speed"},
                    {"clearButton", "Clear the collection"},
                    {"removeGreaterKeyButton", "Remove keys greater than the specified one"},
                    {"removeGreaterHumanButton", "Remove objects greater than the specified one"},
                    {"removeLowerHumanButton", "Remove objects smaller than the specified one"},
                    {"showCollectionButton", "Show the entire collection"},
                    {"showInfoButton", "Show information about the collection"},
                    {"showLessSpeedButton", "Count the number of objects with a lower speed"},
                    {"helpButton", "Help with commands"},
                    {"searchLabel", "Search"},
                    {"searchField", "Enter for search"},
                    {"updateTableFieldButton", "Update"},
                    {"deleteTableFieldButton", "Delete"},
                    {"closeTableFieldButton", "Close"},
                    {"name", "Name"},
                    {"coordinates", "Coordinates(x,y)"},
                    {"impactSpeed", "Engine Power"},
                    {"isRealHero", "Nitro"},
                    {"hasToothPick", "All-Wheel Drive"},
                    {"weaponType", "Transport Type"},
                    {"mood", "Fuel Type"},
                    {"carCool", "Foreign Car"},
                    {"creation date", "Creation Date"},
                    {"user login", "User Login"},
                    {"to table", "To the table"},
                    {"impossible edit object", "Unable to edit object"},
                    {"not created this user", "You cannot edit this object as you did not create it."},
                    {"confirm coordinate changes", "Confirm Coordinate Changes"},
                    {"want change coordinates", "Do you want to change the object's coordinates?"},
                    {"yes", "Yes"},
                    {"no", "No"},
                    {"ok", "OK"},
                    {"createObject", "Create Object"},
                    {"enter id", "Enter ID"},
                    {"choose script", "Choose a script"},
                    {"info about commands", "Information about Commands"},
                    {"info about collection", "Information about the Collection"},
                    {"info", "Collection HashMap<java.util.UUID, Velicles>\n" +
                            "Collection creation date: " + HumanBeingCollection.getDateOfInitialization() + "\n" +
                            "Date of last collection modification: " + HumanBeingCollection.getDateOfLastChange() + "\n" +
                            "Number of elements in the collection: " + HumanBeingCollection.getCountHumanBeingCollection()},
                    {"info for commands", "filter_less_than_engine_power enginePower : display elements whose enginePower field value is less than the specified\n" +
                            "remove_greater_key null : remove from the collection all elements whose key exceeds the specified\n" +
                            "execute_script file_name : read and execute a script from the specified file. The script contains commands in the same format as the user enters them interactively.\n" +
                            "clear : clear the collection\n" +
                            "remove_greater {element} : remove from the collection all elements greater than the specified\n" +
                            "remove_lower {element} : remove from the collection all elements smaller than the specified\n" +
                            "print_field_descending_fuelType : display the fuelType field values of all elements in descending order\n" +
                            "help : display help for available commands\n" +
                            "count_greater_than_engine_power enginePower : display the number of elements whose impactSpeed field value is greater than the specified\n" +
                            "info : display information about the collection (type, initialization date, number of elements, etc.)\n"
                    },
                    {"count", "Count"},
                    {"enter speed", "Enter speed"},
                    {"impossible clear", "Unable to clear the collection"},
                    {"not admin", "Only the admin has the right to clear the entire collection"},
                    {"dataFormat", "MM.dd.yyyy"}
            };
    public Object[][] getContents() {
        return contents;
    }
}
