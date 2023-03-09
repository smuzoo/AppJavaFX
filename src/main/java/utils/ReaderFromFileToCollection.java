package utils;

import collection.*;
import validators.Errors;
import validators.file.DataFileValidator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class ReaderFromFileToCollection {
    private final String FILE_PATH;
    private Map<UUID, HumanBeing> humanBeingCollection = new HashMap<>();

    public ReaderFromFileToCollection(String FILE_PATH){
        this.FILE_PATH = FILE_PATH;
    }

    public void read(){
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(FILE_PATH));
            BufferedReader bufferedReader = new BufferedReader(reader)){
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] data = split(line, ',');
                DataFileValidator dataValidator = new DataFileValidator(data);
                if(!dataValidator.isValidateData()) continue;
                try {
                    final UUID id = UUID.fromString(data[0]);
                    final String name = data[1];
                    final Coordinates coordinates = new Coordinates(Float.parseFloat(data[2]), Integer.parseInt(data[3]));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    formatter = formatter.withLocale(Locale.ROOT);
                    final LocalDate date = LocalDate.parse(data[4], formatter);
                    final boolean realHero = Boolean.parseBoolean(data[5]);
                    final boolean hasToothpick = Boolean.parseBoolean(data[6]);
                    final Integer impactSpeed = data[7].equals("") ? null : Integer.parseInt(data[7]);
                    final WeaponType weaponType = data[8].equals("") ? null : WeaponType.valueOf(data[8]);
                    final Mood mood = data[9].equals("") ? null : Mood.valueOf(data[9]);
                    final Car car = data[10].equals("") ? new Car() : new Car(Boolean.parseBoolean(data[10]));
                    HumanBeing human = new HumanBeing(id, name, coordinates, date, realHero, hasToothpick, impactSpeed, weaponType,
                            mood, car);
                    humanBeingCollection.put(id, human);
                } catch (NumberFormatException e){
                    /* logging Y*/

                } catch (IllegalArgumentException e){
                    //logging UUID and enums

                }catch (DateTimeParseException e){
                    /* logging - Data*/

                }

            }
        }catch (IOException e){
            System.out.println(Errors.IMPOSSIBLEREADFILE);
        }
    }

    public Map<UUID, HumanBeing> getHumanBeingCollection() {
        return humanBeingCollection;
    }

    private String[] split(String s, char regex){

        List<String> ansString = new ArrayList<>();
        String line = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == regex){
                ansString.add(line);
                line = "";
            }
            else {
                line += String.valueOf(s.charAt(i));
            }
        }
        ansString.add(line);
        return ansString.toArray(new String[ansString.size()]);
    }

}
