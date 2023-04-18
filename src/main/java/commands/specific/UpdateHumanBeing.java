package commands.specific;

import Database.Database;
import collection.Fields;
import collection.HumanBeing;
import collection.HumanBeingCollection;
import commands.Command;
import utils.readers.Reader;
import utils.UpdateHumanBeingObject;
import validators.Errors;
import validators.fields.ExistIDValidator;
import validators.fields.HumanForUserValidator;

import java.util.Arrays;
import java.util.UUID;

import static colors.Colors.*;

/**
 * The command Update human being.
 */
public class UpdateHumanBeing implements Command {

    private final Reader reader;


    /**
     * Instantiates a new Update human being.
     *
     * @param reader the reader
     */
    public UpdateHumanBeing(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(String idArgument){
            ExistIDValidator existIDValidator = new ExistIDValidator(idArgument);
            if(existIDValidator.isValid()){
                Long id = Long.parseLong(idArgument);
                HumanBeing human = HumanBeingCollection.getHuman(id);
                HumanForUserValidator hfuv = new HumanForUserValidator(human);
                if(hfuv.isValid()){
                    printHuman(human);
                    printAllFields();
                    int[] numbersFields = getNumbersFields();
                    if(numbersFields != null){
                        UpdateHumanBeingObject updateHumanBeing = new UpdateHumanBeingObject(reader);
                        for(int numberField : numbersFields) {
                            Fields field = Fields.getForOrder(numberField);
                            if (field != null) {
                                HumanBeing humanBeing = updateHumanBeing.update(human, field);
                                Database db = Database.getInstance();
                                db.deleteById("human_beings", humanBeing.getId());
                                int update = db.addHumanBeingToDatabase("human_beings", humanBeing);
                                if (update > 0) {
                                    HumanBeingCollection.add(humanBeing);
                                    System.out.println("поле было успешно изменено");
                                }
                            } else break;
                    }
                    }
                }
            }
        }


    private int[] getNumbersFields(){
        String[] numbersFields = reader.getNewLine().split(",");
        int[] intNumbersFields = new int[numbersFields.length];
        for (int i = 0; i < numbersFields.length; i++) {
            try {
                intNumbersFields[i] = Integer.parseInt(numbersFields[i]);
            }catch (NumberFormatException e){
                System.out.println(Errors.NOTCANTRANSFORMTOINT);
                return null;
            }
        }
        Arrays.sort(intNumbersFields);
        return intNumbersFields;
    }

    private void printAllFields(){
        System.out.println("Введите через запятую номера полей, которые хотите изменить:");
        Fields[] fieldsValues = Fields.values();
        for(int i =1; i <= fieldsValues.length; i++){
            System.out.println(i + " - " + fieldsValues[i-1].getName());
        }
    }

    private void printHuman(HumanBeing human){
        System.out.println("Данный элемент имеет следующие параметры: " + human);
    }

    @Override
    public String description(){
        return BLUE + "update" + PURPLE + " id {element}" + RESET + " : обновить значение элемента коллекции, id которого равен заданному";
    }

}
