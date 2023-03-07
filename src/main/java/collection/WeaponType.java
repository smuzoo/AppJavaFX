package collection;

import org.jetbrains.annotations.Nullable;

public enum WeaponType {
    HAMMER("HAMMER", "1", 10),
    PISTOL("PISTOL", "2", 45),
    SHOTGUN("SHOTGUN", "3", 80);

    private final String name, order;

    private int power;

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

    public void setPower(int power){
        this.power = power;
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

    public static String getStringValues() {
        StringBuilder sb = new StringBuilder();
        for (WeaponType weaponType : WeaponType.values()) {
            sb.append(weaponType.getOrder()).append(" - ").append(weaponType.getName()).append(" | ");
        }
        sb.delete(sb.length() - 3, sb.length()); // удаление последнего разделителя "| "
        return sb.toString();
    }

    @Override
    public String toString(){
        return this.getName();
    }

}