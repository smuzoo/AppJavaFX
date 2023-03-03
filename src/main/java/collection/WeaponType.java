package collection;

import org.jetbrains.annotations.Nullable;

public enum WeaponType {
    HAMMER("HAMMER", "1", 10),
    PISTOL("PISTOL", "2", 45),
    SHOTGUN("SHOTGUN", "3", 80);

    private final String name, order;

    private final int power;

    WeaponType(String name, String order, int power) {
        this.name = name;
        this.order = order;
        this.power = power;
    }


    public String getName() {
        return name;
    }

    public String getOrder() {
        return order;
    }

    public int getPower() {
        return power;
    }

    public static @Nullable WeaponType getWeaponType(String s) {
        WeaponType[] weaponTypesValues = WeaponType.values();
        for (WeaponType weaponType : weaponTypesValues) {
            if (s.equals(weaponType.getName()) || s.equals(weaponType.getOrder())) {
                return weaponType;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.getName();
    }

}