package scramblers;

import DTOs.Move;
import DTOs.MoveTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;

public class ScramblerPyraminx implements Scrambler {
    private static final int ALGORITHM_LENGTH = 10;
    private static final int MOVE_ENUM_RANGE = 4;
    private static final int MOVE_TYPE_ENUM_RANGE = 2;

    private ArrayList<Integer> replaceDMoveToBMove(ArrayList<Integer> mainInts) {
        for (int i = 0; i < mainInts.size(); i++) {
            if (mainInts.get(1) == 3) {
                mainInts.set(1, 4);
            }
        }
        return mainInts;
    }

    private ArrayList<Move> replaceDoubleToPrim(ArrayList<Move> smallMoves) {
        for (Move move : smallMoves) {
            if (move.getMoveTypeEnum() == MoveTypeEnum.DOUBLE) {
                move.setMoveTypeEnum(MoveTypeEnum.PRIM);
            }
        }
        return smallMoves;
    }

    @Override
    public ArrayList<Move> getScramble() {
        ArrayList<Integer> mainInts = getRandomInts(ALGORITHM_LENGTH, MOVE_ENUM_RANGE);
        ArrayList<Move> scramble = intsToMoves(replaceDMoveToBMove(mainInts), MOVE_TYPE_ENUM_RANGE);

        ArrayList<Integer> smallInts = new ArrayList<>(Arrays.asList(12, 13, 14, 15));
        ArrayList<Move> smallScramble = intsToMoves(replaceDMoveToBMove(smallInts), MOVE_TYPE_ENUM_RANGE);
        scramble.addAll(smallScramble);
        return replaceDoubleToPrim(scramble);
    }
}
