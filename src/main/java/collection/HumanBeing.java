package collection;

import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HumanBeing implements Comparable<HumanBeing>{
    private final Map<Fields, Predicate<String>> notNullSetters = new LinkedHashMap<>();
    private final Map<Fields, Consumer<String>> setters = new LinkedHashMap<>();
    private UUID id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private Integer impactSpeed; //Максимальное значение поля: 496, Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле может быть null

    {
        addNotNullSetter(Fields.NAME, this::isSetName);
        addNotNullSetter(Fields.COORDINATES, this::isSetCoordinates);
        addNotNullSetter(Fields.IMPACTSPEED, this::isSetImpactSpeed);
        addSetter(Fields.REALHERO, this::setRealHero);
        addSetter(Fields.HASTOOTHPICK, this::setHasToothpick);
        addSetter(Fields.WEAPONTYPE, this::setWeaponType);
        addSetter(Fields.MOOD, this::setMood);
        addSetter(Fields.CARCOOL, this::setCar);
    }


    public HumanBeing(UUID id, String name, Coordinates coordinates, LocalDate creationDate, boolean realHero, boolean hasToothpick,
                      Integer impactSpeed, WeaponType weaponType, Mood mood, Car car){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero= realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public HumanBeing(){
        this.creationDate = LocalDate.now();
    }

    public Map<Fields, Predicate<String>> getNotNullSetters() {
        return notNullSetters;
    }

    public Map<Fields, Consumer<String>> getSetters() {
        return setters;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setRealHero(String realHero) {
        if(realHero.equals("1")) this.realHero = true;
        else this.realHero = Boolean.parseBoolean(realHero);
    }

    public void setHasToothpick(String hasToothpick) {
        if(hasToothpick.equals("1")) this.hasToothpick = true;
        else this.hasToothpick = Boolean.parseBoolean(hasToothpick);
    }

    public void setImpactSpeed(Integer impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = WeaponType.getWeaponType(weaponType);
    }

    public void setMood(String mood) {
        this.mood = Mood.getMood(mood);
    }

    public void setCar(String isCarCool) {
        boolean coolCar = Boolean.parseBoolean(isCarCool);
        this.car = new Car(coolCar);
    }

    public boolean isSetName(String name){
        NameValidator nameValidator = new NameValidator(name);
        if(nameValidator.isValid()){
            setName(name);
            return true;
        }
        return false;
    }

    public boolean isSetCoordinates(String coordinates){
        String[] coords = coordinates.split(",");
        CoordinatesValidator coordinatesValidator = new CoordinatesValidator(coords);
        if(coordinatesValidator.isValid()){
            float x = Float.parseFloat(coords[0]);
            Integer y = Integer.parseInt(coords[1]);
            setCoordinates(new Coordinates(x,y));
            return true;
        }
        return false;
    }

    public boolean isSetImpactSpeed(String impactSpeed){
        if(impactSpeed.equals("")) {
            setImpactSpeed(null);
            return true;
        }
        else{
            ImpactSpeedValidator impactSpeedValidator = new ImpactSpeedValidator(impactSpeed);
            if(impactSpeedValidator.isValid()){
                setImpactSpeed(Integer.parseInt(impactSpeed));
                return true;
            }
        }
        return false;
    }

    public UUID getId() {
        return id;
    }

    public Integer getImpactSpeed() {
        return impactSpeed;
    }

    public Mood getMood(){
        return mood;
    }

    public int getMoodPower() {
        return mood.getPower();
    }

    public String getAllFieldsAsString() {
        String impactSpeedString = (impactSpeed != null) ? impactSpeed.toString() : "";
        String weaponTypeString = (weaponType != null) ? weaponType.toString() : "";
        String moodString = (mood != null) ? mood.toString() : "";
        String carString = (car != null) ? String.valueOf(car.getStatus()) : "";

        return id + "," + name + "," + coordinates.getX() + "," + coordinates.getY() + "," + creationDate + ","
                + realHero + "," + hasToothpick + "," + impactSpeedString + "," + weaponTypeString + ","
                + moodString + "," + carString;
    }

    private int countPower(){
        int power = 0;
        if(realHero) power += 100;
        if(car.getStatus()) power += 75;
        if(hasToothpick) power -= 50;
        int powerImpactSpeed = impactSpeed == null ? 0 : impactSpeed;
        int weaponPower = weaponType == null ? 0 : weaponType.getPower();
        int moodPower = mood == null ? 0 : mood.getPower();
        power += powerImpactSpeed + weaponPower + moodPower;
        return power;
    }

    public void addNotNullSetter(Fields field, Predicate<String> predicate){
        notNullSetters.put(field, predicate);
    }

    public void addSetter(Fields field, Consumer<String> consumer){
        setters.put(field, consumer);
    }

    @Override
    public int compareTo(HumanBeing human){
        return this.countPower() - human.countPower();
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n coordinates.x=" + coordinates.getX() +
                ",\n coordinates.y=" + coordinates.getY() +
                ",\n creationDate=" + creationDate +
                ",\n realHero=" + realHero +
                ",\n hasToothpick=" + hasToothpick +
                ",\n impactSpeed=" + impactSpeed +
                ",\n weaponType=" + weaponType +
                ",\n mood=" + mood +
                ",\n car.cool=" + car.getStatus() +
                "\n}";
    }


}