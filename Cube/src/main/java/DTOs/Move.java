package DTOs;

import lombok.Data;

import static DTOs.MoveEnum.BLANK;

@Data
public class Move {

    private MoveTypeEnum moveTypeEnum;
    private MoveEnum moveEnum;

    public Move(MoveEnum moveEnum, MoveTypeEnum moveTypeEnum) {
        this.moveEnum = moveEnum;
        this.moveTypeEnum = moveTypeEnum;
        validateMove();
    }

    public Move(Move move) {
        this.moveEnum = move.getMoveEnum();
        this.moveTypeEnum = move.getMoveTypeEnum();
        validateMove();
    }

    public Move(String moveString){
        Move move = InspectMove.getMove(moveString);
        this.moveEnum = move.getMoveEnum();
        this.moveTypeEnum = move.getMoveTypeEnum();
        validateMove();
    }

    private void validateMove() {
        if (moveEnum == BLANK || moveTypeEnum == MoveTypeEnum.BLANK) {
            this.moveEnum = BLANK;
            this.moveTypeEnum = MoveTypeEnum.BLANK;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Move)) {
            return false;
        }
        Move c = (Move) o;
        return c.getMoveEnum() == this.getMoveEnum() &&
                c.getMoveTypeEnum() == this.getMoveTypeEnum();
    }

    @Override
    public String toString() {
        return moveEnum.toString() + moveTypeEnum.toString();
    }
}
