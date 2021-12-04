package DTOs;

public enum MoveTypeEnum {
    BLANK(0),
    SIMPLE(1),
    DOUBLE(2),
    PRIM(3),
    INVALID(-2);

    private final int value;

    MoveTypeEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public static MoveTypeEnum returnEnumByInt(int val) {
        for (MoveTypeEnum type : MoveTypeEnum.values()) {
            if (val == type.value)
                return type;
        }
        return INVALID;
    }

    @Override
    public String toString() {
        switch (this) {
            case SIMPLE:
                return "";
            case DOUBLE:
                return "2";
            case PRIM:
                return "'";
            case INVALID:
                return "INVALID";
            case BLANK:
                return "--";
            default:
                throw new IllegalArgumentException();
        }
    }

    public static MoveTypeEnum simplify(MoveTypeEnum type1, MoveTypeEnum type2) {
        int sum = (type1.value + type2.value) % 4;
        return returnEnumByInt(sum);
    }
}
