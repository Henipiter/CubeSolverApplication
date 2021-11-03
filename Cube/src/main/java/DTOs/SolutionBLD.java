package DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
public class SolutionBLD extends Solution {
    private ArrayList<Move> algorithm;
    private String marks;
    private ArrayList<Integer> elementIndexes;

    public static String getWholeMarks(ArrayList<SolutionBLD> solution) {
        StringBuilder stringBuilder = new StringBuilder();
        solution.forEach(moves -> stringBuilder.append(moves.getMarks()).append(" "));
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    @Override
    public ArrayList<Move> getWholeAlg(Object solution) {
        ArrayList<SolutionBLD> solutionBLD = (ArrayList<SolutionBLD>) solution;
        ArrayList<Move> alg = new ArrayList<>();
        solutionBLD.forEach(moves -> alg.addAll(moves.algorithm));
        return alg;
    }

    public SolutionBLD(ArrayList<Move> algorithm, String marks, ArrayList<Integer> elementIndexes,
                       ElementType elementType){
        this.algorithm = algorithm;
        this.marks = marks;
        this.elementIndexes = elementIndexes;
        this.elementType = elementType;
        this.progressInfo = ProgressInfo.NONE;
    }
}


