package DTOs;

import static DTOs.MoveEnum.INVALID;

/** Parse moves for cubes
 *
 */
public class InspectMove {
    private char move;
    private MoveTypeEnum moveTypeEnum;
    private MoveEnum moveEnum;

    public InspectMove(MoveEnum moveEnum, MoveTypeEnum moveTypeEnum){
        this.moveEnum = moveEnum;
        this.moveTypeEnum = moveTypeEnum;
    }

    public InspectMove(String direction){
        recogniseType(direction);
        recogniseMove(direction);
    }

    private void recogniseMove(String direction){
        moveEnum = INVALID;
        if(direction.contains("'") || direction.contains("2"))
            direction = direction.substring(0, direction.length() - 1);
        for(MoveEnum i : MoveEnum.values()){
            if( direction.equals(i.toString())){
                moveEnum=i;
                break;
            }
        }
    }

    private void recogniseType(String direction){
        moveTypeEnum = MoveTypeEnum.INVALID;
        if(direction.length()==2 || direction.length()==3){
            if( direction.charAt(direction.length()-1) == '2')
                moveTypeEnum = MoveTypeEnum.DOUBLE;
            else if( direction.charAt(direction.length()-1) == '\'')
                moveTypeEnum = MoveTypeEnum.PRIM;
        }
        if(( direction.length()==2 && direction.charAt(1)=='w')|| direction.length()==1 )
            moveTypeEnum = MoveTypeEnum.SIMPLE;

    }

    public MoveEnum getMove() {
        return moveEnum;
    }

    public MoveTypeEnum getMoveType() {
        return moveTypeEnum;
    }

    public void setMoveType(MoveTypeEnum moveTypeEnum) {
        this.moveTypeEnum = moveTypeEnum;
    }

    public void setMoveEnum(MoveEnum moveEnum) {
        this.moveEnum = moveEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof InspectMove)) {
            return false;
        }
        InspectMove c = (InspectMove) o;


        return c.getMove() == this.getMove() &&
                c.getMoveType() == this.getMoveType();
    }
}
