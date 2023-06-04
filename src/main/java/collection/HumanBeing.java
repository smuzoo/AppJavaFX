package collection;

import authentication.User;
import validators.fields.CoordinatesValidator;
import validators.fields.ImpactSpeedValidator;
import validators.fields.NameValidator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The main class that is stored in the collection
 */
public class HumanBeing implements Comparable<HumanBeing>{
    private final Map<Fields, Predicate<String>> notNullSetters = new LinkedHashMap<>(); // Все сеттеры, устанавливающие поля, которые не должны быть null
    private final Map<Fields, Consumer<String>> setters = new LinkedHashMap<>(); // Все сеттеры, устанавливающие поля, которые могут быть null
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private Integer impactSpeed; //Максимальное значение поля: 496, Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле может быть null
    private String userLogin;
    {
        /**Adding all setters to Map*/
        addNotNullSetter(Fields.NAME, this::isSetName);
        addNotNullSetter(Fields.COORDINATES, this::isSetCoordinates);
        addNotNullSetter(Fields.IMPACTSPEED, this::isSetImpactSpeed);
        addSetter(Fields.REALHERO, this::setRealHero);
        addSetter(Fields.HASTOOTHPICK, this::setHasToothpick);
        addSetter(Fields.WEAPONTYPE, this::setWeaponType);
        addSetter(Fields.MOOD, this::setMood);
        addSetter(Fields.CARCOOL, this::setCar);
    }


    /**
     * Instantiates a new Human being.
     *
     * @param id           the id
     * @param name         the name
     * @param coordinates  the coordinates
     * @param creationDate the creation date
     * @param realHero     the real hero
     * @param hasToothpick the has toothpick
     * @param impactSpeed  the impact speed
     * @param weaponType   the weapon type
     * @param mood         the mood
     * @param car          the car
     * @param userLogin    the user login
     */
    public HumanBeing(Long id, String name, Coordinates coordinates, LocalDate creationDate, boolean realHero, boolean hasToothpick,
                      Integer impactSpeed, WeaponType weaponType, Mood mood, Car car, String userLogin){
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
        this.userLogin = userLogin;
    }

    /**
     * Instantiates a new Human being.
     */
    public HumanBeing(){
        this.creationDate = LocalDate.now();
        this.userLogin = User.getLogin();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Timestamp getCreationDate() {
        LocalDateTime localDateTime = creationDate.atStartOfDay();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Timestamp.valueOf(zonedDateTime.toLocalDateTime());
    }

    public String getStringCreationDate() {
        LocalDateTime localDateTime = creationDate.atStartOfDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDateTime.format(formatter);
    }

    /**
     * Is real hero boolean.
     *
     * @return the boolean
     */
    public boolean isRealHero() {
        return realHero;
    }

    /**
     * Is has toothpick boolean.
     *
     * @return the boolean
     */
    public boolean isHasToothpick() {
        return hasToothpick;
    }

    /**
     * Gets weapon type.
     *
     * @return the weapon type
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Get string weapon type string.
     *
     * @return the string
     */
    public String getStringWeaponType(){
        return getWeaponType() == null ? "null" : getWeaponType().toString();
    }


    /**
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Gets user login.
     *
     * @return the user login
     */
    public String getUserLogin() {
        return userLogin;
    }


    /**
     * Gets not null setters.
     *
     * @return the not null setters
     */
    public Map<Fields, Predicate<String>> getNotNullSetters() {
        return notNullSetters;
    }

    /**
     * Gets setters.
     *
     * @return the setters
     */
    public Map<Fields, Consumer<String>> getSetters() {
        return setters;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Sets real hero.
     *
     * @param realHero the real hero
     */
    public void setRealHero(String realHero) {
        if(realHero.equals("1")) this.realHero = true;
        else this.realHero = Boolean.parseBoolean(realHero);
    }

    public void setRealHero(boolean realHero) {
       this.realHero = realHero;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    /**
     * Sets has toothpick.
     *
     * @param hasToothpick the has toothpick
     */
    public void setHasToothpick(String hasToothpick) {
        if(hasToothpick.equals("1")) this.hasToothpick = true;
        else this.hasToothpick = Boolean.parseBoolean(hasToothpick);
    }

    /**
     * Sets impact speed.
     *
     * @param impactSpeed the impact speed
     */
    public void setImpactSpeed(Integer impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    /**
     * Sets weapon type.
     *
     * @param weaponType the weapon type
     */
    public void setWeaponType(String weaponType) {
        this.weaponType = WeaponType.getWeaponType(weaponType);
    }
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Sets mood.
     *
     * @param mood the mood
     */
    public void setMood(String mood) {
        this.mood = Mood.getMood(mood);
    }


    public void setMood(Mood mood) {
        this.mood = mood;
    }

    /**
     * Sets car.
     *
     * @param isCarCool the is car cool
     */
    public void setCar(String isCarCool) {
        boolean coolCar = Boolean.parseBoolean(isCarCool);
        this.car = new Car(coolCar);
    }

    public void setCar(boolean isCarCool) {
        this.car = new Car(isCarCool);
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * A method that returns a boolean indicating whether the name field is set or not
     *
     * @param name the name
     * @return boolean boolean
     */
    public boolean isSetName(String name){
        NameValidator nameValidator = new NameValidator(name);
        if(nameValidator.isValid()){
            setName(name);
            return true;
        }
        return false;
    }

    /**
     * A method that returns a boolean indicating whether the coordinate field of the given object is set or not
     *
     * @param coordinates the coordinates
     * @return boolean boolean
     */
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

    /**
     * A method that returns a boolean indicating whether the hit rate field is set or not
     *
     * @param impactSpeed the impact speed
     * @return boolean boolean
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets impact speed.
     *
     * @return the impact speed
     */
    public Integer getImpactSpeed() {
        return impactSpeed == null ? 0 : impactSpeed;
    }

    /**
     * Get mood mood.
     *
     * @return the mood
     */
    public Mood getMood(){
        return mood;
    }

    /**
     * Get string mood string.
     *
     * @return the string
     */
    public String getStringMood(){
        return getMood() == null ? "null" : getMood().toString();
    }


    /**
     * Gets mood power.
     *
     * @return the mood power
     */
    public int getMoodPower() {
        return mood.getPower();
    }

    /**
     * A method that returns all field values separated by commas; if the field is null, it is replaced with an empty string
     *
     * @return String all fields as string
     */
    public String getAllFieldsAsString() {
        String impactSpeedString = (impactSpeed != null) ? impactSpeed.toString() : "";
        String weaponTypeString = (weaponType != null) ? weaponType.toString() : "";
        String moodString = (mood != null) ? mood.toString() : "";
        String carString = (car != null) ? String.valueOf(car.getStatus()) : "";

        return id + "," + name + "," + coordinates.getX() + "," + coordinates.getY() + "," + creationDate + ","
                + realHero + "," + hasToothpick + "," + impactSpeedString + "," + weaponTypeString + ","
                + moodString + "," + carString;
    }

    /**A method that counts the "power" of an object - how strong all of its characteristics are
     * @return int
     */
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

    /**
     * Add not null setter.
     *
     * @param field     the field
     * @param predicate the predicate
     */
    public void addNotNullSetter(Fields field, Predicate<String> predicate){
        notNullSetters.put(field, predicate);
    }

    /**
     * Add setter.
     *
     * @param field    the field
     * @param consumer the consumer
     */
    public void addSetter(Fields field, Consumer<String> consumer){
        setters.put(field, consumer);
    }

    /** A method for comparing dragons by power field
     * @return int
     * */
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
                ",\n userLogin=" + userLogin +
                "\n}";
    }
}