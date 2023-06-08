package l10n_i18n;

import collection.HumanBeingCollection;

import java.util.ListResourceBundle;

public class GUILabels_it extends ListResourceBundle {
    private static final Object[][] contents =
            {

    {"loginText", "Accedi al tuo account"},
    {"createAccountText", "Crea un account"},
    {"loginField", "login"},
    {"passwordField", "password"},
    {"loginButton", "Accedi"},
    {"dontHaveAccountText", "Non hai un account?"},
    {"haveAccountText", "Hai già un account?"},
    {"createAccountButton", "Crea"},
    {"createButton", "Crea"},
    {"invalidAuth", "Nome utente o password non validi"},
    {"ru", "Russo"},
    {"sp", "Spagnolo"},
    {"it", "Italiano"},
    {"du", "Olandese"},
    {"language", "Lingua:"},
    {"not exist file", "Questo file non esiste"},
    {"not can read file", "Impossibile leggere il file"},
    {"not can write file", "Impossibile scrivere sul file"},
    {"not have errors", "Nessun errore rilevato"},
    {"not has element", "Non esiste un elemento con questa chiave nella collezione"},
    {"not int", "L'argomento non è un numero intero"},
    {"empty field", "Questo campo non può essere vuoto"},
    {"bigger x", "Il valore del campo coordinate.x non può superare -809.0"},
    {"bigger impactSpeed", "Il valore del campo impactSpeed non può superare 496"},
    {"not has field", "Non esistono numeri con questo campo"},
    {"impossible read file", "Impossibile leggere il file"},
    {"recursion", "Rilevata ricorsione"},
    {"not has two coords", "Non sono state inserite due coordinate x e y."},
    {"not exist option", "Questa opzione non esiste"},
    {"not space", "Questo campo non può contenere spazi"},
    {"not create this user", "Questo oggetto è stato creato da un altro utente e non puoi modificarlo"},
    {"used login", "Questo nome utente è già in uso"},
    {"unknown error", "Errore sconosciuto"},
    {"exist id", "Questo id esiste già"},
    {"mapButton", "Mappa"},
    {"deleteByIdButton", "Elimina per ID"},
    {"executeScriptButton", "Esegui script"},
    {"addButton", "Aggiungi"},
    {"leaveButton", "Esci"},
    {"commands", "Comandi"},
    {"countGreaterSpeedButton", "Conta gli oggetti con velocità maggiore"},
    {"clearButton", "Cancella la collezione"},
    {"removeGreaterKeyButton", "Rimuovi le chiavi superiori a quella specificata"},
    {"removeGreaterHumanButton", "Rimuovi gli oggetti superiori a quelli specificati"},
    {"removeLowerHumanButton", "Rimuovi gli oggetti inferiori a quelli specificati"},
    {"showCollectionButton", "Mostra tutta la collezione"},
    {"showInfoButton", "Mostra informazioni sulla collezione"},
    {"showLessSpeedButton", "Contare il numero di oggetti con una velocità inferiore"},
    {"helpButton", "Guida ai comandi"},
    {"searchLabel", "Ricerca"},
    {"searchField", "Inserisci per cercare"},
    {"updateTableFieldButton", "Aggiorna"},
    {"deleteTableFieldButton", "Elimina"},
    {"closeTableFieldButton", "Chiudi"},
    {"name", "Nome"},
    {"coordinates", "Coordinate"},
    {"impactSpeed", "Velocità"},
    {"isRealHero", "Eroe vero"},
    {"hasToothPick", "Ha lo stuzz icadenti"},
        {"weaponType", "Tipo di arma"},
        {"mood", "Umore"},
        {"carCool", "Macchina figa"},
        {"creation date", "Data di creazione"},
        {"user login", "Nome utente"},
        {"to table", "Alla tabella"},
        {"impossible edit object", "Impossibile modificare l'oggetto"},
        {"not created this user", "Non puoi modificare questo oggetto poiché non l'hai creato tu."},
        {"confirm coordinate changes", "Conferma le modifiche alle coordinate"},
        {"want change coordinates", "Vuoi modificare le coordinate dell'oggetto?"},
        {"yes", "Sì"},
        {"no", "No"},
        {"ok", "OK"},
        {"createObject", "Creazione oggetto"},
        {"enter id", "Inserisci ID"},
        {"choose script", "Scegli lo script"},
        {"info about commands", "Informazioni sui comandi"},
        {"info about collection", "Informazioni sulla collezione"},
        {"info", "Collezione HashMap<java.util.UUID, HumanBeing>\n" +
                "Data di creazione della collezione: " + HumanBeingCollection.getDateOfInitialization() + "\n" +
                "Data dell'ultima modifica della collezione: " + HumanBeingCollection.getDateOfLastChange() + "\n" +
                "Numero di elementi nella collezione: " + HumanBeingCollection.getCountHumanBeingCollection()},
        {"info for commands", "filter_less_than_impact_speed impactSpeed: visualizza gli elementi con valore del campo impactSpeed inferiore a quello specificato\n" +
                "remove_greater_key null: rimuove dalla collezione tutti gli elementi con chiave superiore a quella specificata\n" +
                "execute_script file_name: legge ed esegue lo script dal file specificato. Lo script contiene comandi nel formato in cui vengono inseriti dall'utente in modalità interattiva.\n" +
                "show: visualizza tutti gli elementi della collezione in formato di stringa\n" +
                "clear: cancella la collezione\n" +
                "insert null {element}: aggiunge un nuovo elemento con la chiave specificata\n" +
                "update id {element}: aggiorna il valore dell'elemento nella collezione con l'id specificato\n" +
                "remove_greater {element}: rimuove dalla collezione tutti gli elementi superiori a quello specificato\n" +
                "remove_lower {element}: rimuove dalla collezione tutti gli elementi inferiori a quello specificato\n" +
                "print_field_descending_mood: visualizza i valori del campo mood di tutti gli elementi in ordine decrescente\n" +
                "help: visualizza l'elenco dei comandi disponibili\n" +
                "count_greater_than_impact_speed impactSpeed: visualizza il numero di elementi con valore del campo impactSpeed maggiore a quello specificato\n" +
                "remove_key null: rimuove un elemento dalla collezione in base alla chiave\n" +
                "info: visualizza informazioni sulla collezione (tipo, data di inizializzazione, numero di elementi, ecc.)\n"
        },
                    {"count", "Calcolare"},
                    {"enter speed", "Inserire la velocità"},
                    {"impossible clear", "Non è possibile svuotare la collezione"},
                    {"not admin", "Solo un amministratore può svuotare l'intera collezione"}
            };
    public Object[][] getContents() {
        return contents;
    }
}
