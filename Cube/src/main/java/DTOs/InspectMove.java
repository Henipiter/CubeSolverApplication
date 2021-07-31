package DTOs;

/** Parse moves for cubes
 *
 */
public class InspectMove {
    private char move;
    private MoveEnum moveEnum;


    public InspectMove(String direction){
        move = direction.charAt(0);
        moveEnum = MoveEnum.INVALID;
        if( direction.length()==2 ){
            if(direction.charAt(1)=='2')
                moveEnum = MoveEnum.DOUBLE;
            else if(direction.charAt(1)=='\'')
                moveEnum = MoveEnum.PRIM;
        }
        else if(direction.length()==1)
            moveEnum = MoveEnum.SIMPLE;

    }

    public char getMove() {
        return move;
    }

    public MoveEnum getMoveEnum() {
        return moveEnum;
    }

    public void setMoveEnum(MoveEnum moveEnum) {
        this.moveEnum = moveEnum;
    }

    public void setMove(char move) {
        this.move = move;
    }
}
