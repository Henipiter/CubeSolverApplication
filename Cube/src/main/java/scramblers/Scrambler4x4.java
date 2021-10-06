package scramblers;

import DTOs.Move;

import java.util.ArrayList;

public class Scrambler4x4 implements Scrambler{

    private static final int ALGORITHM_LENGTH = 44;
    private static final int MOVE_ENUM_RANGE = 12;

    @Override
    public ArrayList<Move> getScramble() {
        return getScramble(ALGORITHM_LENGTH, MOVE_ENUM_RANGE);
    }
}
