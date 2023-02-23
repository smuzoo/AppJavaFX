package utils;

import collection.*;
import validators.file.DataFileValidator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static colors.Colors.RED;
import static colors.Colors.RESET;

public class ReaderHumanBeingCollection {
    private final String FILE_PATH;
    private Map<UUID, HumanBeing> humanBeingCollection = new HashMap<>();
    public ReaderHumanBeingCollection(String FILE_PATH){
        this.FILE_PATH = FILE_PATH;
    }

    public Map<UUID, HumanBeing> read(){
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(FILE_PATH));
            BufferedReader br = new BufferedReader(reader)){
            String line = br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                DataFileValidator dataValidator = new DataFileValidator(data);
                if(!dataValidator.isValidateData()) continue;
                final UUID id = UUID.fromString(data[0]);
                final String name = data[1];
                final Coordinates coordinates = new Coordinates(Float.parseFloat(data[2]), Integer.parseInt(data[3]));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                formatter = formatter.withLocale(Locale.ROOT);
                final LocalDate date = LocalDate.parse(data[4], formatter);
                final boolean realHero = Boolean.parseBoolean(data[5]);
                final boolean hasToothpick = Boolean.parseBoolean(data[6]);
                final Integer impactSpeed = Integer.parseInt(data[7]);
                final WeaponType weaponType = WeaponType.valueOf(data[8]);
                final Mood mood = Mood.valueOf(data[9]);
                final Car car = new Car(Boolean.parseBoolean(data[10]));
                HumanBeing human = new HumanBeing(id, name, coordinates, date, realHero, hasToothpick, impactSpeed, weaponType,
                        mood, car);
                humanBeingCollection.put(id, human);
            }
        }catch (IOException e){
            System.out.println(RED + "Невозможно прочитать файл" + RESET);
        }

        return humanBeingCollection;
    }

}
