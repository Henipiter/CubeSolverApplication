package scramblers;

import DTOs.Move;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;

import java.util.ArrayList;

public interface Scrambler {

    int MOVE_TYPE_ENUM_RANGE = 3;
    ArrayList<Move> getScramble();

    default boolean isMovesOpposite(int moveValue1, int moveValue2) {
        moveValue1 = moveValue1 % 6;
        moveValue2 = moveValue2 % 6;
        return moveValue1 == moveValue2 || moveValue1 / 2 == moveValue2 / 2;
    }

    default int getRandomValue(int upperBound) {
        return (int) (Math.random() * upperBound);
    }

    default ArrayList<Move> getScramble(int algLength, int moveEnumRange) {
        ArrayList<Move> scramble = new ArrayList<>();
        int lastMoveValue = -1;
        int currentMoveValue = 0;
        MoveEnum moveEnum;
        MoveTypeEnum moveTypeEnum;
        for (int i = 0; i < algLength; i++) {
            while (isMovesOpposite(lastMoveValue, currentMoveValue)) {
                currentMoveValue = getRandomValue(moveEnumRange);
            }
            moveEnum = MoveEnum.returnEnumByInt(currentMoveValue);
            moveTypeEnum = MoveTypeEnum.returnEnumByInt(getRandomValue(MOVE_TYPE_ENUM_RANGE)+1);
            scramble.add(new Move(moveEnum, moveTypeEnum));
            lastMoveValue = currentMoveValue;
        }
        return scramble;

    }
}
