package collection;

import java.util.UUID;

public class HumanBeing implements Comparable<HumanBeing> {
    private UUID id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private Integer impactSpeed; //Максимальное значение поля: 496, Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле может быть null

    public HumanBeing(UUID id, String name, Coordinates coordinates, java.time.LocalDate creationDate, boolean realHero, boolean hasToothpick,
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

    private int countPower(){
        int power = 0;
        if(realHero) power += 100;
        if(car.getStatus()) power += 75;
        if(hasToothpick) power -= 50;
        power += impactSpeed + weaponType.getPower() + mood.getPower();
        return power;
    }

    @Override
    public int compareTo(HumanBeing human){
        return this.countPower() - human.countPower();
    }

    public UUID getId() {
        return id;
    }

    public Integer getImpactSpeed() {
        return impactSpeed;
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