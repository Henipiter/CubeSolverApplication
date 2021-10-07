package DTOs;

public enum MoveEnum {
    R(0), L(1),
    U(2), D(3),
    F(4), B(5),
    Rw(6), Lw(7),
    Uw(8), Dw(9),
    Fw(10), Bw(11),
    r(12), l(13),
    u(14), d(15),
    f(16), b(17),
    M(18), S(19),
    E(20), x(21),
    y(22), z(23),
    INVALID(24),
    BLANK(25);

    MoveEnum(final int newValue) {
        value = newValue;
    }

    private final int value;

    public static MoveEnum returnEnumByInt(int val) {
        for (MoveEnum type : MoveEnum.values()) {
            if (val == type.value)
                return type;
        }
        return INVALID;
    }

    public int getValue() {
        return value;
    }
}