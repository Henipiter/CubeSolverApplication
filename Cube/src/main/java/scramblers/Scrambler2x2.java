package scramblers;

import DTOs.Move;

import java.util.ArrayList;

public class Scrambler2x2 implements Scrambler {

    private static final int ALGORITHM_LENGTH = 9;
    private static final int MOVE_ENUM_RANGE = 6;

    @Override
    public ArrayList<Move> getScramble() {
        return getScramble(ALGORITHM_LENGTH, MOVE_ENUM_RANGE);
    }
}
