package DTOs;

public enum MoveTypeEnum {
    SIMPLE, DOUBLE, PRIM,  INVALID;

    @Override
    public String toString() {
        switch(this) {
            case SIMPLE: return "";
            case DOUBLE: return "2";
            case PRIM: return "'";
            case INVALID: return "INVALID";
            default: throw new IllegalArgumentException();
        }
    }
}