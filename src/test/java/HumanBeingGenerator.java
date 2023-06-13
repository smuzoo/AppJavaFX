import Database.Database;
import collection.Coordinates;
import collection.FuelType;
import collection.Vehicle;
import collection.VehicleType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class HumanBeingGenerator {
    private static final Random random = new Random();

    public static Vehicle generateRandomHumanBeing() {
        Vehicle vehicle = new Vehicle();

        // Генерация уникального идентификатора
        vehicle.setId(generateUniqueId());

        // Генерация случайного имени
        vehicle.setName(generateRandomName());

        // Генерация координат
        vehicle.setCoordinates(generateRandomCoordinates());

        // Генерация значения для realHero (true/false)
        vehicle.setRealHero(generateRandomBoolean());

        // Генерация значения для hasToothpick (true/false)
        vehicle.setHasToothpick(generateRandomBoolean());

        // Генерация случайной impactSpeed
        vehicle.setImpactSpeed(generateRandomImpactSpeed());

        // Генерация случайного weaponType
        vehicle.setWeaponType(generateRandomWeaponType());

        // Генерация случайного mood
        vehicle.setMood(generateRandomMood());

        // Генерация случайного автомобиля
        vehicle.setVehiclePublicity(generateRandomBoolean());

        // Генерация случайного значения для userLogin
        vehicle.setUserLogin("Marat");

        return vehicle;
    }

    private static Long generateUniqueId() {
        // Генерация случайного положительного числа
        ResultSet rs = Database.getInstance().getNewId("users_id_seq");
        try {
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateRandomName() {
        // Список возможных имен
        String[] names = {"John", "Jane", "David", "Emily", "Michael", "Sarah"};

        // Выбор случайного имени из списка
        return names[random.nextInt(names.length)];
    }

    private static Coordinates generateRandomCoordinates() {


        float minValue = -Float.MAX_VALUE;
        float maxValue = -809f;
        double rand = Math.random();
        float x = (float) (minValue + rand * (maxValue - minValue));


        // Генерация случайного значения для y
        Integer y = random.nextInt();

        return new Coordinates(x, y);
    }

    private static boolean generateRandomBoolean() {
        // Генерация случайного значения true/false
        return random.nextBoolean();
    }

    private static Integer generateRandomImpactSpeed() {
        // Генерация случайного значения для impactSpeed от 0 до 496
        return random.nextInt(495);
    }

    private static VehicleType generateRandomWeaponType() {
        // Список возможных типов оружия
        VehicleType[] vehicleTypes = {VehicleType.HAMMER, VehicleType.BOAT, VehicleType.SHIP};

        // Выбор случайного типа оружия из списка
        return vehicleTypes[random.nextInt(vehicleTypes.length)];
    }

    private static FuelType generateRandomMood() {
        FuelType[] fuelTypes = {FuelType.ALCOHOL, FuelType.KEROSENE, FuelType.NUCLEAR, FuelType.GASOLINE};

        // Выбор случайного типа оружия из списка
        return fuelTypes[random.nextInt(fuelTypes.length)];
    }
}

