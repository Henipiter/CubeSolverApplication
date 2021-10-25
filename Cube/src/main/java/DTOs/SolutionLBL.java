package DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolutionLBL extends Solution {
    private String message;
    private char color;
    private InfoType infoType;

    public SolutionLBL(ArrayList<Move> algorithm, String message) {
        this.algorithm = algorithm;
        this.message = message;
        this.infoType = InfoType.DEFAULT;
    }

    public SolutionLBL(ArrayList<Move> algorithm, String message, char color) {
        this.algorithm = algorithm;
        this.color = color;
        this.message = message;
        this.infoType = InfoType.COLOR;
    }

    public SolutionLBL(ArrayList<Move> algorithm, String message, ArrayList<Integer> elementIndexes) {
        this.algorithm = algorithm;
        this.elementIndexes = elementIndexes;
        this.message = message;
        this.infoType = InfoType.INDEX;
    }

    public SolutionLBL(ArrayList<Move> algorithm, String message, char color, ArrayList<Integer> elementIndexes) {
        this.algorithm = algorithm;
        this.elementIndexes = elementIndexes;
        this.color = color;
        this.message = message;
        this.infoType = InfoType.ALL;
    }

    public SolutionLBL(InfoType infoType) {
        this.infoType = infoType;
    }

    public static ArrayList<Move> getWholeAlgLBL(ArrayList<SolutionLBL> solution) {
        ArrayList<Move> alg = new ArrayList<>();
        solution.forEach(moves -> alg.addAll(moves.algorithm));
        return alg;
    }

    public void setElementIndexes(ArrayList<Integer> elementIndexes){
        this.elementIndexes = elementIndexes;
    }

    public ArrayList<Move> getAlgorithm(){
        return algorithm;
    }
}
