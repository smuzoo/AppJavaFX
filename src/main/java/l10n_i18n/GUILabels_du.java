package l10n_i18n;

import collection.HumanBeingCollection;

import java.time.format.DateTimeFormatter;
import java.util.ListResourceBundle;

public class GUILabels_du extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"loginText", "Log in op uw account"},
                    {"createAccountText", "Maak uw account aan"},
                    {"loginField", "Gebruikersnaam"},
                    {"passwordField", "Wachtwoord"},
                    {"loginButton", "Inloggen"},
                    {"dontHaveAccountText", "Heeft u geen account?"},
                    {"haveAccountText", "Heeft u al een account?"},
                    {"createAccountButton", "Maak aan"},
                    {"createButton", "Aanmaken"},
                    {"invalidAuth", "Ongeldige gebruikersnaam of wachtwoord"},
                    {"ru", "Russisch"},
                    {"sp", "Spaans"},
                    {"it", "Engelsk"},
                    {"du", "Nederlands"},
                    {"language", "Taal:"},
                    {"not exist file", "Dit bestand bestaat niet"},
                    {"not can read file", "Dit bestand heeft geen leestoegang"},
                    {"not can write file", "Dit bestand heeft geen schrijftoegang"},
                    {"not have errors", "Er zijn geen fouten gevonden"},
                    {"not has element", "Het element met die sleutel bestaat niet in de collectie"},
                    {"not int", "Dit argument is geen geheel getal"},
                    {"empty field", "Dit veld kan niet leeg zijn"},
                    {"bigger x", "De waarde van het veld coordinate.x kan niet groter zijn dan -809,0"},
                    {"bigger impactSpeed", "De waarde van het veld impactSpeed kan niet groter zijn dan 496"},
                    {"not has field", "Er zijn geen nummers met dat veld"},
                    {"impossible read file", "Kan het bestand niet lezen"},
                    {"recursion", "Recursie gedetecteerd"},
                    {"not has two coords", "Er zijn geen 2 x- en y-coördinaten ingevoerd."},
                    {"not exist option", "Deze optie bestaat niet"},
                    {"not space", "Dit veld mag geen spaties bevatten"},
                    {"not create this user", "Dit object is gemaakt door een andere gebruiker, je kunt het niet wijzigen"},
                    {"used login", "Deze gebruikersnaam bestaat al"},
                    {"unknown error", "Onbekende fout"},
                    {"exist id", "Dit id bestaat al"},
                    {"mapButton", "Kaart"},
                    {"deleteByIdButton", "Verwijderen op ID"},
                    {"executeScriptButton", "Script uitvoeren"},
                    {"addButton", "Toevoegen"},
                    {"leaveButton", "Verlaten"},
                    {"commands", "Opdrachten"},
                    {"countGreaterSpeedButton", "Tel het aantal objecten met een hogere snelheid"},
                    {"clearButton", "Collectie wissen"},
                    {"removeGreaterKeyButton", "Verwijder sleutels die hoger zijn dan opgegeven"},
                    {"removeGreaterHumanButton", "Verwijder objecten die hoger zijn dan opgegeven"},
                    {"removeLowerHumanButton", "Verwijder objecten die lager zijn dan opgegeven"},
                    {"showCollectionButton", "Toon de hele collectie"},
                    {"showInfoButton", "Toon informatie over de collectie"},
                    {"showLessSpeedButton", "Tel het aantal objecten met een lagere snelheid"},
                    {"helpButton", "Help met opdrachten"},
                    {"searchLabel", "Zoeken"},
                    {"searchField", "Voer zoekterm in"},
                    {"updateTableFieldButton", "Bijwerken"},
                    {"deleteTableFieldButton", "Verwijderen"},
                    {"closeTableFieldButton", "Sluiten"},
                    {"name", "Naam"},
                    {"coordinates", "Coördinaten(x,y)"},
                            {"impactSpeed", "Snelheid"},
                            {"isRealHero", "Echte held"},
                            {"hasToothPick", "Friemelt in tanden"},
                            {"weaponType", "Wapentype"},
                            {"mood", "Humeur"},
                            {"carCool", "Coole auto"},
                            {"creation date", "Aanmaakdatum"},
                            {"user login", "Gebruikersnaam"},
                            {"to table", "Naar de tabel"},
                            {"impossible edit object", "Kan object niet bewerken"},
                            {"not created this user", "Je kunt dit object niet bewerken omdat je het niet hebt gemaakt."},
                            {"confirm coordinate changes", "Bevestiging van coördinatenwijzigingen"},
                            {"want change coordinates", "Wilt u de coördinaten van het object wijzigen?"},
                            {"yes", "Ja"},
                            {"no", "Nee"},
                            {"ok", "OK"},
                            {"createObject", "Object aanmaken"},
                            {"enter id", "Voer ID in"},
                            {"choose script", "Kies een script"},
                            {"info about commands", "Informatie over opdrachten"},
                            {"info about collection", "Informatie over de collectie"},
                            {"info", "HashMap<java.util.UUID, HumanBeing> Collectie\n" +
                                    "Datum van collectiecreatie: " + HumanBeingCollection.getDateOfInitialization() + "\n" +
                                    "Datum van laatste wijziging in collectie: " + HumanBeingCollection.getDateOfLastChange() + "\n" +
                                    "Aantal elementen in de collectie: " + HumanBeingCollection.getCountHumanBeingCollection()},
                            {"info for commands", "filter_less_than_impact_speed impactSpeed : Geeft elementen weer waarvan de impactSpeed kleiner is dan opgegeven\n" +
                                    "remove_greater_key null : Verwijdert alle elementen uit de collectie waarvan de sleutel groter is dan opgegeven\n" +
                                    "execute_script file_name : Leest en voert een script uit het opgegeven bestand uit. Het script bevat opdrachten zoals gebruikers ze in de interactieve modus invoeren.       \n" +
                                    "show : Toont alle elementen van de collectie in de standaarduitvoer als tekenreeks\n" +
                                    "clear : Maakt de collectie leeg\n" +
                                    "insert null {element} : Voegt een nieuw element met de opgegeven sleutel toe\n" +
                                    "update id {element} : Werkt de waarde van het element in de collectie bij waarvan het id overeenkomt met het opgegeven id\n" +
                                    "remove_greater {element} : Verwijdert alle elementen uit de collectie die groter zijn dan opgegeven\n" +
                                    "remove_lower {element} : Verwijdert alle elementen uit de collectie die kleiner zijn dan opgegeven\n" +
                                    "print_field_descending_mood : Toont de waarden van het mood-veld van alle elementen in aflopende volgorde\n" +
                                    "help : Toont hulp voor beschikbare opdrachten\n" +
                                    "count_greater_than_impact_speed impactSpeed : Toont het aantal elementen waarvan de impactSpeed groter is dan opgegeven\n" +
                                    "remove_key null : Verwijdert het element uit de collectie met de opgegeven sleutel\n" +
                                    "info : Toont informatie over de collectie in de standaarduitvoer (type, initialisatiedatum, aantal elementen, enz.)\n"
},
                    {"count", "Bereken"},
                    {"enter speed", "Snelheid invoeren"},
                    {"impossible clear", "Het is niet mogelijk om de collectie te wissen"},
                    {"not admin", "Alleen een beheerder kan de hele collectie wissen"},
                    {"dataFormat", "dd-MM-yyyy"}

            };
    public Object[][] getContents() {
        return contents;
    }
}
