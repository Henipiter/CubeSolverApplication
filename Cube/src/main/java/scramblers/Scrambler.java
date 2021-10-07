package scramblers;

import DTOs.Move;
import DTOs.MoveEnum;
import DTOs.MoveTypeEnum;

import java.util.ArrayList;

public interface Scrambler {

    ArrayList<Move> getScramble();

    default boolean isMovesOpposite(int moveValue1, int moveValue2) {
        moveValue1 = moveValue1 % 6;
        moveValue2 = moveValue2 % 6;
        return moveValue1 == moveValue2 || moveValue1 / 2 == moveValue2 / 2;
    }

    default int getRandomValue(int upperBound) {
        return (int) (Math.random() * upperBound);
    }

    default ArrayList<Move> intsToMoves(ArrayList<Integer> array, int enumRange) {
        ArrayList<Move> scramble = new ArrayList<>();
        for (int i : array) {
            MoveEnum moveEnum = MoveEnum.returnEnumByInt(i);
            MoveTypeEnum moveTypeEnum = MoveTypeEnum.
                    returnEnumByInt(getRandomValue(enumRange) + 1);
            scramble.add(new Move(moveEnum, moveTypeEnum));
        }
        return scramble;
    }

    default ArrayList<Integer> getRandomInts(int algLength, int moveEnumRange) {
        ArrayList<Integer> ints = new ArrayList<>();
        int lastMoveValue = -1;
        int currentMoveValue = 0;
        for (int i = 0; i < algLength; i++) {
            while (isMovesOpposite(lastMoveValue, currentMoveValue)) {
                currentMoveValue = getRandomValue(moveEnumRange);
            }
            ints.add(currentMoveValue);
            lastMoveValue = currentMoveValue;
        }
        return ints;
    }
}
