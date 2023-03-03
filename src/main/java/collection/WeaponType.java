package collection;

import org.jetbrains.annotations.Nullable;

public enum WeaponType {
    HAMMER("HAMMER", "1"),
    PISTOL("PISTOL", "2"),
    SHOTGUN("SHOTGUN", "3");

    private final String name, order;


    WeaponType(String name, String order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getOrder() {
        return order;
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