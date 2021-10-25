package DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class SolutionBLD extends Solution {
    private ArrayList<Move> algorithm;
    private String marks;
    private ArrayList<Integer> elementIndexes;

    public static String getWholeMarks(ArrayList<SolutionBLD> solution) {
        StringBuilder stringBuilder = new StringBuilder();
        solution.forEach(moves -> stringBuilder.append(moves.getMarks()).append(" "));
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    public static ArrayList<Move> getWholeAlgBLD(ArrayList<SolutionBLD> solution) {
        ArrayList<Move> alg = new ArrayList<>();
        solution.forEach(moves -> alg.addAll(moves.algorithm));
        return alg;
    }
}


