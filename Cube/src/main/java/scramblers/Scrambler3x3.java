package scramblers;

import DTOs.Move;

import java.util.ArrayList;

public class Scrambler3x3 implements Scrambler{
    private static final int ALGORITHM_LENGTH = 21;
    private static final int MOVE_ENUM_RANGE = 6;

    @Override
    public ArrayList<Move> getScramble() {
        return getScramble(ALGORITHM_LENGTH, MOVE_ENUM_RANGE);
    }
}
