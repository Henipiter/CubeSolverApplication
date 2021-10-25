package DTOs;

import java.util.ArrayList;

public class Solution {

    protected ArrayList<Move> algorithm = new ArrayList<>();
    protected ArrayList<Integer> elementIndexes;

    public static ArrayList<Move> getWholeAlg(ArrayList solution) {
        if (solution.size() == 0) {
            return new ArrayList<>();
        }
        if (solution.get(0) instanceof SolutionLBL) {
            return SolutionLBL.getWholeAlgLBL(new ArrayList<SolutionLBL>(solution));
        }
        if (solution.get(0) instanceof SolutionBLD) {
            return SolutionBLD.getWholeAlgBLD(new ArrayList<SolutionBLD>(solution));
        }
        return null;
    }
}
