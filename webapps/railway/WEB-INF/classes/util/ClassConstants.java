package util;

import java.util.HashMap;

public enum ClassConstants {
    AC(1, 1000, "AC"), NONAC(2, 700, "NONAC"), SEATER(3, 500, "SEATER");

    int id;
    int cost;
    String className;
    private static final HashMap<Integer, ClassConstants> MAP = new HashMap<>();

    private ClassConstants(int id, int cost, String className) {
        this.id = id;
        this.cost = cost;
        this.className = className;
    }

    static {
        for (ClassConstants f : values()) {
            MAP.put(f.id, f);
        }
    }

    private ClassConstants(int fieldNumber) {
        this.id = fieldNumber;
    }

    public int value() {
        return this.id;
    }

    public int cost() {
        return this.cost;
    }

    public String className() {
        return this.className;
    }

    public static ClassConstants getValueById(int id) {
        return MAP.get(id);
    }
}